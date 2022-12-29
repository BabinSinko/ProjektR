package hr.fer.projektr.game.generators;

import hr.fer.projektr.game.GameState;
import hr.fer.projektr.game.entities.Enemy;

import java.util.List;

/**
 * Abstract class modeling an object that generates other objects, or in other words, a generator.
 */
public abstract class Generator {

    private GameState gameState;

    /**
     * konstruktor
     * @param gameState ...
     */
    public Generator(GameState gameState){
        this.gameState=gameState;
    }

    /**
     * Mice prepreke koje se vise ne vide<br/>
     * Dodaje nove neprijatelje u listu<br/>
     * Poziva se na svakom stepu
     */
    public void updateList(){

        removeEnemies();

        List<Enemy> enemies=gameState.getEnemies();

        if ( enemies.size()<4 ) {

        }
    }

    /**
     * Ako se prepreka vise ne vidi, mice se iz liste neprijatelja;
     */
    private void removeEnemies(){
        List<Enemy> enemies=gameState.getEnemies();
        for (int k=0; k<enemies.size(); ++k){
            Enemy enemy=enemies.get(k);
            if ( enemy.getPositionX() + enemy.getWidth() <= 0 ) {
                gameState.removeEnemy(k--);
            }
        }
    }
}
