package hr.fer.projektr.game;

import hr.fer.projektr.game.entities.Enemy;
import hr.fer.projektr.game.entities.Player;

import java.util.List;

public class GameInterface {
    private final GameState gameState;

    public GameInterface() {
        this.gameState = new GameState();
    }

    public void start(){
        gameState.start();
    }

    public void input(boolean duck, boolean jump){
        gameState.getPlayer().influencePlayer(duck, jump);
    }

    public Player getPlayer(){
        return gameState.getPlayer();
    }

    public List<Enemy> getEnemies(){
        return gameState.getEnemies();
    }
}
