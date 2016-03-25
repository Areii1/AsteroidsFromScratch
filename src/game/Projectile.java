package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Projectile extends GameObject {
	Handler handler;
	public static int killCount;

	public Projectile(int x, int y, ID id, Handler handler, Player player, int width, int height) {
		super(x, y, id, width, height);
		this.handler = handler;
		
		velocityX = player.getVelX();
		velocityY = player.getVelY() - 1;
		GameObject projectile = (GameObject) this;
		
		handler.addObject(projectile);
	}

	@Override
	public void tick() {
		x = x + velocityX;
		y = y + velocityY;
		
		if (x > Game.WIDTH || x < 0 || y > Game.HEIGHT || y < 0) handler.removeObject(this);
		collision();
	}
	
	private void collision() {
		for (int i = 0; i < handler.gameObjects.size(); i++) {
			GameObject tempObject = handler.gameObjects.get(i);
			
			if (tempObject.getId() == ID.Asteroid) {
				
				if (getBounds().intersects(tempObject.getBounds())) {
					
					if (!(tempObject.getWidth() == 20 && tempObject.getHeight() == 20)) {
						handler.removeObject(this);
						handler.addObject(new Asteroid(tempObject.getX() + 20, tempObject.getY() - 20, ID.Asteroid, 20, 20));
						handler.addObject(new Asteroid(tempObject.getX() - 20, tempObject.getY() + 20, ID.Asteroid, 20, 20));
						handler.removeObject(tempObject);
						
						killCount++;
					}
					else {
						handler.removeObject(tempObject);
						handler.removeObject(this);
						killCount++;
					}
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, objectWidth, objectHeight);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, objectWidth, objectHeight);
	}

}
