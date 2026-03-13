package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Coin implements Collidable {

  private int x;
  private int y;
  private int size;

  public Coin(int x, int y, int size) {
    this.x = x;
    this.y = y;
    this.size = size;
  }

  /**
   * Draws the coin on the top of the window.
   *
   * The coin is a gold circle with an orange outline.
   *
   * @param g the Graphics object used for drawing
   */
  public void draw(Graphics g) {
    g.setColor(new Color(255, 215, 0)); // gold
    g.fillOval(x, y, size, size);
    g.setColor(Color.ORANGE);
    g.drawOval(x, y, size, size);
  }

  /**
   * Returns a collision boundary of the coin.
   *
   * This is used to detect collisions with other objects.
   *
   * @return a Rectangle representing the coin's bounds
   */
  @Override
  public Rectangle getBounds() {
    return new Rectangle(x, y, size, size);
  }
}
