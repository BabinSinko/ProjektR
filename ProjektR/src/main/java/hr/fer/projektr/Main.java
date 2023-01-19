package hr.fer.projektr;

import hr.fer.projektr.ai.Training;

public class Main {
    public static void main(String[] args) {
        //todo dodati da ako mu predamo bytecode da pogrece simulaciju, inace trenira
        new Training(60).train(200, 5000, 5);
    }
}