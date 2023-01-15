package hr.fer.projektr.ai;


import hr.fer.projektr.game.GameSimulator;

public class Training {

    private int populationSize;
    private NeuralNetwork[] population;

    public Training(int populationSize) {
        this.populationSize = populationSize;
        this.population = new NeuralNetwork[populationSize];
    }

    public void train(int numOfIterations, double desiredFitness) {
        for (int i = 0; i < populationSize; i++) {
            NeuralNetwork network = new NeuralNetwork(
                    9,
                    new Layer(5, ActivationFunctionAppliers.Sigmoid),
                    new Layer(3, ActivationFunctionAppliers.Net)
            );

            network.initializeNetwork();
            population[i] = network;
        }

        double[] fitness = GameSimulator.simulate(population);

        int currIteration = 0;
        double currBestFitness = fitness[NetworkUtil.findBestPlayer(population, fitness)];

        while(currIteration < numOfIterations || currBestFitness < desiredFitness) {
            NeuralNetwork[] nextGeneration = new NeuralNetwork[populationSize];
            int bestFitnessInd = NetworkUtil.findBestPlayer(population, fitness);

            nextGeneration[0] = population[bestFitnessInd]; //elitism

            for (int i = 1; i < populationSize; i++) {
                NeuralNetwork[] parents = NetworkUtil.pickParents(population, fitness);
                NeuralNetwork child = NetworkUtil.crossParents(parents);
                NetworkUtil.mutate(child, 0.05);
                nextGeneration[i] = child;    // but only if such child doesn't already exist in next gen
            }

            population = nextGeneration;
            fitness = GameSimulator.simulate(population);

            currIteration++;
            currBestFitness = fitness[bestFitnessInd];
        }

        System.out.println("Treniranje završilo:");
        System.out.println("iteracija = " + currIteration);
        System.out.println("high score = " + currBestFitness);

        GameSimulator.play(population[NetworkUtil.findBestPlayer(population, fitness)]);
    }
}


