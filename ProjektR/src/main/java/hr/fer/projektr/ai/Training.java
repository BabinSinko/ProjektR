package hr.fer.projektr.ai;

public class Training {
    NeuralNetwork[] population = new NeuralNetwork[POPULATION_SIZE];

    for (i = 0; i < POPULATION_SIZE; i++) {
        NeuralNetwork network = new NeuralNetwork(
                        3,
                        new Layer(5, ActivationFunction.SIGMOID),
                        new Layer(3, ActivationFunction.NET), // 3 output neurons because dino can jump/duck/do nothing
                    );

        network.initializeNetwork();
        population[i] = network;
    }

    double[] fitness = game.play(population); //game will return how well each unit did


    while("number of iterations is reached or highest fitness reaches desired score") {
        NeuralNetwork[] nextGeneration = new NeuralNetwork[POPULATION_SIZE];

        children[0] = population["parent with the highest score"] //elitism

        while(children.length < POPULATION_SIZE) {
            NeuralNetwork[] parents = NetworkUtil.pickParents(population, fitness);
            NeuralNetwork[] children = NetworkUtil.crossParents(parents);
            nextGeneration[i] = NetworkUtil.mutate(children[0]);    // but only if such child doesn't already exist in next gen
            nextGeneration[i + 1] = NetworkUtil.mutate(children[1]);

            population = nextGeneration;
            fitness = game.play(population);
        }
    }
}
