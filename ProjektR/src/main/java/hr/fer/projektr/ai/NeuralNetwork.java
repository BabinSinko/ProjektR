package hr.fer.projektr.ai;

/**
 * Class representing a neural network
 */
public class NeuralNetwork {
    private int inputSize;
    private Layer[] layers;
    /**
     * Constructor
     * @param inputSize int Number of inputs to the network
     * @param layers Layer[] containing hidden layers and output layer
     */
    NeuralNetwork(int inputSize, Layer... layers) {
        this.inputSize = inputSize;
        this.layers = layers;
    };

    /**
     * inputSize getter
     * @return int number of inputs to the network
     */
    public int getInputSize() {
        return inputSize;
    }

    /**
     * layers getter
     * @return Layer[] all the layers of the network
     */
    public Layer[] getLayers() {
        return layers;
    }

    /**
     * Initializes the network by calling {@code initializeLayer} on every layer
     */
    void initializeNetwork() {
        layers[0].initializeLayer(inputSize);

        for(int i = 1; i < layers.length; i++) {
            layers[i].initializeLayer(layers[i-1].getLayerSize());
        }
    }

    /**
     * Computes the forward propagation of the neural network
     * @param input double[][1] vector of doubles representing the input for which to calculate forward propagation
     * @return int index of output layer neuron with highest value (the decision based on input)
     */
    public int computeForwardProp(double[][] input) {

        //todo
        //our input will be double[3][1]
        //first component tells the height of the object in front, second the distance to it, third the current speed of the game
        //once again this could be a double[], depending on the matrix multiplication library support
        //same logic will be applied for the rest of the document

        double[][] prevLayerOutput = input;
        double[][] layerOutput;

        for(Layer l : layers) {
            layerOutput = f(l.getWeights() * prevLayerOutput + l.getBiases());    //where f is layer.activationFunction that will
                                                                                //be applied to every element of output matrix

            prevLayerOutput = layerOutput //just keep in mind these might not be the same size
        }

        return "index of layerOutput component which has the highest value";
    }

}
