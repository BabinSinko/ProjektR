package hr.fer.projektr.game;

import hr.fer.projektr.game.entities.Enemy;
import hr.fer.projektr.game.entities.Entity;
import hr.fer.projektr.game.entities.Player;
import hr.fer.projektr.game.generators.Generator;
import hr.fer.projektr.game.utility.Physics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
    public static final double PLAYER_CROUCH_HEIGHT = 0.07;

    public static final double INITIAL_JUMP_SPEED = -1.5;


    //Constants relating to the cactus enemies
    public static final double INITIAL_CACTUS_POSITION_X = 1;
    public static final double INITIAL_CACTUS_POSITION_Y = 1;
    public static final double STANDARD_CACTUS_WIDTH = 0.1;
    public static final double STANDARD_CACTUS_HEIGHT = 0.2;
    public static final double SMALL_CACTUS_WIDTH = 0.05;
    public static final double SMALL_CACTUS_HEIGHT = 0.15;
    public static final double LARGE_CACTUS_WIDTH = 0.3;
    public static final double LARGE_CACTUS_HEIGHT = 0.25;

    //Constants relating to the bird enemies
    public static final double INITIAL_BIRD_POSITION_X = 1;
    public static final double DEFAULT_INITIAL_BIRD_POSITION_Y = 0;
    public static final double BIRD_WIDTH = 0.1;
    public static final double BIRD_HEIGHT = 0.1;

    //Constants relating to the game world
    public static final double GRAVITY = 2.;
    public static final double INITIAL_GAME_SPEED = 2;
    public static final double STEP_DURATION = 0.01666666666;


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

    private Generator generator;

    /**
     * Constructor for the game state, initializes everything by itself.
     */
    public GameState() {
        this.player = new Player();
        this.enemies = new ArrayList<>();
        this.gameSpeed = INITIAL_GAME_SPEED;
        this.generator = new Generator(this);
    }

    public Player getPlayer() {
    	return player;
    }
    
    public List<Enemy> getEnemies(){
        return enemies;
    }

    public void removeEnemy(int k){
        enemies.remove(k);
    }

    public void addEnemy(Enemy enemy){
        enemies.add(enemy);
    }

    /**
     * za svrhe testiranja, ne znam koliko ce biti potrebno kasnije
     * @return brzinu igre
     */
    public double getSpeed(){
        return gameSpeed;
    }


    /**
     * Runs a single step of the game state simulation.
     * For example moves the enemies, deals with the player movement and checks for collisions with the player.
     */
    public void step(){
        //TODO pokretat generator, pozivat physics za entitete i provjeravati kolizije
        Physics.playerUpdate(player);
        Physics.moveEnemies(enemies, gameSpeed);
        generator.updateList();
        System.out.println(enemies);
    }

    //TODO
    public boolean isOver(){
        return false;
    }
}
