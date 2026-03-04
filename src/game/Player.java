package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player implements Collidable  {
	private double x;
	  private double y;
	  private int width;
	  private int height;

	  private double vx;
	  private double vy;

	  private boolean onGround;

	  public Player(int x, int y, int width, int height) {
	    this.x = x;
	    this.y = y;
	    this.width = width;
	    this.height = height;
	    this.vx = 0;
	    this.vy = 0;
	    this.onGround = false;
	  }

	  public void update(double gravity, double floorY, int worldWidth) {
	    vy += gravity;
	    x += vx;
	    y += vy;

	    if (y + height >= floorY) {
	      y = floorY - height;
	      vy = 0;
	      onGround = true;
	    } else {
	      onGround = false;
	    }

	    if (x < 0) {
	      x = 0;
	    }
	    if (x + width > worldWidth) {
	      x = worldWidth - width;
	    }
	  }

	  public void draw(Graphics g) {
	    g.setColor(Color.green);
	    g.fillRect((int) x, (int) y, width, height);
	  }

	  @Override
	  public Rectangle getBounds() {
	    return new Rectangle((int) x, (int) y, width, height);
	  }

	  public boolean isOnGround() {
	    return onGround;
	  }

	  public void setVx(double vx) {
	    this.vx = vx;
	  }

	  public void setVy(double vy) {
	    this.vy = vy;
	  }

	 public double getX() {
		return x;
	 }

	 public double getY() {
		return y;
	 }

	 public double getVy() {
		return vy;
	 }

	 public int getWidth() {
		return width;
	 }

	 public int getHeight() {
		return height;
	 }

	 public void setY(double y) {
		this.y = y;	
	 }
	 
	 public void setX(double x) {
		 this.x = x;
	 }

	 public void setOnGround(boolean b) {
		this.onGround = b;
	 }
	 
}