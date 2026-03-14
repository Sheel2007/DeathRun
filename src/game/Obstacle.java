package game;

import java.awt.Graphics;

/**
 * Represents an abstract obstacle.
 *
 * Obstacles are can move,
 * rotate, or change position over time. 
 * 
 * Obstacles reset the player to the beginning.
 */
abstract class Obstacle extends Polygon {
	protected int[] xPts;
	protected int[] yPts;
	protected Point[] pts;

	public Obstacle(Point[] inShape, Point inPosition, double inRotation) {
		super(inShape, inPosition, inRotation);
	}
	
	/**
	 * changes how an obstacle moves either by rotating an element or shift position
	 */
	abstract public void update();
	
	/**
	 * get method for x points
	 * 
	 * @return xPts an array of the x coordinates of the object
	 */
	public int[] getXPts() {
		return xPts;
	}
	
	/**
	 * get method for x points
	 * 
	 * @return yPts an array of the y coordinates of the object
	 */
	public int[] getYPts() {
		return yPts;
	}

	/**
	 * Retrieves a list of points and initializes an array for xPts and yPts
	 * to prepare for rendering
	 * 
	 * @param brush 
	 */
	public void draw(Graphics brush) {
        pts = getPoints();
        xPts = new int[pts.length];
        yPts = new int[pts.length];
        for (int i = 0; i < pts.length; i++) {
            xPts[i] = (int) pts[i].x;
            yPts[i] = (int) pts[i].y;
        }
	}
}
