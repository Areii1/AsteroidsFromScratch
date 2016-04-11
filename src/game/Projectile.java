package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Projectile extends GameObject {
	Handler handler;

	public Projectile(Point point, ID id, Handler handler, Player player, int width, int height) {
		super(point, id, width, height);
		this.handler = handler;
		
		handler.addObject(this);
		
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
		else if (player.getVelocityX() == 0 && player.getVelocityY() > 0 || (KeyInput.playerAngle == Player.ANGLE.Down)) {
			velocityX = 0;
			velocityY = player.getVelocityY() + 2;
		}
		else if (player.getVelocityX() == 0 && player.getVelocityY() < 0 || (KeyInput.playerAngle == Player.ANGLE.Up)) {
			velocityX = 0;
			velocityY = player.getVelocityY() - 2;
		}
		else if (player.getVelocityX() > 0 && player.getVelocityY() == 0 || (KeyInput.playerAngle == Player.ANGLE.Right)) {
			velocityX = player.getVelocityX() + 2;
			velocityY = 0;
		}
		else if (player.getVelocityX() < 0 && player.getVelocityY() == 0 || (KeyInput.playerAngle == Player.ANGLE.Left)) {
			velocityX = player.getVelocityX() - 2;
			velocityY = 0;
		}
		
		
	}

	@Override
	public void tick() {
		point.setX(point.getX() + velocityX);
		point.setY(point.getY() + velocityY);
		
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
						handler.addObject(new Asteroid(new Point(gameObject.point.getX() + 20, gameObject.point.getY() - 20), ID.Asteroid, 20, 20));
						handler.addObject(new Asteroid(new Point(gameObject.point.getX() - 20, gameObject.point.getY() + 20), ID.Asteroid, 20, 20));
						
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
		g.fillRect(point.getX(), point.getY(), objectWidth, objectHeight);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(point.getX() - objectWidth / 2, point.getY(), objectWidth - (objectWidth / 4), objectHeight);
	}
	// removes projectile when it's out of the window.
	private void removeWhenOutOfArea() {
		if (point.getX() > Game.WIDTH || point.getX() < 0 || point.getY() > Game.HEIGHT || point.getY() < 0) {
			handler.removeObject(this);
		}
	}

}
