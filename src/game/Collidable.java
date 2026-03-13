package game;

import java.awt.Rectangle;

interface Collidable {
  /**
   * Returns the collision boundary of the object.
   *
   * The returned rectangle is used to determine whether this
   * object collides with another object.
   *
   * @return a Rectangle representing the object's bounds
   */
  Rectangle getBounds();
}
