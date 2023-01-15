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

        for(int i = 0; i < population.length; i++) {
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
            gameInput(unit, game);
            game.step();
        }

        return game.getScore();
    }

    public static void play(NeuralNetwork unit) {
        var game = new GameInterface();
        var gameRender = new DinosaurGame(game);
        gameRender.setVisible(true);

        while(!game.isOver()) {
            gameInput(unit, game);
        }
    }

    private static void gameInput(NeuralNetwork unit, GameInterface game) {
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
        var player = game.getPlayer();
        var enemies = game.getEnemies();

//      sensors[0] = gameSpeed
//      sensors[1] = playerBottomY
//      sensors[2] = playerVerticalSpeed
//      sensors[3] = udaljenost od prvog enemia
//      sensors[4] = prvi enemi dno
//      sensors[5] = prvi enemi visina
//      sensors[6] = prvi enemi sirina
//      sensors[7] = udaljenost prvog i drugog enemia

        var sensors = new double[8];
        sensors[0] = game.getGameSpeed();
        sensors[1] = player.getBottomY();
        sensors[2] = player.getVerticalSpeed();

        var enemiesSize = enemies.size();
        if(enemiesSize == 2) {
            var first = enemies.get(0);
            var second = enemies.get(1);

            if(first.getRightX() < player.getLeftX()) {
                sensors[3] = second.getLeftX() - player.getRightX();
                sensors[4] = second.getBottomY();
                sensors[5] = second.getHeight();
                sensors[6] = second.getWidth();
                sensors[7] = 1 - second.getRightX();
            } else if() {

            } else {
                sensors[3] = first.getLeftX() - player.getRightX();
                sensors[4] = first.getBottomY();
                sensors[5] = first.getHeight();
                sensors[6] = first.getWidth();
                sensors[7] = second.getLeftX() - first.getRightX();
            }
        } else if(enemiesSize == 1) {
            var enemy = enemies.get(0);

            if(enemy.getLeftX() > player.getRightX()) {
                sensors[3] = enemy.getLeftX() - player.getRightX();
                sensors[4] = enemy.getBottomY();
                sensors[5] = enemy.getHeight();
                sensors[6] = enemy.getWidth();
                sensors[7] = 1 - enemy.getRightX();
            } else {
                sensors[3] = 1 - player.getRightX();
                sensors[4] = 0;
                sensors[5] = 0;
                sensors[6] = 0;
                sensors[7] = 0;
            }
        } else if(enemiesSize > 2) {
            var first = enemies.get(0);
            var second = enemies.get(1);
            var third = enemies.get(2);

            if(first.getRightX() < player.getLeftX()) {
                sensors[3] = second.getLeftX() - player.getRightX();
                sensors[4] = second.getBottomY();
                sensors[5] = second.getHeight();
                sensors[6] = second.getWidth();
                sensors[7] = third.getLeftX() - second.getRightX();
            } else {
                sensors[3] = first.getLeftX() - player.getRightX();
                sensors[4] = first.getBottomY();
                sensors[5] = first.getHeight();
                sensors[6] = first.getWidth();
                sensors[7] = second.getLeftX() - first.getRightX();
            }
        } else {
            sensors[3] = 1 - player.getRightX();
            sensors[4] = 0;
            sensors[5] = 0;
            sensors[6] = 0;
            sensors[7] = 0;
        }


        return new SimpleMatrix(8 +, 1, false, sensors);
    }
}
