package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class KeyInput extends KeyAdapter {
	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	
	public KeyInput(Handler handler) {
		this.handler = handler;
		
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.gameObjects.size(); i++) {
			GameObject tempObject = handler.gameObjects.get(i);
			
			if (tempObject.getId() == ID.Player) {
//				PLAYER KEY events
				if (key == KeyEvent.VK_W) {
					tempObject.setVelY(-5);
					keyDown[0] = true;
				}
				if (key == KeyEvent.VK_A) {
					tempObject.setVelX(-5);
					keyDown[1] = true;
				}
				if (key == KeyEvent.VK_S) { 
					tempObject.setVelY(5);
					keyDown[2] = true;
				}
				if (key == KeyEvent.VK_D) {
					tempObject.setVelX(5);
					keyDown[3] = true;
				}
				
				if (key == KeyEvent.VK_ENTER) new Projectile((tempObject.getX() + tempObject.getWidth() / 2), tempObject.getY(), ID.Projectile, handler, (Player) tempObject, 10, 2); 
			}
			if (tempObject.getId() == ID.Asteroid) {
//				PLAYER KEY events
				if (key == KeyEvent.VK_SPACE) {
					tempObject.setVelY(0);
					tempObject.setVelX(0);
				}
			}
		}
		if (key == KeyEvent.VK_ESCAPE) System.exit(0);
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		Random rnd = new Random();
		
		for (int i = 0; i < handler.gameObjects.size(); i++) {
			GameObject tempObject = handler.gameObjects.get(i);
			
			if (tempObject.getId() == ID.Player) {
//				PLAYER KEY events
				if (key == KeyEvent.VK_W) keyDown[0] = false; //tempObject.setVelY(0);
				if (key == KeyEvent.VK_A) keyDown[1] = false; //tempObject.setVelX(0);
				if (key == KeyEvent.VK_S) keyDown[2] = false; //tempObject.setVelY(0);
				if (key == KeyEvent.VK_D) keyDown[3] = false; //tempObject.setVelX(0);
				
//				Vertical movement
				if (!keyDown[0] && !keyDown[2]) {
					tempObject.setVelY(0);
				}
//				Horizontal movement
				if (!keyDown[1] && !keyDown[3]) {
					tempObject.setVelX(0);
				}
			}
			
			if (tempObject.getId() == ID.Asteroid) {
//				PLAYER KEY events
				if (key == KeyEvent.VK_SPACE) {
					if (rnd.nextBoolean()) {
						tempObject.setVelY(rnd.nextInt(3));
						tempObject.setVelX(rnd.nextInt(3) * 2);
					}
					else if (rnd.nextBoolean()) {
						tempObject.setVelY(rnd.nextInt(3));
						tempObject.setVelX(rnd.nextInt(3) * -2);
					}
					else if (rnd.nextBoolean()) {
						tempObject.setVelY(rnd.nextInt(3) * -1);
						tempObject.setVelX(rnd.nextInt(3) * 2);
					}
					else if (rnd.nextBoolean()) {
						tempObject.setVelY(rnd.nextInt(3) * -1);
						tempObject.setVelX(rnd.nextInt(3) * -2);
					}
					else if (rnd.nextInt(10) == 2) {
						tempObject.setVelY(rnd.nextInt(1));
						tempObject.setVelX(rnd.nextInt(25));
					}
					else if (rnd.nextInt(10) == 2) {
						tempObject.setVelY(rnd.nextInt(25));
						tempObject.setVelX(rnd.nextInt(1));
					}
					else if (rnd.nextInt(30) == 10) {
						tempObject.setVelY(rnd.nextInt(50));
						tempObject.setVelX(rnd.nextInt(30));
					}
					else {
						tempObject.setVelY(rnd.nextInt(3));
						tempObject.setVelX(rnd.nextInt(3));
					}
				}
			}
		}
	}
}
