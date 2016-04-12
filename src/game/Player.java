package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {
	Handler handler;
	
	public enum ANGLE {
		Up,
		Down,
		Left,
		Right,
		UpLeft,
		UpRight,
		DownLeft,
		DownRight;
	}
	
	private ANGLE playerAngle = ANGLE.Up;
	
	private Point topPoint;
	private Point firstBasePoint;
	private Point secondBasePoint;

	public Player(Point point, ID id, Handler handler, int width, int height) {
		super(point, id, width, height);
		this.handler = handler;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(point.getX(), point.getY(), objectWidth, objectHeight);
	}

	@Override
	public void tick() {
		point.setX(point.getX() + velocityX);
		point.setY(point.getY() + velocityY);
		
		setOppositeHorizontalOrVertivalCoordinate();
		checkForCollision();
	}
	
	/* Checks if particles are in touch with each other. If yes, something happens. Else you can continue normally.
	 * */
	private void checkForCollision() {
		for (int i = 0; i < handler.gameObjects.size(); i++) {
			GameObject gameObject = handler.gameObjects.get(i);
			
			if (gameObject.getId() == ID.Asteroid) {
				if (getBounds().intersects(gameObject.getBounds())) {
					dontKillPlayerAtStart();
					
					point.setX(Game.WIDTH / 2);
					point.setY(Game.HEIGHT / 2);
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		renderPlayer(g, playerAngle);
	}
	
	/* Makes sure that player doesn't die right after it spawns. Works all so as death counter
	 * */
	private void dontKillPlayerAtStart() {
		if (point.getX() > (Game.WIDTH / 2 + 10) || point.getX() < (Game.WIDTH / 2 - 10)
			&& point.getY() > (Game.HEIGHT / 2 + 10) || point.getY() < (Game.HEIGHT / 2 - 10)) {
			Game.deathCount++;
		}
	}
	
	private void renderPlayer(Graphics g, ANGLE angle) {
		g.setColor(Color.GREEN);
	
		topPoint = new Point(point.getX(), point.getY());
		firstBasePoint = new Point(point.getX() - objectWidth / 2, point.getY() + objectHeight);
		secondBasePoint = new Point(point.getX() + objectWidth / 2, point.getY() + objectHeight);
		
		if (angle == ANGLE.Up) {	
			drawLines(g);
		}
		else if (angle == ANGLE.Down) {
			firstBasePoint = new Point(topPoint.getX() - objectWidth / 2, topPoint.getY());
			secondBasePoint = new Point(firstBasePoint.getX() + objectWidth, firstBasePoint.getY());
			topPoint = new Point(topPoint.getX(), topPoint.getY() + objectHeight);
			
			drawLines(g);
		}
		else if (angle == ANGLE.Left) {
			firstBasePoint = new Point(topPoint.getX() + objectWidth / 2, topPoint.getY());
			secondBasePoint = new Point(firstBasePoint.getX(), firstBasePoint.getY() + objectWidth);
			topPoint = new Point(firstBasePoint.getX() - objectWidth, firstBasePoint.getY() + objectWidth / 2);
			
			drawLines(g);
		}
		else if (angle == ANGLE.Right) {
			firstBasePoint = new Point(topPoint.getX() - objectWidth / 2, topPoint.getY());
			secondBasePoint = new Point(firstBasePoint.getX(), firstBasePoint.getY() + objectWidth);
			topPoint = new Point(firstBasePoint.getX() + objectWidth, firstBasePoint.getY() + objectWidth / 2);
			
			drawLines(g);
		}
		else if (angle == ANGLE.UpLeft) {
			topPoint = new Point(topPoint.getX() - objectWidth / 2, firstBasePoint.getY());
			firstBasePoint = new Point(topPoint.getX() + objectWidth / 2, topPoint.getY() + objectHeight);
			secondBasePoint = new Point(firstBasePoint.getX() + objectWidth / 2, firstBasePoint.getY() - objectWidth / 2);
			
			drawLines(g);
		}
		else if (angle == ANGLE.UpRight) {
			topPoint = new Point(topPoint.getX() + objectWidth / 2, firstBasePoint.getY());
			firstBasePoint = new Point(topPoint.getX() - objectWidth / 2, topPoint.getY() + objectHeight);
			secondBasePoint = new Point(firstBasePoint.getX() - objectWidth / 2, firstBasePoint.getY() - objectWidth / 2);
			
			drawLines(g);
		}
		else if (angle == ANGLE.DownLeft) {
			firstBasePoint = new Point(topPoint.getX(), topPoint.getY());
			secondBasePoint = new Point(firstBasePoint.getX() + objectWidth / 2, firstBasePoint.getY() + objectWidth / 2);
			topPoint = new Point(firstBasePoint.getX() - objectWidth / 2, firstBasePoint.getY() + objectHeight);
			
			drawLines(g);
		}
		else if (angle == ANGLE.DownRight) {
			firstBasePoint = new Point(topPoint.getX(), topPoint.getY());
			secondBasePoint = new Point(firstBasePoint.getX() - objectWidth / 2, firstBasePoint.getY() + objectWidth / 2);
			topPoint = new Point(firstBasePoint.getX() + objectWidth / 2, firstBasePoint.getY() + objectHeight);
			
			drawLines(g);
		}
	}
	
	private void drawLines(Graphics g) {
		g.drawLine(topPoint.getX(), topPoint.getY(), secondBasePoint.getX(), secondBasePoint.getY());
		g.drawLine(topPoint.getX(), topPoint.getY(), firstBasePoint.getX(), firstBasePoint.getY());
		g.drawLine(secondBasePoint.getX(), secondBasePoint.getY(), firstBasePoint.getX(), firstBasePoint.getY());
	}
	
	public int getTopPointX() {
		return topPoint.getX();
	}

	public int getTopPointY() {
		return topPoint.getY();
	}

	public ANGLE getPlayerAngle() {
		return playerAngle;
	}

	public void setPlayerAngle(ANGLE playerAngle) {
		this.playerAngle = playerAngle;
	}
}
