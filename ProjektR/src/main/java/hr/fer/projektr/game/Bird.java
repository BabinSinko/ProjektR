package hr.fer.projektr.game;

public class Bird extends Enemy{
    public Bird() {
        this(GameState.INITIAL_BIRD_POSITION_Y);
    }

    public Bird(double positionY){
        super(GameState.INITIAL_BIRD_POSITION_X, positionY, GameState.INITIAL_BIRD_WIDTH, GameState.INITIAL_BIRD_HEIGHT);
    }
}
