package model;

/**
 * The type Rectangle.
 */
public class Rectangle extends AbstractShape {

  /**
   * Instantiates a new Rectangle.
   *
   * @param name     the name
   * @param type     the type
   * @param widthXR  the width xr
   * @param heightYR the height yr
   * @param x        the x
   * @param y        the y
   * @param red      the red
   * @param green    the green
   * @param blue     the blue
   */
  public Rectangle(String name, String type, double x, double y, double widthXR,
                   double heightYR, double red, double green, double blue) {
    super(name, type, x, y, widthXR, heightYR, red, green, blue);
  }

  @Override
  public String toString() {
    return String.format("Name: %s\nType: rectangle\nMin corner: (%.1f, %.1f), width: %.1f, height: %.1f,"
                    + " Color: (%.1f,%.1f,%.1f)\n",
            this.getName(), this.getX(), this.getY(), this.getWidthXR(),
            this.getHeightYR(), this.getRed(), this.getGreen(), this.getBlue());
  }
}

