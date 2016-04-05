package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Projectile extends GameObject {
	Handler handler;

	public Projectile(int x, int y, ID id, Handler handler, Player player, int width, int height) {
		super(x, y, id, width, height);
		this.handler = handler;
		
		handler.addObject(this);
		if (player.getVelocityX() == 0 && player.getVelocityY() == 0) {
			handler.removeObject(this);
		}
		
		if (player.getVelocityX() < 0 && player.getVelocityY() < 0) {
			velocityX = player.getVelocityX() - 2;
			velocityY = player.getVelocityY() - 2;
		}
		else if (player.getVelocityX() > 0 && player.getVelocityY() > 0) {
			velocityX = player.getVelocityX() + 2;
			velocityY = player.getVelocityY() + 2;
		}
		else if (player.getVelocityX() < 0 && player.getVelocityY() > 0) {
			velocityX = player.getVelocityX() - 2;
			velocityY = player.getVelocityY() + 2;
		}
		else if (player.getVelocityX() > 0 && player.getVelocityY() < 0) {
			velocityX = player.getVelocityX() + 2;
			velocityY = player.getVelocityY() - 2;
		}
		else if (player.getVelocityX() == 0 && player.getVelocityY() > 0) {
			velocityX = 0;
			velocityY = player.getVelocityY() + 2;
		}
		else if (player.getVelocityX() == 0 && player.getVelocityY() < 0) {
			velocityX = 0;
			velocityY = player.getVelocityY() - 2;
		}
		else if (player.getVelocityX() > 0 && player.getVelocityY() == 0) {
			velocityX = player.getVelocityX() + 2;
			velocityY = 0;
		}
		else if (player.getVelocityX() < 0 && player.getVelocityY() == 0) {
			velocityX = player.getVelocityX() - 2;
			velocityY = 0;
		}
		
		
	}

	@Override
	public void tick() {
		x = x + velocityX;
		y = y + velocityY;
		
		removeWhenOutOfArea();
		checkForImpact();
	}
	// checks if bullet hits asteroid.
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
		return new Rectangle(x - objectWidth / 2, y, objectWidth - (objectWidth / 4), objectHeight);
	}
	
	private void removeWhenOutOfArea() {
		if (x > Game.WIDTH || x < 0 || y > Game.HEIGHT || y < 0) {
			handler.removeObject(this);
		}
	}

}
