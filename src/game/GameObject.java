package game;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	Point point;
	protected ID id;
	protected int velocityX;
	protected int velocityY;
	protected int objectWidth;
	protected int objectHeight;
	
	public GameObject(Point point, ID id, int width, int height) {
		this.point = point;
		this.id = id;
		this.objectWidth = width;
		this.objectHeight = height;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public int getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(int velX) {
		this.velocityX = velX;
	}

	public int getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(int velY) {
		this.velocityY = velY;
	}
	
	public int getWidth() {
		return objectWidth;
	}

	public void setWidth(int width) {
		this.objectWidth = width;
	}

	public int getHeight() {
		return objectHeight;
	}

	public void setHeight(int height) {
		this.objectHeight = height;
	}
	
	protected void setOppositeHorizontalOrVertivalCoordinate() {
		if (point.getX() > Game.WIDTH - objectWidth) {
			point.setX(0);
		}
		else if (point.getX() < 0) {
			point.setX(Game.WIDTH - objectWidth);
		}
		if (point.getY() > Game.HEIGHT - objectWidth) {
			point.setY(0);
		}
		else if (point.getY() < 0) {
			point.setY(Game.HEIGHT - objectWidth);
		}
	}
}
