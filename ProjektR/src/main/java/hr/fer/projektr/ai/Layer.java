package hr.fer.projektr.ai;

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


    void initializeLayer(int prevLayerSize) {
        weights = new double[layerSize][prevLayerSize];
        biases = new double[layerSize][1];

        //todo
        //fill both with random values from uniform distribution in [-2.4/n, 2.4/n]
        // where n is the number of inputs to the layer (prevLayerSize)

        isInitialized = true;
    }

}
