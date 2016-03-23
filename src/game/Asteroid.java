package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Asteroid extends GameObject{
	
	private int height;
	private int width;
	public Random rnd;

	public Asteroid(int x, int y, ID id) {
		super(x, y, id);
		rnd = new Random();
		
		if (rnd.nextBoolean()) {
			velX = rnd.nextInt(2) * 1 + 1;
			velY = rnd.nextInt(2) * 2 + 1;
		}
		else if (rnd.nextBoolean()) {
			velX = rnd.nextInt(2) * -1 + 1;
			velY = rnd.nextInt(2) * -2 - 1;
		}
		else if (rnd.nextBoolean()) {
			velX = rnd.nextInt(2) * -1 - 1;
			velY = rnd.nextInt(2) * -2 + 1;
		}
		else {
			velX = rnd.nextInt(2) * -1 - 1;
			velY = rnd.nextInt(2) * -2 - 1;
		
//			do { 
//		height = rnd.nextInt(30) + 10;
//		width = rnd.nextInt(50) + 10;
//			} while(height == 0 || width == 0);
//			
//		System.out.println("height: " + height);
//		System.out.println("width: " + width);
		}
	}

	@Override
	public void tick() {
		x = x + velX;
		y = y + velY;
		
		if (x > Game.WIDTH) x = 0;
		if (x < 0) x = Game.WIDTH;
		if (y > Game.HEIGHT) y = 0;
		if (y < 0) y = Game.HEIGHT;
	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.WHITE);
		g.drawRect(x, y, 30, 30);
	}
	
	public int getRandomInt(int seed) {
		rnd = new Random();
		int rndNumber = rnd.nextInt(seed);
		return rndNumber;
	}

}
