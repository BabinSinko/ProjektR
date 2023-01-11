package hr.fer.projektr.ai;

import org.ejml.simple.SimpleMatrix;

public class TestiramAIBozePomozi {

    public static void main(String[] args) {

        // ljudi nije mi se dalo pisat testove za ovo pa ak se zelite igrat sam odkomentirate println-ove
        // iznad svakog pise sta bi trebali dobit
        // e da i nije ovo nesto detaljan test di sam provjeravo detaljno matematiku, gledo sam jel okvirno imas smila

        NeuralNetwork neuralNetwork = new NeuralNetwork(
                3,
                new Layer(5, ActivationFunctionAppliers.Sigmoid),
                new Layer(3, ActivationFunctionAppliers.Net)
        );

        // vraca praznu mrezu
        //System.out.println(neuralNetwork);

        //pri svakom pozivu vraca mrezu sa drrugacijim vrijednostima
        neuralNetwork.initializeNetwork();
        //System.out.println(neuralNetwork);

        NeuralNetwork[] testPopulation = new NeuralNetwork[10];
        for (int i = 0; i < testPopulation.length; i++) {
            NeuralNetwork n = new NeuralNetwork(
                    3,
                    new Layer(5, ActivationFunctionAppliers.Sigmoid),
                    new Layer(3, ActivationFunctionAppliers.Net)
            );
            n.initializeNetwork();
            testPopulation[i] = n;
        }
        double[] testFitness = {0, 1, 20, 3, 4, 5, 6, 7, 8, 9};

        // ocekuje 2 jer je taj najbolji
        // System.out.println(NetworkUtil.findBestPlayer(testPopulation, testFitness));

        NeuralNetwork[] parents = NetworkUtil.pickParents(testPopulation, testFitness);
        //ocekuje se ispis 2 razlicita roditelja
        //System.out.println("Parent numero uno: " + parents[0]);
        //System.out.println();
        //System.out.println("Parent numero dou: " + parents[1]);

        //ovo trenutno puca al trebalo bi vratit iskrizano dijete i false, false
        //NeuralNetwork child = NetworkUtil.crossParents(parents);
        //System.out.println(child);
        //System.out.println(child.equals(parents[0]));
        //System.out.println(child.equals(parents[1]));

        // pravit cemo se da je ovo dijete, ovo vraca 2 razlicite mreze jer je sansa mutacije 100 posto
        NeuralNetwork child = parents[0];
        //System.out.println("Dijete prije: " + child);
        //System.out.println();
        NetworkUtil.mutate(child, 1);
        //System.out.println("Dijete poslije: " + child);
    }


}
