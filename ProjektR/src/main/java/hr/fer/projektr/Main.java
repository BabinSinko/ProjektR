package hr.fer.projektr;

import hr.fer.projektr.ai.Layer;
import hr.fer.projektr.ai.NeuralNetwork;
import hr.fer.projektr.ai.Training;
import hr.fer.projektr.game.GameSimulator;
import hr.fer.projektr.ui.DinosaurGame;
import org.ejml.simple.SimpleMatrix;

import javax.management.OperationsException;
import javax.swing.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws OperationsException {
        var scanner = new Scanner(System.in);

        System.out.println("AI training (1)");
        System.out.println("Load AI from AI.ser (2)");
        System.out.println("Play the game (3)");
        System.out.print("Choose activity: ");
        var input = Integer.parseInt(scanner.nextLine());
        System.out.println();

        if(input == 1) {
            System.out.print("Input population size: "); var populationSize = scanner.nextInt();
            System.out.print("Input maximum number of iterations: "); var numOfIterations = scanner.nextInt();
            System.out.print("Input desired fitness size: "); var desiredFitness = scanner.nextDouble();
            System.out.print("Input seed change interval size: "); var seedChangeInterval = scanner.nextInt();
            System.out.print("Input mutation chance: "); var mutationChance = scanner.nextDouble();
            System.out.println();

            new Training(populationSize).train(numOfIterations, desiredFitness, seedChangeInterval, mutationChance);
        }
        else if(input == 2) {
            NeuralNetwork unit = null;
            double fitness;
            try {
                InputStream fileInputStream = Main.class.getClassLoader().getResourceAsStream("AI.ser");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                unit = (NeuralNetwork) objectInputStream.readObject();
                fitness = (double) objectInputStream.readObject();
                fileInputStream.close();
                objectInputStream.close();
            }
            catch(Exception e) {
                throw new OperationsException("File could not be loaded.");
            }

            for(Layer layer: unit.getLayers()) {
                layer.setBiases(new SimpleMatrix(layer.getBiases()));
                layer.setWeights(new SimpleMatrix(layer.getWeights()));
            }

            System.out.print("Starting game with a unit of " + fitness + " fitness.");
            GameSimulator.play(unit);
        } else if (input == 3) {
            SwingUtilities.invokeLater(() -> {
                JFrame frame = new DinosaurGame();
                frame.setVisible(true);
            });
        }
    }
}