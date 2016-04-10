package game;

public class Point {
	private int x;
	private int y;
	
	
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

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
	
	public Point rotatePoint(Point point, Point centerPoint, int angle) {
        angle = (int) (angle * (Math.PI / 180));		//radians
        int rotatedX = (int) (Math.cos(angle) * (point.getX() - centerPoint.getX()) - Math.sin(angle) * (point.getY() - centerPoint.getY()) + centerPoint.getX());
        int rotatedY = (int) (Math.sin(angle) * (point.getX() - centerPoint.getX()) + Math.cos(angle) * (point.getY() - centerPoint.getY()) + centerPoint.getY());
        
//        System.out.println("pointX: " + point.getX() + " pointY: " + point.getY());
//        System.out.println("centerPointX: " + centerPoint.getX() + " centerPointY: " + centerPoint.getY());
//        System.out.println("rotatedX: " + rotatedX + " pointY: " + rotatedY);
        
        return new Point(rotatedX, rotatedY);
    }
}
