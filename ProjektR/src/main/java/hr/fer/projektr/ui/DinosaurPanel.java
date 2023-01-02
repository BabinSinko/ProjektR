package hr.fer.projektr.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import hr.fer.projektr.game.GameState;
import hr.fer.projektr.game.entities.Entity;

public class DinosaurPanel extends JPanel implements ActionListener {
	private final static int WIDTH = 1600;
	private final static int HEIGHT = 900;
	private final static int DELAY = 10;
	
	private static final long serialVersionUID = 1L;
	
	private GameState game;
	private EntityImage ei;
	private Timer timer;
	
	public DinosaurPanel(GameState game) {
		this.game = game;
		ei = new EntityImage();
        timer = new Timer(DELAY, this);
        
		gameStart();
	}
	
	private void gameStart() {
        timer.start();
	}
	
	private void render() {
		game.step();
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		drawEntity(g, game.getPlayer());
		for (Entity entity : game.getEnemies()) {
			drawEntity(g, entity);
		}
	}
	
	private void drawEntity(Graphics g, Entity entity) {
		Image image = ei.getEntityImage(entity);
		g.drawImage(image, convertWidth(entity.getPositionX()), convertHeight(entity.getPositionY() - entity.getHeight()), convertWidth(entity.getWidth()), convertHeight(entity.getHeight()), null);
	}
	
	private static int convertWidth(double width) {
		return (int) (width * (WIDTH - 1));
	}
	
	private static int convertHeight(double height) {
		return (int) (height * (HEIGHT - 1));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		render();
	}

}