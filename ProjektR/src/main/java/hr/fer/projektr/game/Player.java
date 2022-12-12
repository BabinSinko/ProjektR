package hr.fer.projektr.game;

public class Player extends Entity{
    private boolean isJumping;
    private boolean isDucking;
    private double verticalSpeed;
    public Player() {
        super(GameState.INITIAL_PLAYER_POSITION_X, GameState.INITIAL_PLAYER_POSITION_Y, GameState.INITIAL_PLAYER_WIDTH, GameState.INITIAL_PLAYER_HEIGHT);
    }
}
