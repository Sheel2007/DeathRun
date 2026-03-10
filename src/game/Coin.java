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

  public void draw(Graphics g) {
    g.setColor(new Color(255, 215, 0)); // gold
    g.fillOval(x, y, size, size);
    g.setColor(Color.ORANGE);
    g.drawOval(x, y, size, size);
  }

  @Override
  public Rectangle getBounds() {
    return new Rectangle(x, y, size, size);
  }
}
