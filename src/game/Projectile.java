package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Projectile extends GameObject {
	Handler handler;
	public static int killCount;

	public Projectile(int x, int y, ID id, Handler handler, Player player) {
		super(x, y, id);
		this.handler = handler;
		
		velX = player.getVelX() - 1;
		velY = player.getVelY() - 1;
		GameObject projectile = (GameObject) this;
		
		handler.addObject(projectile);
	}

	@Override
	public void tick() {
		x = x + velX;
		y = y + velY;
		
		if (x > Game.WIDTH || x < 0 || y > Game.HEIGHT || y < 0) handler.removeObject(this);
		collision();
	}
	
	private void collision() {
		for (int i = 0; i < handler.gameObjects.size(); i++) {
			GameObject tempObject = handler.gameObjects.get(i);
			
			if (tempObject.getId() == ID.Asteroid) {
				
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(tempObject);
					killCount++;
					}
				}
			}
		}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, 3, 10);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 3, 10);
	}

}
