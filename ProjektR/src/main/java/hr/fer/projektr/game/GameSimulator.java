package hr.fer.projektr.game;

import hr.fer.projektr.ai.NeuralNetwork;
import hr.fer.projektr.ui.DinosaurGame;
import org.ejml.simple.SimpleMatrix;

public class GameSimulator {

    private static final long DEFAULT_SEED = 3;

    public static double[] simulate(NeuralNetwork[] population) {
        return simulate(population, DEFAULT_SEED);
    }

    public static double[] simulate(NeuralNetwork[] population, long seed) {
        var fitness = new double[population.length];

        for(int i = 0; i < population.length ; i++) {
            fitness[i] = simulate(population[i], seed);
        }

        return fitness;
    }

    public static double simulate(NeuralNetwork unit) {
        return simulate(unit, DEFAULT_SEED);
    }

    public static double simulate(NeuralNetwork unit, long seed) {
        var game = new GameInterface();
        game.start(seed);

        while(!game.isOver()) {
            gameStep(unit, game);
        }

        return game.getScore();
    }

    public static void play(NeuralNetwork unit) {
        var game = new GameInterface();
        var gameRender = new DinosaurGame(game);
        gameRender.setVisible(true);

        while(!game.isOver()) {
            gameStep(unit, game);
        }
    }

    private static void gameStep(NeuralNetwork unit, GameInterface game) {
        var input = getInputMatrix(game);
        var decision = unit.computeForwardProp(input);
        switch(decision) {
            case 0 -> game.input(false, false);
            case 1 -> game.input(false, true);
            case 2 -> game.input(true, false);
            default -> throw new UnsupportedOperationException("Output value must be 0, 1 or 2");
        }
    }

    private static SimpleMatrix getInputMatrix(GameInterface game) {
        // TODO: Ucitaj input - ovo je vjerojatno najteze
        var sensors = new double[7];

        return new SimpleMatrix(7, 1, false, sensors);
    }
}
