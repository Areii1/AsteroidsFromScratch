package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import game.Player.*;

public class KeyInput extends KeyAdapter {
	private Handler handler;
	private boolean[] keyDown = {false, false, false, false};
		
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	// When some key is pressed do something. Makes possible to move Player around.
	public void keyPressed(KeyEvent e) {
		int pressedKey = e.getKeyCode();
		
		for (int i = 0; i < handler.gameObjects.size(); i++) {
			GameObject gameObject = handler.gameObjects.get(i);
			
			if (gameObject.getId() == ID.Player) {
				switch (pressedKey) {
					case KeyEvent.VK_W:
						keyDown[0] = true;
						break;
						
					case KeyEvent.VK_A:
						keyDown[1] = true;
						break;
						
					case KeyEvent.VK_S:
						keyDown[2] = true;
						break;
					
					case KeyEvent.VK_D:
						keyDown[3] = true;
						break;
						
					case KeyEvent.VK_ENTER:
						Player player = (Player) gameObject;
						new Projectile(new Point(player.getTopPointX(), player.getTopPointY()),
								ID.Projectile, handler, (Player) gameObject, 3, 3);
						break;
				}
				Player player = (Player) gameObject;
				setVelocity(gameObject);
				checkForPlayerDirection(player);
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
				Player player = (Player) gameObject;
				setVelocity(gameObject);
				stopPlayerVelocityWhenBothDirectionsNotPressed(gameObject);
				checkForPlayerDirection(player);
			}
			if (gameObject.getId() == ID.Asteroid) {
				if (releasedKey == KeyEvent.VK_SPACE) {
					Asteroid asteroid = (Asteroid) gameObject;
					asteroid.calculateVelocity();
				}
			}
		}
	}
	
	// Stops player when there is not any keys pressed.
	private void stopPlayerVelocityWhenBothDirectionsNotPressed(GameObject gameObject) {
		if (!keyDown[0] && !keyDown[2]) {
			gameObject.setVelocityY(0);
		}
		if (!keyDown[1] && !keyDown[3]) {
			gameObject.setVelocityX(0);
		}
	}
	
	private void checkForPlayerDirection(Player player) {
		if (keyDown[0] && !keyDown[1] && !keyDown[2] && !keyDown[3]) player.setPlayerAngle(Player.ANGLE.Up);
		if (!keyDown[0] && keyDown[1] && !keyDown[2] && !keyDown[3]) player.setPlayerAngle(Player.ANGLE.Left);
		if (!keyDown[0] && !keyDown[1] && keyDown[2] && !keyDown[3]) player.setPlayerAngle(Player.ANGLE.Down);
		if (!keyDown[0] && !keyDown[1] && !keyDown[2] && keyDown[3]) player.setPlayerAngle(Player.ANGLE.Right);
		if (keyDown[0] && keyDown[1] && !keyDown[2] && !keyDown[3]) player.setPlayerAngle(Player.ANGLE.UpLeft);
		if (keyDown[0] && !keyDown[1] && !keyDown[2] && keyDown[3]) player.setPlayerAngle(Player.ANGLE.UpRight);
		if (!keyDown[0] && keyDown[1] && keyDown[2] && !keyDown[3]) player.setPlayerAngle(Player.ANGLE.DownLeft);
		if (!keyDown[0] && !keyDown[1] && keyDown[2] && keyDown[3]) player.setPlayerAngle(Player.ANGLE.DownRight);
	}
	
	private void setVelocity(GameObject gameObject) {
		if (keyDown[0] && !keyDown[1] && !keyDown[2] && !keyDown[3]) {
			gameObject.setVelocityY(-5);
		}
		else if (!keyDown[0] && keyDown[1] && !keyDown[2] && !keyDown[3]) {
			gameObject.setVelocityX(-5);
		}
		else if (!keyDown[0] && !keyDown[1] && keyDown[2] && !keyDown[3]) {
			gameObject.setVelocityY(5);
		}
		else if (!keyDown[0] && !keyDown[1] && !keyDown[2] && keyDown[3]) {
			gameObject.setVelocityX(5);
		}
		else if (keyDown[0] && keyDown[1] && !keyDown[2] && !keyDown[3]) {
			gameObject.setVelocityY(-4);
			gameObject.setVelocityX(-4);
		}
		else if (keyDown[0] && !keyDown[1] && !keyDown[2] && keyDown[3]) {
			gameObject.setVelocityY(-4);
			gameObject.setVelocityX(4);
		}
		else if (!keyDown[0] && keyDown[1] && keyDown[2] && !keyDown[3]) {
			gameObject.setVelocityY(4);
			gameObject.setVelocityX(-4);
		}
		else if (!keyDown[0] && !keyDown[1] && keyDown[2] && keyDown[3]) {
			gameObject.setVelocityY(4);
			gameObject.setVelocityX(4);
		}
	}
}
