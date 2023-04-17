package model;

/**
 * The type Oval.
 */
public class Oval extends AbstractShape {

  /**
   * Instantiates a new Oval.
   *
   * @param name    the name
   * @param type    the type
   * @param centerX the center x
   * @param centerY the center y
   * @param xRadius the x radius
   * @param yRadius the y radius
   * @param red     the red
   * @param green   the green
   * @param blue    the blue
   */
  public Oval(String name, String type, double centerX, double centerY,
              double xRadius, double yRadius, double red, double green, double blue) {
    super(name, type, centerX, centerY, xRadius, yRadius, red, green, blue);
  }


  @Override
  public String toString() {
    return String.format("Name: %s\nType: oval\nCenter: (%.1f, %.1f), X radius: %.1f, Y radius: %.1f,"
                    + " Color: (%.1f,%.1f,%.1f)\n",
            this.getName(), this.getX(), this.getY(), this.getWidthXR(),
            this.getHeightYR(), this.getRed(), this.getGreen(), this.getBlue());
  }
}
