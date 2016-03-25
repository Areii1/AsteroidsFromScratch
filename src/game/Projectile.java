package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Projectile extends GameObject {
	Handler handler;

	public Projectile(int x, int y, ID id, Handler handler, Player player, int width, int height) {
		super(x, y, id, width, height);
		this.handler = handler;
		
		if (player.getVelocityX() < 0) {
			velocityX = -7;
		}
		else if (player.getVelocityX() > 0) {
			velocityX = 7;
		}
		else {
			velocityX = 0;
		}
		
		if (player.getVelocityY() < 0) {
			velocityY = -7;
		}
		else if (player.getVelocityY() > 0) {
			velocityY = 7;
		}
		else {
			velocityY = -7;
		}
		
		handler.addObject(this);
	}

	@Override
	public void tick() {
		x = x + velocityX;
		y = y + velocityY;
		
		removeWhenOutOfArea();
		checkForImpact();
	}
	
	private void checkForImpact() {
		for (int i = 0; i < handler.gameObjects.size(); i++) {
			GameObject gameObject = handler.gameObjects.get(i);
			
			if (gameObject.getId() == ID.Asteroid) {
				Asteroid asteroid = (Asteroid) gameObject;
				if (getBounds().intersects(gameObject.getBounds())) {
					if (asteroid.isBigger()) {
						handler.removeObject(this);
						handler.removeObject(gameObject);
						handler.addObject(new Asteroid(gameObject.getX() + 20, gameObject.getY() - 20, ID.Asteroid, 20, 20));
						handler.addObject(new Asteroid(gameObject.getX() - 20, gameObject.getY() + 20, ID.Asteroid, 20, 20));
						
						Game.killCount++;
					}
					else {
						handler.removeObject(this);
						handler.removeObject(gameObject);
						
						Game.killCount++;
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
	
	private void removeWhenOutOfArea() {
		if (x > Game.WIDTH || x < 0 || y > Game.HEIGHT || y < 0) {
			handler.removeObject(this);
		}
	}

}
