package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {
	Handler handler;
	private int angle;
	private Point topPoint;
	private Point firstBasePoint;
	private Point secondBasePoint;
	private Point centerPoint;

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
		
		angle = angle + 1;
		if (angle >= 360) angle = 0;
		
//		int rotatedTopX = topPoint.rotatePoint(topPoint, topPoint, 1).getX();
//		int rotatedTopY = topPoint.rotatePoint(topPoint, topPoint, 1).getY();
//		topPoint.setX(rotatedTopX);
//		topPoint.setX(rotatedTopY);
//		
//		int rotatedFirstBasePointX = firstBasePoint.rotatePoint(firstBasePoint, topPoint, 1).getX();
//		int rotatedFirstBasePointY = firstBasePoint.rotatePoint(firstBasePoint, topPoint, 1).getY();
//		firstBasePoint.setX(rotatedFirstBasePointX);
//		firstBasePoint.setX(rotatedFirstBasePointY);
		
		
		
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
		renderPlayer(g);
	}
	
	/* Makes sure that player doesn't die right after it spawns. Works all so as death counter
	 * */
	private void dontKillPlayerAtStart() {
		if (point.getX() > (Game.WIDTH / 2 + 10) || point.getX() < (Game.WIDTH / 2 - 10)
			&& point.getY() > (Game.HEIGHT / 2 + 10) || point.getY() < (Game.HEIGHT / 2 - 10)) {
			Game.deathCount++;
		}
	}
	
	private void renderPlayer(Graphics g) {
		g.setColor(Color.GREEN);
		topPoint = new Point(point.getX(), point.getY());
		firstBasePoint = new Point(point.getX() - objectWidth / 2, point.getY() + objectHeight);
		secondBasePoint = new Point(point.getX() + objectWidth / 2, point.getY() + objectHeight);
//		centerPoint = new Point()
		
		g.drawLine(topPoint.getX(), topPoint.getY(), secondBasePoint.getX(), secondBasePoint.getY());
		g.drawLine(topPoint.getX(), topPoint.getY(), firstBasePoint.getX(), firstBasePoint.getY());
		g.drawLine(secondBasePoint.getX(), secondBasePoint.getY(), firstBasePoint.getX(), firstBasePoint.getY());
	}
	
}
