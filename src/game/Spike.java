package game;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Represents a spike obstacle.
 *
 * Spikes make the player lose a life.
 * Some spikes can also move.
 */
public class Spike extends Obstacle {
    private double deltaX = 2;
    private static final double MIN_X = 300;
    private static final double MAX_X = 470;
    private static final double MOVEMENT_SPEED = 2;

    public Spike(int x, int y, int width, int height) {
        super(new Point[] {
                new Point(width / 2, 0),
                new Point(0, height),
                new Point(width, height)
        }, new Point(x, y), 0);
    }

    /**
     * Updates the object's horizontal position, reversing direction if it hits
     * the MIN_X value or MAX_X value.
     */
    public void update() {
        if (position.x < MIN_X) {
            deltaX = MOVEMENT_SPEED;
        }
        if (position.x > MAX_X) {
            deltaX = -MOVEMENT_SPEED;
        }
        position.x += deltaX;
    }

    /**
     * Draws the polygon onto the provided graphics context.
     *
     * This method first invokes the superclass draw logic, then renders the
     * polygon with a red fill and a black outline using the current
     * coordinate sets.
     *
     * @param brush the Graphics context used for drawing.
     */
    @Override
    public void draw(Graphics brush) {
        super.draw(brush);

        brush.setColor(Color.RED);
        brush.fillPolygon(xPts, yPts, pts.length);
        brush.setColor(Color.BLACK);
        brush.drawPolygon(xPts, yPts, pts.length);
    }

}
