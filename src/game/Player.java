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

	  /**
	     * Updates the object's position and velocity based on gravity and world boundaries.
	     * 
	     * Applies gravity to the vertical velocity, updates the coordinates, 
	     * and performs collision checks. If the object hits the floor, its vertical 
	     * velocity is set to zero and the onGround state is set to true.
	     * It also constrains the object's horizontal position within the world width.
	     *
	     *
	     * @param gravity   
	     * @param floorY   
	     * @param worldWidth 
	     */
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

	  /**
	   * Draws the player onto the provided graphics context.
	   * 
	   * Renders the player with a green fill using the coordinates and dimensions 
	   * of the player.
	   * 
	   * @param g
	   */
	  public void draw(Graphics g) {
	    g.setColor(Color.green);
	    g.fillRect((int) x, (int) y, width, height);
	  }

	  /**
	   * gets the value of onGround.
	   * 
	   * @return onGround 
	   */
	  public boolean isOnGround() {
	    return onGround;
	  }
	  
	  /**
	   * get method for vx.
	   * 
	   * @return vx 
	   */
	  public double getVx() {
		return vx;
	  }

	  /**
	   * get method for vy.
	   * 
	   * @return vy 
	   */
	  public double getVy() {
		return vy;
	  }

	  /**
	   * sets vx to the value defined in the parameter.
	   * 
	   * @param vx
	   */
	  public void setVx(double vx) {
	    this.vx = vx;
	  }
	  
	  /**
	   * sets vy to the value defined in the parameter.
	   * 
	   * @param vy
	   */
	  public void setVy(double vy) {
	    this.vy = vy;
	  }

	  /**
	   * get method for x.
	   * 
	   * @return x
	   */
	 public double getX() {
		return x;
	 }

	 /**
	   * get method for y.
	   * 
	   * @return y 
	   */
	 public double getY() {
		return y;
	 }
	 
	 /**
	   * sets x to the value defined in the parameter.
	   * 
	   * @param y
	   */
	 public void setX(double x) {
		 this.x = x;	
	 }
		 
	 /**
	   * sets y to the value defined in the parameter.
	   * 
	   * @param y
	   */
	 public void setY(double y) {
		 this.y = y;
	 }

	 /**
	   * get method for width.
	   * 
	   * @return width 
	   */
	 public int getWidth() {
		return width;
	 }

	 /**
	   * get method for height.
	   * 
	   * @return height 
	   */
	 public int getHeight() {
		return height;
	 }

	 /**
	   * sets onGround to the value defined in the parameter.
	   * 
	   * @param onGround
	   */
	 public void setOnGround(boolean b) {
		this.onGround = b;
	 }
	 
	 /**
	   * initiates the player with the coordinates of the constructor.
	   * 
	   * @return new Rectangle
	   */
	 @Override
	 public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, width, height);
	 }
	  
}