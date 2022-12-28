package hr.fer.projektr.game.utility;

import hr.fer.projektr.game.entities.Enemy;
import hr.fer.projektr.game.entities.Entity;
import hr.fer.projektr.game.entities.Player;

public class Physics {


    public void calculateVerticalVelocity(Player player, double g){
        //TODO
        //player.setVerticalSpeed();
    }

    public static void moveEnemyX(Enemy enemy, double amount){
        enemy.setPositionX(enemy.getPositionX()-amount);
    }

    public static void movePlayerY(Player player, double amount){
        player.setPositionY(player.getPositionY()+amount);
    }
}
