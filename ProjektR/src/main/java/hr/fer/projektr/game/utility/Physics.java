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

  //Sluzilo samo za isprobavanje skoka, mozda korisno za ubuduce, inace ignore ili delete

    /* 
    public static void main(String ...args) {
    	double positionX = 0;
    	double positionY = 1;
    	double speed = 0.785;
    	double gravity = 1;
    	double t = 0;
    	
    	@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
    	String s;
    	boolean jump = false;
    	
    	while(true) {
    		s = sc.nextLine();
    		
    		if(s.equals("w") && jump == false) 
    			jump = true;
    		else if (!(s.equals("w")) && jump == true)
    			jump = false;
    		
    		//positionX += speed * t;
    		
    		if(jump == true && (positionY < 1.000001 && positionY > 0.9999999)) {
	    		do {
	    			//konstante namjestati, gravitacija najvise ovisi o frameratu zbog kvadrata, paziti na to
	    			t += 0.1;
	    			positionY = positionY - speed * t + gravity * t * t;
	    			
	    			if(positionY >= 0.99) {
	    				positionY = 1;
		    			t = 0;
	    			}
	    			
	    			//printove zamijeniti s iscrtavanjem
	    			System.out.println("PositionY: " + positionY);
	    			System.out.println("t: " + t);
	    		} while(positionY < 0.99);
	    		
    		}
    	}
    	
    }
    */
}
