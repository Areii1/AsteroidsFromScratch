package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import game.Game.STATE;

public class KeyInput extends KeyAdapter {
	private Handler handler;
	private boolean[] keyDown = {false, false, false, false};
	private Game game;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int pressedKey = e.getKeyCode();
		
		for (int i = 0; i < handler.gameObjects.size(); i++) {
			GameObject gameObject = handler.gameObjects.get(i);
			
			if (gameObject.getId() == ID.Player) {
				switch (pressedKey) {
					case KeyEvent.VK_W:
						gameObject.setVelocityY(-5);
						keyDown[0] = true;
						break;
						
					case KeyEvent.VK_A:
						gameObject.setVelocityX(-5);
						keyDown[1] = true;
						break;
						
					case KeyEvent.VK_S:
						gameObject.setVelocityY(5);
						keyDown[2] = true;
						break;
					
					case KeyEvent.VK_D:
						gameObject.setVelocityX(5);
						keyDown[3] = true;
						break;
						
					case KeyEvent.VK_ENTER:
						new Projectile(gameObject.getX(), gameObject.getY(),
							ID.Projectile, handler, (Player) gameObject, 3, 3);
						break;
				}
			}
			else if (gameObject.getId() == ID.Asteroid) {
				if (pressedKey == KeyEvent.VK_SPACE) {
					gameObject.setVelocityY(0);
					gameObject.setVelocityX(0);
				}
			}
		}
		if (pressedKey == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
	}

	public void keyReleased(KeyEvent e) {
		int releasedKey = e.getKeyCode();
		
		for (int i = 0; i < handler.gameObjects.size(); i++) {
			GameObject gameObject = handler.gameObjects.get(i);
			
			if (gameObject.getId() == ID.Player) { 
				switch (releasedKey) {
					case KeyEvent.VK_W:
						keyDown[0] = false;
						break;
						
					case KeyEvent.VK_A:
						keyDown[1] = false;
						break;
						
					case KeyEvent.VK_S:
						keyDown[2] = false;
						break;
						
					case KeyEvent.VK_D:
						keyDown[3] = false;
						break;
				}
				stopPlayerVelocityWhenBothDirectionsNotPressed(gameObject);
			}
			
			if (gameObject.getId() == ID.Asteroid) {
				if (releasedKey == KeyEvent.VK_SPACE) {
					Asteroid asteroid = (Asteroid) gameObject;
					asteroid.calculateVelocity();
				}
			}
		}
	}
	
	private void stopPlayerVelocityWhenBothDirectionsNotPressed(GameObject gameObject) {
		if (!keyDown[0] && !keyDown[2]) {
			gameObject.setVelocityY(0);
		}
			
		if (!keyDown[1] && !keyDown[3]) {
			gameObject.setVelocityX(0);
		}
	}
}
