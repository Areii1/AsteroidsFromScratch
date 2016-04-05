package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {
	Handler handler;

	public Player(int x, int y, ID id, Handler handler, int width, int height) {
		super(x, y, id, width, height);
		this.handler = handler;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, objectWidth, objectHeight);
	}

	@Override
	public void tick() {
		x = x + velocityX;
		y = y + velocityY;
		
		setOppositeHorizontalOrVertivalCoordinate();
		checkForCollision();
	}
	/* Checks if particles are in touch with each other. If yes, something happens. Else you can continue normally.
	 * */
	private void checkForCollision() {
		for (int i = 0; i < handler.gameObjects.size(); i++) {
			GameObject gameObject = handler.gameObjects.get(i);
			
			if (gameObject.getId() == ID.Asteroid) {
				if (getBounds().intersects(gameObject.getBounds())) {
					dontKillPlayerAtStart();
					
					setX(Game.WIDTH / 2);
					setY(Game.HEIGHT / 2);
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.GREEN);
//		g.fillRect(x, y, objectWidth, objectHeight);
		g.drawLine(x, y, x + objectWidth / 2, y + objectHeight);
		g.drawLine(x, y, x - objectWidth / 2, y + objectHeight);
		
		g.drawLine(x + objectWidth / 2, y + objectHeight, x - objectWidth / 2, y + objectHeight);
	}
	/* Makes sure that player doesn't die right after it spawns. Works all so as death counter
	 * */
	private void dontKillPlayerAtStart() {
		if (getX() > (Game.WIDTH / 2 + 10) || getX() < (Game.WIDTH / 2 - 10)
			&& getY() > (Game.HEIGHT / 2 + 10) || getY() < (Game.HEIGHT / 2 - 10)) {
			Game.deathCount++;
		}
	}
	
}
