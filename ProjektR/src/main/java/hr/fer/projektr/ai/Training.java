package hr.fer.projektr.ai;


import hr.fer.projektr.game.GameSimulator;

import java.util.Random;

public class Training {

    private int populationSize;
    private NeuralNetwork[] population;

    public Training(int populationSize) {
        this.populationSize = populationSize;
        this.population = new NeuralNetwork[populationSize];
    }

    public void train(int numOfIterations, double desiredFitness, int changeSeedInterval) {
        var random = new Random();
        var seed = random.nextLong();

        for (int i = 0; i < populationSize; i++) {
            NeuralNetwork network = new NeuralNetwork(
                    9,
                    new Layer(5, ActivationFunctionAppliers.Sigmoid),
                    new Layer(3, ActivationFunctionAppliers.Net)
            );

            network.initializeNetwork();
            population[i] = network;
        }

        double[] fitness = GameSimulator.simulate(population, seed);

        int currIteration = 0;
        double currBestFitness = fitness[NetworkUtil.findBestPlayer(population, fitness)];

        while(currIteration < numOfIterations && currBestFitness < desiredFitness) {
            if(currIteration % changeSeedInterval == 0) {
                seed = random.nextLong();
            }

            NeuralNetwork[] nextGeneration = new NeuralNetwork[populationSize];
            int bestFitnessInd = NetworkUtil.findBestPlayer(population, fitness);

            nextGeneration[0] = population[bestFitnessInd]; //elitism

            int index = 1;
            while (index < nextGeneration.length) {
                NeuralNetwork[] parents = NetworkUtil.pickParents(population, fitness);
                NeuralNetwork child = NetworkUtil.crossParents(parents);
                NetworkUtil.mutate(child, 0.05);

                //if(NetworkUtil.populationContainsNetwork(nextGeneration, child)) continue;

                nextGeneration[index++] = child;
            }

            population = nextGeneration;
            fitness = GameSimulator.simulate(population, seed);

            currIteration++;
            bestFitnessInd = NetworkUtil.findBestPlayer(population, fitness);
            currBestFitness = fitness[bestFitnessInd];

            if(currIteration % 10 == 0) {
                System.out.println("Treniranje u tijeku: iteracija = " + currIteration + ", high score = " + currBestFitness);
            }
        }

        System.out.println();
        System.out.println("Treniranje završilo:");
        System.out.println("iteracija = " + currIteration);
        System.out.println("high score = " + currBestFitness);


        var bestPlayer = population[NetworkUtil.findBestPlayer(population, fitness)];
        //todo OVDJE: Dodati da fja zapisuje bytecod objekta u neki text file ili nesto

        GameSimulator.play(bestPlayer, seed);
    }
}


