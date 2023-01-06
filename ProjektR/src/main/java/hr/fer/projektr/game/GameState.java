package hr.fer.projektr.game;

import hr.fer.projektr.game.entities.Enemy;
import hr.fer.projektr.game.entities.Player;
import hr.fer.projektr.game.generators.Generator;
import hr.fer.projektr.game.utility.Physics;

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
    public static final double PLAYER_WIDTH = 0.07;
    public static final double PLAYER_HEIGHT = 0.2;
    public static final double PLAYER_CROUCH_HEIGHT = 0.07;

    public static final double INITIAL_JUMP_SPEED = -1.5;


    //Constants relating to the cactus enemies
    public static final double INITIAL_CACTUS_POSITION_X = 1;
    public static final double INITIAL_CACTUS_POSITION_Y = 1;
    public static final double STANDARD_CACTUS_WIDTH = 0.05;
    public static final double STANDARD_CACTUS_HEIGHT = 0.1;
    public static final double SMALL_CACTUS_WIDTH = 0.025;
    public static final double SMALL_CACTUS_HEIGHT = 0.075;
    public static final double LARGE_CACTUS_WIDTH = 0.12;
    public static final double LARGE_CACTUS_HEIGHT = 0.15;
    public static final double LONG_CACTUS_WIDTH = 0.05;
    public static final double LONG_CACTUS_HEIGHT = 0.5;

    //Constants relating to the bird enemies
    public static final double INITIAL_BIRD_POSITION_X = 1;
    public static final double DEFAULT_INITIAL_BIRD_POSITION_Y = 0;
    public static final double BIRD_WIDTH = 0.15;
    public static final double BIRD_HEIGHT = 0.1;
    public static final double MIN_BIRD_Y = 1-GameState.PLAYER_CROUCH_HEIGHT-GameState.BIRD_HEIGHT;
    public static final double MAX_BIRD_Y = 1-0.5*GameState.INITIAL_JUMP_SPEED*GameState.INITIAL_JUMP_SPEED/GameState.GRAVITY;
    public static final double CENTER_BIRD_Y = (MIN_BIRD_Y+MAX_BIRD_Y)/2;


    //Constants relating to the game world
    public static final double GRAVITY = 2.;
    public static final double INITIAL_GAME_SPEED = 0.2;
    public static final int SPEED_INCREASE_SCORE_THRESHOLD = 100;
    public static final double SPEED_INCREASE_AMOUNT = 0.01;
    public static final double SCORE_TO_DISTANCE_RAN_RATIO = 6.;
    public final double STEP_DURATION;


    /**
     * The player in this game state
     */
    private Player player;

    /**
     * Present enemies in this game state
     */
    private List<Enemy> enemies;

    /**
     * The current game speed (how fast is everything moving)
     */
    private double gameSpeed;

    /**
     * The current score
     */
    private double distanceRan;

    private boolean isRunning;

    private Generator generator;

    /**
     * Constructor for the game state, initializes everything by itself.
     */

    public GameState(double stepDuration) {
        this.STEP_DURATION = stepDuration;
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
        if (!isRunning){
            return;
        }

        Physics.playerUpdate(this);
        Physics.moveEnemies(this);

        if (Physics.checkCollisions(this)){
            isRunning = false;
        }

        generator.updateList();
        int scoreBefore = this.getScore();
        distanceRan += gameSpeed * STEP_DURATION;
        if (this.getScore() > scoreBefore && this.getScore() % SPEED_INCREASE_SCORE_THRESHOLD == 0){
            gameSpeed += SPEED_INCREASE_AMOUNT;
        }
        System.out.println(gameSpeed);
    }

    public void start(){
        this.player = new Player();
        this.enemies = new ArrayList<>();
        this.gameSpeed = INITIAL_GAME_SPEED;
        this.generator = new Generator(this);
        this.distanceRan = 0.;
        this.isRunning = true;
    }
    public boolean isOver(){
        return isRunning;
    }

    public int getScore() {
        return (int) (distanceRan * SCORE_TO_DISTANCE_RAN_RATIO);
    }
}
