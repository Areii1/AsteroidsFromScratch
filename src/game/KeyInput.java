package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		Random rnd = new Random();
		
		for (int i = 0; i < handler.gameObjects.size(); i++) {
			GameObject tempObject = handler.gameObjects.get(i);
			
			if (tempObject.getId() == ID.Player) {
//				PLAYER KEY events
				if (key == KeyEvent.VK_W) tempObject.setVelY(-5);
				if (key == KeyEvent.VK_A) tempObject.setVelX(-5);
				if (key == KeyEvent.VK_S) tempObject.setVelY(5);
				if (key == KeyEvent.VK_D) tempObject.setVelX(5);
			}
			
			if (tempObject.getId() == ID.Asteroid) {
//				PLAYER KEY events
				if (key == KeyEvent.VK_SPACE) {
					tempObject.setVelY(0);
					tempObject.setVelX(0);
					
				}
			}
		}
		
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		Random rnd = new Random();
		
		for (int i = 0; i < handler.gameObjects.size(); i++) {
			GameObject tempObject = handler.gameObjects.get(i);
			
			if (tempObject.getId() == ID.Player) {
//				PLAYER KEY events
				if (key == KeyEvent.VK_W) tempObject.setVelY(0);
				if (key == KeyEvent.VK_A) tempObject.setVelX(0);
				if (key == KeyEvent.VK_S) tempObject.setVelY(0);
				if (key == KeyEvent.VK_D) tempObject.setVelX(0);
			}
			
			if (tempObject.getId() == ID.Asteroid) {
//				PLAYER KEY events
				if (key == KeyEvent.VK_SPACE) {
					if (rnd.nextBoolean()) {
						tempObject.setVelY(rnd.nextInt(5));
						tempObject.setVelX(rnd.nextInt(5));
					}
					else if (rnd.nextBoolean()) {
						tempObject.setVelY(rnd.nextInt(5) * -1);
						tempObject.setVelX(rnd.nextInt(5));
					}
					else if (rnd.nextBoolean()) {
						tempObject.setVelY(rnd.nextInt(5));
						tempObject.setVelX(rnd.nextInt(5) * -1);
					}
					else {
						tempObject.setVelY(rnd.nextInt(5) * -1);
						tempObject.setVelX(rnd.nextInt(5) * -1);
					}
					
					
					
				}
			}
		}
	}
}
