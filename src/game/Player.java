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
		
		if (x > Game.WIDTH - 32) x = 0;
		if (x < 0) x = Game.WIDTH - 32;
		if (y > Game.HEIGHT - 32) y = 0;
		if (y < 0) y = Game.HEIGHT - 32;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillOval(x, y, 30, 30);
	}
	
	

}
