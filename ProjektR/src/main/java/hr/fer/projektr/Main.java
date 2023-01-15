package hr.fer.projektr;

import hr.fer.projektr.ai.Training;

public class Main {
    public static void main(String[] args) {
        new Training(50).train(1_000, 2500, 50);
    }
}