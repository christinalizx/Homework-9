package model;

/**
 * The type Abstract shape.
 */
public class AbstractShape implements IShape, Cloneable {
  private String name;
  private String type;
  private double x;
  private double y;
  private double widthXR;
  private double heightYR;
  private double red;
  private double green;
  private double blue;

  /**
   * Instantiates a new Abstract shape.
   *
   * @param name     the name
   * @param type     the type
   * @param x        the x
   * @param y        the y
   * @param widthXR  the width xr
   * @param heightYR the height yr
   * @param red      the red
   * @param green    the green
   * @param blue     the blue
   */
  public AbstractShape(String name, String type, double x, double y,
                       double widthXR, double heightYR, double red, double green, double blue) {
    this.name = name;
    this.type = type;
    this.x = x;
    this.y = y;
    this.widthXR = widthXR;
    this.heightYR = heightYR;
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Gets name.
   *
   * @return the name
   */
  @Override
  public String getName() {
    return name;
  }


  /**
   * Sets color.
   *
   * @param red
   * @param green
   * @param blue
   * @return the color
   */
  @Override
  public void setColor(double red, double green, double blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Gets red.
   *
   * @return the red.
   */
  public double getRed() {
    return red;
  }

  /**
   * Gets green.
   *
   * @return the green.
   */
  public double getGreen() {
    return green;
  }

  /**
   * Gets blue.
   *
   * @return the blue
   */
  public double getBlue() {
    return blue;
  }


  /**
   * Move.
   *
   * @param dx the dx
   * @param dy the dy
   */
  @Override
  public void move(double dx, double dy) {
    this.x = dx;
    this.y = dy;
  }

  /**
   * Resize.
   *
   * @param XRwidth
   * @param YRheight
   */
  @Override
  public void resize(double XRwidth, double YRheight) {
    this.widthXR = XRwidth;
    this.heightYR = YRheight;
  }

  /**
   * Gets x.
   *
   * @return the x
   */
  @Override
  public double getX() {
    return x;
  }

  /**
   * Gets y.
   *
   * @return the y
   */
  @Override
  public double getY() {
    return y;
  }

  /**
   * Gets width xr.
   *
   * @return the width xr
   */
  @Override
  public double getWidthXR() {
    return widthXR;
  }

  /**
   * Gets height yr.
   *
   * @return the height yr
   */
  @Override
  public double getHeightYR() {
    return heightYR;
  }

  /**
   * Sets the new min corner/center.
   */
  @Override
  public void set(double newX, double newY) {
    this.x = newX;
    this.y = newY;
  }

}
