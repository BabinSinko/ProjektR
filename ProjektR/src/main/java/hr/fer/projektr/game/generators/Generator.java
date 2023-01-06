package hr.fer.projektr.game.generators;

import hr.fer.projektr.game.GameState;
import hr.fer.projektr.game.entities.Bird;
import hr.fer.projektr.game.entities.Cactus;
import hr.fer.projektr.game.entities.CactusType;
import hr.fer.projektr.game.entities.Enemy;

import java.util.List;
import java.util.Random;

/**
 * Abstract class modeling an object that generates other objects, or in other words, a generator.
 */
public class Generator {

    private final GameState gameState;

    /**
     * Nasumicno generirani broj.
     * Na svaki tick se smanji za jedan.
     * Kad se smanji na 0, resetira se i stvara se novi neprijatelj
     */
    private int ticks;

    private final Random rand;

    /**
     * konstruktor
     * @param gameState ...
     */
    public Generator(GameState gameState){
        this.gameState = gameState;
        rand = new Random();
        generateTicks();
    }

    /**
     * Mice prepreke koje se vise ne vide<br/>
     * Dodaje nove neprijatelje u listu<br/>
     * Poziva se na svakom stepu
     */
    public void updateList(){

        removeEnemies();

        if ( ticks>0 ) {
            ticks--;
        }
        else{
            int noEnemy=rand.nextInt(25);
            if (noEnemy<6){
                gameState.addEnemy(new Cactus(CactusType.SMALL));
            }
            else if (noEnemy<12){
                gameState.addEnemy(new Cactus(CactusType.STANDARD));
            }
            else if (noEnemy<18){
                gameState.addEnemy(new Cactus(CactusType.LARGE));
            }
            else if (noEnemy<20){
                gameState.addEnemy(new Cactus(CactusType.LONG));
            }
            else {
                int birdY=rand.nextInt(3);
                if (birdY==1) gameState.addEnemy(new Bird(GameState.MIN_BIRD_Y));
                else if (birdY==2) gameState.addEnemy(new Bird(GameState.CENTER_BIRD_Y));
                else gameState.addEnemy(new Bird(GameState.MAX_BIRD_Y));

            }
            generateTicks();
        }
    }

    /**
     * Ako se prepreka vise ne vidi, mice se iz liste neprijatelja;
     */
    private void removeEnemies(){
        List<Enemy> enemies=gameState.getEnemies();
        for (int k=0; k<enemies.size(); ++k){
            Enemy enemy=enemies.get(k);
            if ( enemy.getLeftX() + enemy.getWidth() <= 0 ) {
                gameState.removeEnemy(k--);
            }
        }
    }

    private void generateTicks(){
        ticks = rand.nextInt((int) (50 / gameState.getSpeed()))+ (int)(3/gameState.STEP_DURATION);
    }
}
