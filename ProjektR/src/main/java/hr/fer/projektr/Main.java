package hr.fer.projektr;

import hr.fer.projektr.ai.Training;

public class Main {
    public static void main(String[] args) {
        new Training(100).train(10_000, 1000);
    }
}