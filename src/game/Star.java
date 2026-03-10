package game;

import java.awt.Color;
import java.awt.Graphics;

public class Star extends Obstacle {
	private int size;

    public Star(int x, int y, int size) {
        super(new Point[] {
            new Point(size, 0),    
            new Point(size * 1.3, size * 0.7), 
            new Point(size * 2, size),   
            new Point(size * 1.3, size * 1.3), 
            new Point(size, size * 2),   
            new Point(size * 0.7, size * 1.3),
            new Point(0, size),       
            new Point(size * 0.7, size * 0.7) 
        }, new Point(x, y), 0);
        
        this.size = size;
    }

    /**
     * Rotates the object by incrementing it by 5 degrees.
     * 
     * The rotation is kept within the bounds of a circle to prevent 
     * the value form increasing indefinitely.
     */
    public void update() {
        this.rotation = (this.rotation + 5) % 360;
    }
    
    /**
     * Draws the polygon onto the provided graphics context.
     *
     * This method first invokes the superclass draw logic, then renders the 
     * polygon with a light gray fill and a white outline using the current 
     * coordinate sets.
     *
     * @param brush
     */
	@Override
    public void draw(Graphics brush) {
    	super.draw(brush);

        brush.setColor(Color.LIGHT_GRAY);
        brush.fillPolygon(xPts, yPts, pts.length);
        brush.setColor(Color.WHITE);
        brush.drawPolygon(xPts, yPts, pts.length);
    }
}
