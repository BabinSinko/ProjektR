package hr.fer.projektr.ai;

public class NetworkUtil {
    static NeuralNetwork[] pickParents(NeuralNetwork[] population, double[] fitness) {
        //method that picks 2 different parents from population using proportionate selection:
        //probability(i) = fitness(i) / sum(every fitness)
    
        return NeuralNetwork[] parents;
    }
    
    static NeuralNetwork[] crossParents(parents); {
        //creates 2 different children by crossing the parents' weights and biases
        //but which method should we use for crossing them? (take each w and b from parents with 50% chance, cut and mix rows...)
    
        return NeuralNetwork[] children;
    }
    
    static NeuralNetwork mutate(NeuralNetwork neuralNetwork) {
        //each weight and bias has a MUTATION_FACTOR(static constant) chance of being mutated
        //but by how much should we mutate them?
    
        return mutatedNeuralNetwork;
    }
}
