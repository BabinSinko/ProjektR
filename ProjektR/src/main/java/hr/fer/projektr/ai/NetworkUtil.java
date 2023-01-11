package hr.fer.projektr.ai;

import org.ejml.simple.SimpleMatrix;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class NetworkUtil {

    /**
     * From the population of NeuralNetworks finds and returns the index of the one with the highest fitness
     * @param population NeuralNetwork[]
     * @param fitness double[]
     * @return int index of network with the best fitness
     */
    public static int findBestPlayer(NeuralNetwork[] population, double[] fitness) {
        if (population.length != fitness.length)
            throw new IllegalArgumentException("Population and fitness must have same length");

        int ind = 0;
        double highestScore = fitness[0];

        for (int i = 0; i < fitness.length; i++) {
            if (fitness[i] > highestScore) {
                ind = i;
                highestScore = fitness[i];
            }
        }

        return ind;
    }

    public static NeuralNetwork[] pickParents(NeuralNetwork[] population, double[] fitness) {
        var parents = new NeuralNetwork[2];

        var fitnessSum = Arrays.stream(fitness).sum();
        var parentPickProbabilities = Arrays.stream(fitness).map(unitFitness -> unitFitness / fitnessSum).toArray();

        var random = new Random();

        //value from 0.1
        var randomIntervalValueFirst = random.nextDouble();
        var randomIntervalValueSecond = random.nextDouble();

        var sum = 0.0;
        for(int i = 0; i < fitness.length; i++) {
            sum += parentPickProbabilities[i];

            var firstFound = randomIntervalValueFirst <= sum;
            var secondFound = randomIntervalValueSecond <= sum;
            if(firstFound && secondFound) {
                parents[0] = population[i];
                if(i == fitness.length - 1) {
                    parents[1] = population[i - 1];
                } else {
                    parents[1] = population[i + 1];
                }
                break;
            }

            if(firstFound) parents[0] = population[i];
            else if(secondFound) parents[1] = population[i];
        }

        return parents;
    }

    public static NeuralNetwork crossParents(NeuralNetwork[] parents) {

        //creates the child by crossing the parents' weights and biases
        //but which method should we use for crossing them? (take each w and b from parents with 50% chance, cut and mix rows...)
        //todo
        if(parents.length > 2) throw new IllegalArgumentException();

        int inputSize = parents[0].getInputSize();
        Layer[] childLayers = new Layer[parents[0].getLayers().length];

        int sequenceLen = 5; //should be manually adjusted and a divisor of the total number of elements in the weights+biases matrix
        int wbCount = 0;

        for(int layerCount = 0; layerCount < parents[0].getLayers().length; layerCount++) {
            int currentLayerRowLen = parents[0].getLayers()[layerCount].getWeights().numRows();
            int currentLayerColLen = parents[0].getLayers()[layerCount].getWeights().numCols();

            int layerSize = parents[0].getLayers()[layerCount].getLayerSize();
            double[][] childLayerWeights = new double[currentLayerRowLen][currentLayerColLen];
            double[][] childLayerBiases = new double[currentLayerRowLen][1];

            for(int layerRow = 0; layerRow < currentLayerRowLen; layerRow++) {

                for(int layerCol = 0; layerCol < currentLayerColLen + 1; layerCol++) { //+1 je zbog biasa koji se formalno gleda kao 0ti weight ciji je ulaz uvijek 1

                    int i = ((wbCount++) % sequenceLen) % 2;

                    if(layerCol != currentLayerColLen)
                        childLayerWeights[layerRow][layerCol] = parents[i].getLayers()[layerCount].getWeights().get(layerRow, layerCol);
                    else
                        childLayerBiases[layerRow][1] = parents[i].getLayers()[layerCount].getBiases().get(layerRow, 0);
                    
                }
            }

            childLayers[layerCount] = new Layer(layerSize, childLayerWeights, childLayerBiases, parents[0].getLayers()[layerCount].getActivationFunction());
        }

        NeuralNetwork child = new NeuralNetwork(inputSize, childLayers);

        //NetworkUtil.mutate(child, 0.02); //mutation chance is arbitrary but no greater than 0.05

        return child;
    }

    /**
     * Mutates the neural network by changing some elements of layers' weights and biases
     * Mutation is done by summing the current element value and a random number from normal distribution
     *
     * @param neuralNetwork  NeuralNetwork to be mutated
     * @param mutationChance double chance each weight and bias has to be mutated, it is advisable
     *                       that it is not larger than 0.05
     */
    public static void mutate(NeuralNetwork neuralNetwork, double mutationChance) {
        if(neuralNetwork == null) throw new NullPointerException();
        if(mutationChance < 0 || mutationChance > 1)
            throw new IllegalArgumentException("Mutation chance must be in [0, 1], but was " + mutationChance);

        for(Layer l: neuralNetwork.getLayers()) {
            if(!l.isInitialized()) throw new IllegalStateException("Uninitialized layer in neural network!");

            NetworkUtil.mutateMatrixNormalDistribution(l.getWeights(), mutationChance);
            NetworkUtil.mutateMatrixNormalDistribution(l.getBiases(), mutationChance);
        }

    }

    /**
     * Helper method to mutate a matrix
     *
     * @param matrix         {@code double[][]} to be mutated
     * @param mutationChance {@code double} chance each element of the matrix has to be mutated
     */
    protected static void mutateMatrixNormalDistribution(SimpleMatrix matrix, double mutationChance) {
        Random rand = new Random();
        for(int i = 0; i < matrix.numRows(); i++) {
            for(int j = 0; j < matrix.numCols(); j++) {
                if(Math.random() < mutationChance) {
                    matrix.set(i, j, matrix.get(i, j) + rand.nextGaussian());
                }
            }
        }
    }

}
