package game;

import java.awt.Color;
import java.awt.Graphics;

public class Spike extends Obstacle {
	private double deltaX = 2;

	public Spike(int x, int y, int width, int height) {
        super(new Point[]{
            new Point(width / 2, 0), 
            new Point(0, height),     
            new Point(width, height)   
        }, new Point(x, y), 0);
    }
	
	public void update() {
        if (position.x < 300 ) { 
        	deltaX = 2; 
        }
        if (position.x > 470) {
        	deltaX = -2;
        }
        position.x += deltaX;
    }
	
	public void draw(Graphics brush) {
        super.draw(brush);
        
        brush.setColor(Color.RED);
        brush.fillPolygon(xPts, yPts, pts.length);
        brush.setColor(Color.BLACK);
        brush.drawPolygon(xPts, yPts, pts.length);
    }

}
