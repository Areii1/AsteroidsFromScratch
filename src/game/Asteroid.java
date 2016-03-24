package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Asteroid extends GameObject {
	public Random rnd;

	public Asteroid(int x, int y, ID id, int width, int height) {
		super(x, y, id, width, height);
		rnd = new Random();
		
		if (rnd.nextBoolean()) {
			velX = rnd.nextInt(3);
			velY = rnd.nextInt(3) * 2;
		}
		else if (rnd.nextBoolean()) {
			velX = rnd.nextInt(3);
			velY = rnd.nextInt(3) * -2;
		}
		else if (rnd.nextBoolean()) {
			velX = rnd.nextInt(3) * -1;
			velY = rnd.nextInt(3) * 2;
		}
		else if (rnd.nextBoolean()) {
			velX = rnd.nextInt(3) * -1;
			velY = rnd.nextInt(3) * -2;
		}
		else if (rnd.nextBoolean() && rnd.nextBoolean()) {
			velX = rnd.nextInt(20);
			velY = rnd.nextInt(1);
		}
		else if (rnd.nextBoolean() && rnd.nextBoolean()) {
			velX = rnd.nextInt(1);
			velY = rnd.nextInt(20);
		}
		else {
			velX = 2;
			velY = 2;
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	@Override
	public void tick() {
		x = x + velX;
		y = y + velY;
		
		if (rnd.nextBoolean()) {
			if (y < 0 || y > Game.HEIGHT - 32) velY = velY * -1;
			if (x < 0 || x > Game.WIDTH - 32) velX = velX * -1;
		}
		else {
			if (x > Game.WIDTH - 32) x = 0;
			if (x < 0) x = Game.WIDTH - 32;
			if (y > Game.HEIGHT - 32) y = 0;
			if (y < 0) y = Game.HEIGHT - 32;
		}
	}

	@Override
	public void render(Graphics g) {
		
		
		if (this.width == 30 && this.height == 30) {
			g.setColor(Color.RED);
			g.drawRect(x, y, width, height);
		}
		else {
			g.setColor(Color.RED);
			g.drawRect(x, y, width, height);
		}
	}
	
	public int getRandomInt(int seed) {
		rnd = new Random();
		int rndNumber = rnd.nextInt(seed);
		return rndNumber;
	}
}
