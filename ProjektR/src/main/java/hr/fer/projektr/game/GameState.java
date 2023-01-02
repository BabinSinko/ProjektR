package hr.fer.projektr.game;

import hr.fer.projektr.game.entities.Enemy;
import hr.fer.projektr.game.entities.Entity;
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
    public static final double GRAVITY = 10;
    public static final double INITIAL_GAME_SPEED = 0.05;

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

    public Entity getPlayer() {
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
        //TODO
    	if (player.isJumping()) {
    		updateJump();
    	}

	//procitati inpute jump i/ili duck
    	
//    	//Izracunati novu Y koordinatu player-a
//    	double positionY = player.getPositionY();
//    	if((player.isJumping() == true && (positionY < 1.000001 && positionY > 0.9999999)) || 
//    			positionY <= 0.9999999) {
//    		
//    		//konstante namjestati, gravitacija najvise ovisi o frameratu zbog kvadrata, paziti na to
//    		time += 0.1;
//    			
//    		positionY = positionY + player.getVerticalSpeed() * time + GRAVITY * time * time;
//    			
//    		if(positionY >= 0.99) {
//    			positionY = 1;
//	    		time = 0;
//    		}
//    			
//    		player.setPositionY(positionY);
//    			
//    		/*
//    		//printove zamijeniti s iscrtavanjem
//    		System.out.println("PositionY: " + positionY);
//    		System.out.println("t: " + time);
//    		*/
//    		
//	}
    	
    	//Izracunati X koordinatu enemies-a
    		
    	//Provjera kolizije
    	
    	//Iscrtavanje ekrana

        /*
         for (enemy:enemies){
            moveEnemyX(enemy, gameSpeed);
         }
         if (player.isJumping()){
           if (pozicija >= GRANICA_Y) {
             pozicija = GRANICA_Y
            }
            else {
                calculateVerticalVelocity;
                movePlayerY(player, player.getVelocity());
            }

          }
         */
    }

    //ISTO KAO I influencePlayer
    public void jump() {
    	if (!player.isJumping()) {
	    	player.setJumping(true);
	    	player.setVerticalSpeed(-0.05);
	    	player.setPositionY(player.getPositionY() - 0.05);
    	}
    }
    //ISTO KAO I influencePlayer
    public void duck() {
    	
    }
   
    private void updateJump() {
	    if (player.getPositionY() >= 1) {
	        player.setPositionY(1);
	        player.setJumping(false);
	    } else {
	        player.setVerticalSpeed(player.getVerticalSpeed() + 0.01);
	        player.setPositionY(player.getPositionY() + player.getVerticalSpeed());
	    }
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
