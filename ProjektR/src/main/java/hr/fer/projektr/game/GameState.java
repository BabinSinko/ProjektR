package hr.fer.projektr.game;

import hr.fer.projektr.game.entities.Enemy;
import hr.fer.projektr.game.entities.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a game state.
 * Completely separate from a way of playing the game.
 * Holds information about entities (player and present enemies), default values and other miscellaneous info.
 */
public class GameState {
    //Constants relating to the player
    public static final double INITIAL_PLAYER_POSITION_X = 0;
    public static final double INITIAL_PLAYER_POSITION_Y = 1;
    public static final double PLAYER_WIDTH = 0.1;
    public static final double PLAYER_HEIGHT = 0.2;

    //Constants relating to the cactus enemies
    public static final double INITIAL_CACTUS_POSITION_X = 1;
    public static final double INITIAL_CACTUS_POSITION_Y = 1;
    public static final double STANDARD_CACTUS_WIDTH = 0.1;
    public static final double STANDARD_CACTUS_HEIGHT = 0.2;

    //Constants relating to the bird enemies
    public static final double INITIAL_BIRD_POSITION_X = 1;
    public static final double DEFAULT_INITIAL_BIRD_POSITION_Y = 0;
    public static final double BIRD_WIDTH = 0.1;
    public static final double BIRD_HEIGHT = 0.1;

    //Constants relating to the game world
    public static final double GRAVITY = 10;
    public static final double INITIAL_GAME_SPEED = 1;

    /**
     * The player in this game state
     */
    private final Player player;

    /**
     * Present enemies in this game state
     */
    private final List<Enemy> enemies;

    /**
     * The current game speed (how fast is everything moving)
     */
    private double gameSpeed;

    /**
     * The current score
     */
    private long score;

    /**
     * Constructor for the game state, initializes everything by itself.
     */
    public GameState() {
        this.player = new Player();
        this.enemies = new ArrayList<>();
        this.gameSpeed = INITIAL_GAME_SPEED;
    }

    /**
     * Runs a single step of the game state simulation.
     * For example moves the enemies, deals with the player movement and checks for collisions with the player.
     */
    public void step(){
        //TODO
    }

    /**
     * Stand-in function for controlling the player
     * @param jump the player wants to jump if true
     * @param duck the player wants to duck if true
     */
    public void influencePlayer(boolean jump, boolean duck){
        //TODO
    }
}
