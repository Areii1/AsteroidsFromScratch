package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {
	Handler handler;
	public static int deathCounter;

	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 20, 20);
	}

	@Override
	public void tick() {
		x = x + velX;
		y = y + velY;
		
		if (x > Game.WIDTH - 32) x = 0;
		if (x < 0) x = Game.WIDTH - 32;
		if (y > Game.HEIGHT - 32) y = 0;
		if (y < 0) y = Game.HEIGHT - 32;
		
		collision();
	}
	
	private void collision() {
		for (int i = 0; i < handler.gameObjects.size(); i++) {
			GameObject tempObject = handler.gameObjects.get(i);
			
			if (tempObject.getId() == ID.Asteroid) {
				
				if (getBounds().intersects(tempObject.getBounds())) {
//					collision code
					if ((((getX() > (Game.WIDTH / 2 + 10)) || (getX() < (Game.WIDTH / 2 - 10))) 
						&& ((getY() > (Game.HEIGHT / 2 + 10)) || (getY() < (Game.HEIGHT / 2 - 10))))) {
						deathCounter++;
					}
					setX(Game.WIDTH / 2);
					setY(Game.HEIGHT / 2);
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, 20, 20);
	}
}
