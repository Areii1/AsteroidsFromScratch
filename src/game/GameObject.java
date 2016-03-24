package game;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	protected int x, y;
	protected ID id;
	protected int velX;
	protected int velY;
	protected int width;
	protected int height;
	
	public GameObject(int x, int y, ID id, int width, int height) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.width = width;
		this.height = height;
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
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
