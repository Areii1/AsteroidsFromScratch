package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {
	Handler handler;
	private int angle;

	public Player(Point point, ID id, Handler handler, int width, int height) {
		super(point, id, width, height);
		this.handler = handler;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(point.getX(), point.getY(), objectWidth, objectHeight);
	}

	@Override
	public void tick() {
		point.setX(point.getX() + velocityX);
		point.setY(point.getY() + velocityY);
		
		angle = angle + 1;
		if (angle >= 360) angle = 0;
//		x = rotateX(x, y, x - objectWidth / 2, y - objectHeight / 2, angle);
//		y = rotateY(x, y, x - objectWidth / 2, y - objectHeight / 2, angle);
		
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
					
					point.setX(Game.WIDTH / 2);
					point.setY(Game.HEIGHT / 2);
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		renderPlayer(g);
	}
	
	/* Makes sure that player doesn't die right after it spawns. Works all so as death counter
	 * */
	private void dontKillPlayerAtStart() {
		if (point.getX() > (Game.WIDTH / 2 + 10) || point.getX() < (Game.WIDTH / 2 - 10)
			&& point.getY() > (Game.HEIGHT / 2 + 10) || point.getY() < (Game.HEIGHT / 2 - 10)) {
			Game.deathCount++;
		}
	}
	
	private void renderPlayer(Graphics g) {
		g.setColor(Color.GREEN);
		g.drawLine(point.getX(), point.getY(), point.getX() + objectWidth / 2, point.getY() + objectHeight);
		g.drawLine(point.getX(), point.getY(), point.getX() - objectWidth / 2, point.getY() + objectHeight);
		g.drawLine(point.getX() + objectWidth / 2, point.getY() + objectHeight, point.getX() - objectWidth / 2, point.getY() + objectHeight);
		
	}
	
	private int rotateX(int x, int y, int centerX, int centerY, int angle) {
        angle = (int) (angle * (Math.PI / 180));		//radians
        int rotatedX = (int) (Math.cos(angle) * (x - centerX) - Math.sin(angle) * (y - centerY) + centerX);

        return rotatedX;
    }
	
	private int rotateY(int x, int y, int centerX, int centerY, int angle) {
        angle = (int) (angle * (Math.PI / 180));		//radians
        int rotatedY = (int) (Math.sin(angle) * (x - centerX) + Math.cos(angle) * (y - centerY) + centerY);

        return rotatedY;
    }
	
}
