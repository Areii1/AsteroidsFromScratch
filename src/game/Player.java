package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Player extends GameObject{

	public Player(int x, int y, ID id) {
		super(x, y, id);
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
		g.setColor(Color.RED);
		g.fillOval(x, y, 32, 32);
	}
	
	

}
