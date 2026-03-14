package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Represents a platform that the player can stand on.
 *
 * The player can land on it and use them to
 * move through the map.
 */
public class Platform implements Collidable {

    private Rectangle bounds;

    public Platform(int x, int y, int width, int height) {
        this.bounds = new Rectangle(x, y, width, height);
    }

    /**
     * Draws the polygon onto the provided graphics context.
     *
     * draws a rectangle with a light gray fill and a white
     * outline using the coordinates from the constructor.
     *
     * @param g
     */
    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        g.setColor(Color.WHITE);
        g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }

    /**
     * gets the coordinates for this rectangle.
     */
    @Override
    public Rectangle getBounds() {
        return this.bounds;
    }
}
