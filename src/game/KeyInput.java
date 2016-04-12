package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	private Handler handler;
	private static final int UP = 0;
	private static final int LEFT = 1;
	private static final int DOWN = 2;
	private static final int RIGHT = 3;
	
	
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
						keyDown[UP] = true;
						break;
						
					case KeyEvent.VK_A:
						keyDown[LEFT] = true;
						break;
						
					case KeyEvent.VK_S:
						keyDown[DOWN] = true;
						break;
					
					case KeyEvent.VK_D:
						keyDown[RIGHT] = true;
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
						keyDown[UP] = false;
						break;
						
					case KeyEvent.VK_A:
						keyDown[LEFT] = false;
						break;
						
					case KeyEvent.VK_S:
						keyDown[DOWN] = false;
						break;
						
					case KeyEvent.VK_D:
						keyDown[RIGHT] = false;
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
		if (isUpNorDownPressed()) 
			gameObject.setVelocityY(0);
		if (isLeftNorRightPressed())
			gameObject.setVelocityX(0);
	}
	
	private void checkForPlayerDirection(Player player) {
		if (isAngleUp()) 
			player.setPlayerAngle(Player.ANGLE.Up);
		else if (isAngleLeft()) 
			player.setPlayerAngle(Player.ANGLE.Left);
		else if (isAngleDown()) 
			player.setPlayerAngle(Player.ANGLE.Down);
		else if (isAngleRight()) 
			player.setPlayerAngle(Player.ANGLE.Right);
		else if (isAngleUpLeft()) 
			player.setPlayerAngle(Player.ANGLE.UpLeft);
		else if (isAngleUpRight()) 
			player.setPlayerAngle(Player.ANGLE.UpRight);
		else if (isAngleDownLeft()) 
			player.setPlayerAngle(Player.ANGLE.DownLeft);
		else if (isAngleDownRight()) 
			player.setPlayerAngle(Player.ANGLE.DownRight);
	}
	
	private void setVelocity(GameObject gameObject) {
		if (isAngleUp()) {
			gameObject.setVelocityY(-5);
		}
		else if (isAngleLeft()) {
			gameObject.setVelocityX(-5);
		}
		else if (isAngleDown()) {
			gameObject.setVelocityY(5);
		}
		else if (isAngleRight()) {
			gameObject.setVelocityX(5);
		}
		else if (isAngleUpLeft()) {
			gameObject.setVelocityY(-4);
			gameObject.setVelocityX(-4);
		}
		else if (isAngleUpRight()) {
			gameObject.setVelocityY(-4);
			gameObject.setVelocityX(4);
		}
		else if (isAngleDownLeft()) {
			gameObject.setVelocityY(4);
			gameObject.setVelocityX(-4);
		}
		else if (isAngleDownRight()) {
			gameObject.setVelocityY(4);
			gameObject.setVelocityX(4);
		}
	}
	
	private boolean isAngleUp() {
		return keyDown[UP] && !keyDown[LEFT] && !keyDown[DOWN] && !keyDown[RIGHT];
	}
	
	private boolean isAngleDown() {
		return !keyDown[UP] && !keyDown[LEFT] && keyDown[DOWN] && !keyDown[RIGHT];
	}
	
	private boolean isAngleLeft() {
		return !keyDown[UP] && keyDown[LEFT] && !keyDown[DOWN] && !keyDown[RIGHT];
	}
	
	private boolean isAngleRight() {
		return !keyDown[UP] && !keyDown[LEFT] && !keyDown[DOWN] && keyDown[RIGHT];
	}
	
	private boolean isAngleUpLeft() {
		return keyDown[UP] && keyDown[LEFT] && !keyDown[DOWN] && !keyDown[RIGHT];
	}
	
	private boolean isAngleUpRight() {
		return keyDown[UP] && !keyDown[LEFT] && !keyDown[DOWN] && keyDown[RIGHT];
	}
	
	private boolean isAngleDownLeft() {
		return !keyDown[UP] && keyDown[LEFT] && keyDown[DOWN] && !keyDown[RIGHT];
	}
	
	private boolean isAngleDownRight() {
		return !keyDown[UP] && !keyDown[LEFT] && keyDown[DOWN] && keyDown[RIGHT];
	}
	
	private boolean isUpNorDownPressed() {
		return !keyDown[UP] && !keyDown[DOWN];
	}
	
	private boolean isLeftNorRightPressed() {
		System.out.println(!keyDown[LEFT] && !keyDown[RIGHT]);
		return !keyDown[LEFT] && !keyDown[RIGHT];
	}
}
