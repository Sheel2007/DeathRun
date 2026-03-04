package game;

import java.awt.Graphics;

abstract class Obstacle extends Polygon {
	protected int[] xPts;
	protected int[] yPts;
	protected Point[] pts;

	public Obstacle(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
	}
	
	abstract public void update();
	 
	public void draw(Graphics brush) {
        pts = getPoints();
        xPts = new int[pts.length];
        yPts = new int[pts.length];
        for (int i = 0; i < pts.length; i++) {
            xPts[i] = (int) pts[i].x;
            yPts[i] = (int) pts[i].y;
        }
	}
	
	public int[] getXPts() {
		return xPts;
	}
	
	public int[] getYPts() {
		return yPts;
	}

}
