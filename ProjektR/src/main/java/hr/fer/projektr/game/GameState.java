package hr.fer.projektr.game;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    public static final double INITIAL_PLAYER_POSITION_X = 0;
    public static final double INITIAL_PLAYER_POSITION_Y = 1;
    public static final double INITIAL_PLAYER_WIDTH = 0.1;
    public static final double INITIAL_PLAYER_HEIGHT = 0.2;

    public static final double INITIAL_CACTUS_POSITION_X = 1;
    public static final double INITIAL_CACTUS_POSITION_Y = 1;
    public static final double INITIAL_CACTUS_WIDTH = 0.1;
    public static final double INITIAL_CACTUS_HEIGHT = 0.2;

    public static final double INITIAL_BIRD_POSITION_X = 1;
    public static final double INITIAL_BIRD_POSITION_Y = 0;
    public static final double INITIAL_BIRD_WIDTH = 0.1;
    public static final double INITIAL_BIRD_HEIGHT = 0.1;

    public static final double GRAVITY = 10;
    public static final double INITIAL_GAME_SPEED = 1;

    private final Player player;
    private final List<Enemy> enemies;
    private double gameSpeed;
    private long score;

    public GameState() {
        this.player = new Player();
        this.enemies = new ArrayList<>();
        this.gameSpeed = INITIAL_GAME_SPEED;
    }

    public void step(){
        //TODO
    }

    public void influencePlayer(boolean jump, boolean duck){
        //TODO
    }
}
