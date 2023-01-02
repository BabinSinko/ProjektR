package hr.fer.projektr.ui;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import hr.fer.projektr.game.entities.Entity;
import hr.fer.projektr.game.entities.EntityType;
import hr.fer.projektr.game.entities.Player;

public class EntityImage {
	private Image playerImage;
	private Image playerDuckingImage;
	private Image cactusSmallImage;
	private Image cactusStandardImage;
	private Image cactusLargeImage;
	private Image birdImage;
	
	public EntityImage() {
		try {
			this.playerImage = ImageIO.read(getClass().getClassLoader().getResource("dinosaur.png"));
			this.playerDuckingImage = ImageIO.read(getClass().getClassLoader().getResource("dinosaur_ducking.png"));
//			this.cactusSmallImage = ImageIO.read(getClass().getClassLoader().getResource("cactus_small.png"));
//			this.cactusStandardImage = ImageIO.read(getClass().getClassLoader().getResource("cactus_standard.png"));
//			this.cactusLargeImage = ImageIO.read(getClass().getClassLoader().getResource("cactus_large.png"));
//			this.birdImage = ImageIO.read(getClass().getClassLoader().getResource("bird.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Image getEntityImage(Entity entity) {
		EntityType type = entity.getEntityType();
		
		switch (type) {
			case PLAYER:
				Player player = (Player) entity;
				
				if (player.isDucking()) {
					return playerDuckingImage;
				}
				return playerImage;
		
			case CACTUS_SMALL:
				return cactusSmallImage;
				
			case CACTUS_STANDARD:
				return cactusStandardImage;
				
			case CACTUS_LARGE:
				return cactusLargeImage;
				
			case BIRD:
				return birdImage;
				
			default:
				return null;
		}
	}
}
