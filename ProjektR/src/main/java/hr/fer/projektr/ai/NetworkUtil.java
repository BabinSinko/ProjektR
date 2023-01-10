package hr.fer.projektr.ai;

import org.ejml.simple.SimpleMatrix;

import java.util.Random;

public class NetworkUtil {
    public static NeuralNetwork[] pickParents(NeuralNetwork[] population, double[] fitness) {
        NeuralNetwork[] parents = new NeuralNetwork[2];

        //method that picks 2 different parents from population using proportionate selection:
        //probability(i) = fitness(i) / sum(every fitness)
        //todo
    
        return parents;
    }

    public static NeuralNetwork crossParents(NeuralNetwork[] parents) {

        //creates the child by crossing the parents' weights and biases
        //but which method should we use for crossing them? (take each w and b from parents with 50% chance, cut and mix rows...)
        //todo
        if (parents.length > 2) throw new IllegalArgumentException();

        int inputSize = parents[0].getInputSize();
        Layer[] childLayers = new Layer[parents[0].getLayers().length];

        int sequenceLen = 5; //should be manually adjusted and a divisor of the total number of elements in the weights+biases matrix

        
        for(int layerCount = 0; layerCount < parents[0].getLayers().length; layerCount++){
            int currentLayerRowLen = parents[0].getLayers()[layerCount].getWeights().numRows();
            int currentLayerColLen =  parents[0].getLayers()[layerCount].getWeights().numCols();

            int layerSize = parents[0].getLayers()[layerCount].getLayerSize();
            double[][] childLayerWeights = new double[currentLayerRowLen][currentLayerColLen];
            double[][] childLayerBiases = new double[currentLayerRowLen][1];

            for(int layerRow = 0; layerRow < currentLayerRowLen; layerRow++){ 
                
                for(int layerCol = 0; layerCol < currentLayerColLen + 1; layerCol++){ //+1 je zbog biasa koji se formalno gleda kao 0ti weight ciji je ulaz uvijek 1
                    /* 
                    if(((currentLayerColLen * layerRow + layerCol) % sequenceLen) % 2 == 0){
                        if(layerCol != currentLayerColLen)
                            childLayerWeights[layerRow][layerCol] = parents[0].getLayers()[layerCount].getWeights()[layerRow][layerCol];
                        else
                            childLayerBiases[layerRow][1] = parents[0].getLayers()[layerCount].getBiases()[layerRow][layerCol];
                    } else {
                        if(layerCol != currentLayerColLen)
                            childLayerWeights[layerRow][layerCol] = parents[1].getLayers()[layerCount].getWeights()[layerRow][layerCol];
                        else
                            childLayerBiases[layerRow][1] = parents[1].getLayers()[layerCount].getBiases()[layerRow][layerCol];
                    }
                    */

                    int i = ((currentLayerColLen * layerRow + layerCol) % sequenceLen) % 2;

                    if(layerCol != currentLayerColLen)
                        childLayerWeights[layerRow][layerCol] = parents[i].getLayers()[layerCount].getWeights()[layerRow][layerCol];
                    else
                        childLayerBiases[layerRow][1] = parents[i].getLayers()[layerCount].getBiases()[layerRow][layerCol];
                
                }
            }

            childLayers[layerCount] = new Layer(layerSize , childLayerWeights, childLayerBiases, ActivationFunctionApplier);
        }

        NeuralNetwork child = new NeuralNetwork(inputSize, childLayers);

        //NetworkUtil.mutate(child, 0.02); //mutation chance is arbitrary but no greater than 0.05
        
        return child;
    }

    /**
     * Mutates the neural network by changing some elements of layers' weights and biases
     * @param neuralNetwork NeuralNetwork to be mutated
     * @param mutationChance double chance each weight and bias has to be mutated, it is advisable
     *                       that it is not larger than 0.05
     */
    public static void mutate(NeuralNetwork neuralNetwork, double mutationChance) {
        if (neuralNetwork == null) throw new NullPointerException();
        if (mutationChance < 0 || mutationChance > 1)
            throw new IllegalArgumentException("Mutation chance must be in [0, 1], but was " + mutationChance);

        for (Layer l : neuralNetwork.getLayers()) {
            if (!l.isInitialized()) throw new IllegalStateException("Uninitialized layer in neural network!");

            NetworkUtil.mutateMatrixNormalDistribution(l.getWeights(), mutationChance);
            NetworkUtil.mutateMatrixNormalDistribution(l.getBiases(), mutationChance);
        }

    }

    /**
     * Helper method to mutate a matrix
     * @param matrix {@code double[][]} to be mutated
     * @param mutationChance {@code double} chance each element of the matrix has to be mutated
     */
    protected static void mutateMatrixNormalDistribution(SimpleMatrix matrix, double mutationChance) {
        Random rand = new Random();
        for (int i = 0; i < matrix.numRows(); i++) {
            for (int j = 0; j < matrix.numCols(); j++) {
                if (Math.random() < mutationChance) {
                    matrix.set(i, j, matrix.get(i, j) + rand.nextGaussian());
                }
            }
        }
    }

}
