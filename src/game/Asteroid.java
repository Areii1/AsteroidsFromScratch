package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Asteroid extends GameObject {
	public Random rnd;

	public Asteroid(Point point, ID id, int width, int height) {
		super(point, id, width, height);
		calculateVelocity();
	}
	
	public Rectangle getBounds() {
		return new Rectangle(point.getX(), point.getY(), objectWidth, objectHeight);
	}

	@Override
	public void tick() {
		point.setX(point.getX() + velocityX);
		point.setY(point.getY() + velocityY);
		
		if (Math.random() >= 0.5) {
			bounceFromWall();
		}
		else {
			setOppositeHorizontalOrVertivalCoordinate();
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.drawRect(point.getX(), point.getY(), objectWidth, objectHeight);
	}
	
	/* Calculates velocity of the asteroids and sets the velocity to them. 
	 * */
	public void calculateVelocity() {
		double random = Math.random();
		
		if (random > 0.999) {
			velocityX = getRandomIntBetween(-40, 40);
			
			velocityY = getRandomIntBetween(-40, 40);
		}
		else if (random > 0.96) {
			if (random > 0.5) {
				velocityX = getRandomIntBetween(-20, 20);
			}
			else {
				velocityY = getRandomIntBetween(-20, 20);
			}
		}
		else {
			velocityX = getRandomIntBetween(-3, 3);
			velocityY = getRandomIntBetween(-3, 3);
		}
	}
	
	// gets random value between max and min
	private int getRandomIntBetween(int min, int max) {
		Random rnd = new Random();
		return rnd.nextInt(max - min + 1) + min;
	}
	
	// Checks if the asteroid is edge of window. If yes, changes the direction to other direction.
	private void bounceFromWall() {
		if (point.getY() < 0 || point.getY() > Game.HEIGHT - objectWidth) {
			velocityY = velocityY * -1;
		}
		if (point.getX() < 0 || point.getX() > Game.WIDTH - objectWidth) {
			velocityX = velocityX * -1;
		}
	}
	
	// Checks if asteroid is big asteroid.
	public boolean isBigger() {
		return getWidth() != 20 && getHeight() != 20;
	}
}
