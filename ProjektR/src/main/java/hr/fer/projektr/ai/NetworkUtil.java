package hr.fer.projektr.ai;

import java.util.Random;

public class NetworkUtil {
    public static NeuralNetwork[] pickParents(NeuralNetwork[] population, double[] fitness) {
        NeuralNetwork[] parents = new NeuralNetwork[2];

        //method that picks 2 different parents from population using proportionate selection:
        //probability(i) = fitness(i) / sum(every fitness)
        //todo
    
        return parents;
    }

    public static NeuralNetwork crossParents(NeuralNetwork[] parents); {

        //creates the child by crossing the parents' weights and biases
        //but which method should we use for crossing them? (take each w and b from parents with 50% chance, cut and mix rows...)
        //todo
    
        return child;
    }

    /**
     * Mutates the neural network by changing some elements of layers' weights and biases
     * @param neuralNetwork NeuralNetwork to be mutated
     * @param mutationChance double change each weight and bias has to be mutated, it is advisable
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
    protected static void mutateMatrixNormalDistribution(double[][] matrix, double mutationChance) {
        Random rand = new Random();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (Math.random() < mutationChance) {
                    matrix[i][j] += rand.nextGaussian();
                }
            }
        }
    }

}
