package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Player extends GameObject{
	
	private Random rnd;

	public Player(int x, int y, ID id) {
		super(x, y, id);
		rnd = new Random();
//		
		if (rnd.nextBoolean()) {
			velX = rnd.nextInt(3) * 1 + 1;
			velY = rnd.nextInt(2) * 3 + 1;
		}
		else {
			velX = rnd.nextInt(3) * -1 - 1;
			velY = rnd.nextInt(2) * -3 - 1;
		}
	}

	@Override
	public void tick() {
		x = x + velX;
		y = y + velY;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 32, 32);
	}
	
	

}
