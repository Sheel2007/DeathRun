package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Platform implements Collidable {

	private Rectangle bounds;

    public Platform(int x, int y, int width, int height) {
        this.bounds = new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        g.setColor(Color.WHITE);
        g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }

	@Override
	public Rectangle getBounds() {
		return this.bounds;
	}
}


