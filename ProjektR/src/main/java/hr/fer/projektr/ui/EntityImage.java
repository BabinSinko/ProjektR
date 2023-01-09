package hr.fer.projektr.ui;

import java.awt.Image;
import java.awt.image.BufferedImage;
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
	private Image cactusSvenImage;
	private Image coinImage;
	
	public EntityImage() {
		try {
			this.playerImage = ImageIO.read(getClass().getClassLoader().getResource("Dino.png"));
			this.playerDuckingImage = ImageIO.read(getClass().getClassLoader().getResource("DinoDucking1.png"));
			this.cactusSmallImage = ImageIO.read(getClass().getClassLoader().getResource("CactusSmall.png"));
			this.cactusStandardImage = ImageIO.read(getClass().getClassLoader().getResource("CactusStandard.png"));
			this.cactusLargeImage = ImageIO.read(getClass().getClassLoader().getResource("CactusLarge.png"));
			this.cactusSvenImage = ImageIO.read(getClass().getClassLoader().getResource("CactusSven.png"));
			this.birdImage = ImageIO.read(getClass().getClassLoader().getResource("Bird.png"));
			this.coinImage = ImageIO.read(getClass().getClassLoader().getResource("Coin.png"));
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

			case CACTUS_LONG:
				return cactusSvenImage;

			case BIRD:
				return birdImage;
			case COIN:
				return coinImage;
			default:
				return null;
		}
	}

	public double getWidthAdjustments(Entity entity){

		if (entity.getEntityType().equals(EntityType.PLAYER) && ((Player) entity).isDucking()){
			return 0;
		}

		BufferedImage entityImage = (BufferedImage) getEntityImage(entity);
		return (entity.getHeight() * entityImage.getWidth() / entityImage.getHeight() - entity.getWidth()) / 2;
	}
}
