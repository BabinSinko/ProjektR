package hr.fer.projektr.ai;

//todo

public class Training {
    private int populationSize;
    private NeuralNetwork[] population;


    public Training(int populationSize) {
        this.populationSize = populationSize;
        this.population = new NeuralNetwork[populationSize];
    }

    public void train() {
        for (int i = 0; i < populationSize; i++) {
            NeuralNetwork network = new NeuralNetwork(
                    3,
                    new Layer(5, ActivationFunctionAppliers.Sigmoid),
                    new Layer(3, ActivationFunctionAppliers.Net) // 3 output neurons because dino can jump/duck/do nothing
            );

            network.initializeNetwork();
            population[i] = network;
        }

        double[] fitness = game.play(population); //game will return how well each unit did


        while("number of iterations is reached or highest fitness reaches desired score") {
            NeuralNetwork[] nextGeneration = new NeuralNetwork[populationSize];

            children[0] = population["parent with the highest score"] //elitism

            while(children.length < populationSize) {
                NeuralNetwork[] parents = NetworkUtil.pickParents(population, fitness);
                NeuralNetwork[] children = NetworkUtil.crossParents(parents);
                nextGeneration[i] = NetworkUtil.mutate(children[0]);    // but only if such child doesn't already exist in next gen
                nextGeneration[i + 1] = NetworkUtil.mutate(children[1]);

                population = nextGeneration;
                fitness = game.play(population);
            }
        }
    }
}
