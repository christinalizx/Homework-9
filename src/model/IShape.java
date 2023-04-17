package model;

/**
 * The interface Shape.
 */
public interface IShape {
  /**
   * Gets name.
   *
   * @return the name
   */
  String getName();


  /**
   * Sets color.
   *
   * @param red   the red
   * @param green the green
   * @param blue  the blue
   */
  void setColor(double red, double green, double blue);


  /**
   * Move.
   *
   * @param dx the dx
   * @param dy the dy
   */
  void move(double dx, double dy);

  /**
   * Resize.
   *
   * @param XRwidth  the x rwidth
   * @param YRheight the y rheight
   */
  void resize(double XRwidth, double YRheight);

  /**
   * Gets x.
   *
   * @return the x
   */
  double getX();

  /**
   * Gets y.
   *
   * @return the y
   */
  double getY();

  /**
   * Gets width xr.
   *
   * @return the width xr
   */
  double getWidthXR();

  /**
   * Gets height yr.
   *
   * @return the height yr
   */
  double getHeightYR();

  /**
   * Set.
   *
   * @param newX the new x
   * @param newY the new y
   */
  void set(double newX, double newY);

  /**
   * Gets red.
   *
   * @return the red
   */
  double getRed();

  /**
   * Gets green.
   *
   * @return the green
   */
  double getGreen();

  /**
   * Gets blue.
   *
   * @return the blue
   */
  double getBlue();
}
