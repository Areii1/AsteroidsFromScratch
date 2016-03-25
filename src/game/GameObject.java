package game;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	protected int x, y;
	protected ID id;
	protected int velocityX;
	protected int velocityY;
	protected int objectWidth;
	protected int objectHeight;
	
	public GameObject(int x, int y, ID id, int width, int height) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.objectWidth = width;
		this.objectHeight = height;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public int getVelX() {
		return velocityX;
	}

	public void setVelocityX(int velX) {
		this.velocityX = velX;
	}

	public int getVelY() {
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
}
