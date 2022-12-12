package hr.fer.projektr.game;

public class Cactus extends Enemy{
    public Cactus() {
        this(GameState.INITIAL_CACTUS_WIDTH, GameState.INITIAL_CACTUS_HEIGHT);
    }

    public Cactus(double width, double height){
        super(GameState.INITIAL_CACTUS_POSITION_X, GameState.INITIAL_CACTUS_POSITION_Y, width, height);
    }
}
