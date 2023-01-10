package hr.fer.projektr.ai;

import java.util.Arrays;
import java.util.Random;

/**
 * Class representing a single layer of neural network
 * Each Layer has a matrix of weights and biases and an activation function
 */
public class Layer {

    private int layerSize;
    private ActivationFunction activationFunction;
    private double[][] weights;
    private double[][] biases;
    private boolean isInitialized;

    /**
     * Constructor
     * @param layerSize int number of neurons in the layer
     * @param activationFunction ActivationFunction to be applied in neurons of this layer
     */
    public Layer(int layerSize, ActivationFunction activationFunction) {
        if (layerSize < 1) throw new IllegalArgumentException("layerSize can't be less than 1");

        this.layerSize = layerSize;
        this.activationFunction = activationFunction;
        this.isInitialized = false;
    }

    public Layer(int layerSize, double[][] weights, double[][] biases, ActivationFunction activationFunction){
        if (layerSize < 1) throw new IllegalArgumentException("layerSize can't be less than 1");

        this.layerSize = layerSize;
        this.weights = weights;
        this.biases = biases;
        this.activationFunction = activationFunction;
        this.isInitialized = true;
    }

    /**
     * Returns the number of neurons in the layer
     * @return int number of neurons
     */
    public int getLayerSize() {
        return layerSize;
    }

    public ActivationFunction getActivationFunction() {
        return activationFunction;
    }

    public double[][] getWeights() {
        return weights;
    }

    public double[][] getBiases() {
        return biases;
    }

    /**
     * Confirms if the layer has been initialized by neural network
     * @return boolean true if layer is initialized, false otherwise
     */
    public boolean isInitialized() {
        return isInitialized;
    }

    /**
     * Fill weights and biases matrices with random values from uniform distribution in [-2.4/n, 2.4/n]
     * where n is the number of inputs to the layer (prevLayerSize)
     * @param prevLayerSize int number of inputs to this layer
     */
    public void initializeLayer(int prevLayerSize) {
        if (prevLayerSize < 1) throw new IllegalArgumentException("PrevLayerSize can't be less than 1");

        weights = new double[layerSize][prevLayerSize];
        biases = new double[layerSize][1];

        initializeMatrix(weights, prevLayerSize);
        initializeMatrix(biases, prevLayerSize);

        isInitialized = true;
    }

    /**
     * Helper method to initialize matrix random values from uniform distribution in [-2.4/n, 2.4/n]
     * @param matrix double[][]
     * @param prevLayerSize int number of inputs to this layer
     */
    private void initializeMatrix(double[][] matrix, int prevLayerSize) {
        Random rand = new Random();
        double max = 2.4 / prevLayerSize;
        double min = - max;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; i++) {
                matrix[i][j] = rand.nextDouble(max - min + 1) + min;
            }
        }
    }

    @Override
    public String toString() {
        return "Layer{" +
                "weights=" + Arrays.toString(weights) +
                ", biases=" + Arrays.toString(biases) +
                '}';
    }
}
