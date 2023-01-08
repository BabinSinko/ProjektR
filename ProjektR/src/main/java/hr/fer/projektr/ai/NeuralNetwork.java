public class NeuralNetwork {
    int inputSize; //input layer size
    Layer[] layers; //hidden (and output) layers

    //constructor
    NeuralNetwork(int inputSize, Layer... layers);

    void initializeNetwork() {
        layers[0].initializeLayer(inputSize);

        for(int i = 1, i < layers.length, i++) {
            layers[i].initializeLayer(layers[i-1].layerSize);
        }
    }

    int computeForwardProp(double[][] input) {
        //our input will be double[3][1]
        //first component tells the height of the object in front, second the distance to it, third the current speed of the game
        //once again this could be a double[], depending on the matrix multiplication library support
        //same logic will be applied for the rest of the document

        double[][] prevLayerOutput = input
        double[][] layerOutput;

        for(layer : layers) {
            layerOutput = f(layer.weights * prevLayerOutput + layer.biases);    //where f is layer.activationFunction that will
                                                                                //be applied to every element of output matrix

            prevLayerOutput = layerOutput //just keep in mind these might not be the same size
        }

        return "index of layerOutput component which has the highest value"
    }

}
