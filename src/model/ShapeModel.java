package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The type Photo album.
 */
public class ShapeModel implements Cloneable{
  private List<IShape> shapes;
  private List<Snapshot> snapshots;

  /**
   * Instantiates a new Photo album.
   */
  public ShapeModel() {
    shapes = new ArrayList<>();
    snapshots = new ArrayList<>();
  }

  /**
   * Create photo.
   *
   * @param name     the name
   * @param type     the type
   * @param x        the x
   * @param y        the y
   * @param widthXR  the width yr
   * @param heightYR the height xr
   * @param red      the red
   * @param green    the green
   * @param blue     the blue
   * @throws IllegalArgumentException the illegal argument exception
   */
  public void createPhoto(String name, String type, double x, double y, double widthXR,
                          double heightYR, double red, double green, double blue)
          throws IllegalArgumentException {
    if (type == null || heightYR < 0 || widthXR < 0 || red < 0 || green < 0 || blue < 0)
      throw new IllegalArgumentException();
    IShape shape;
    switch (type.toLowerCase()) {
      case "rectangle":
        shape = new Rectangle(name, type, x, y, widthXR, heightYR, red, green, blue);
        break;
      case "oval":
        shape = new Oval(name, type, x, y, widthXR, heightYR, red, green, blue);
        break;
      default:
        shape = null;
    }
    if (shape != null) {
      shapes.add(shape);
    }
  }

  /**
   * Move.
   *
   * @param shape  the shape
   * @param deltaX the delta x
   * @param deltaY the delta y
   */
  public void move(IShape shape, double deltaX, double deltaY) {
    if (shape == null) {
      throw new IllegalArgumentException();
    }
    if (!shapes.contains(shape)) {
      throw new IllegalArgumentException("Shape has not been created");
    }
    shape.move(deltaX, deltaY);
  }

  /**
   * Resize.
   *
   * @param shape    the shape
   * @param widthXR  the width xr
   * @param heightYR the height yr
   */
  public void resize(IShape shape, double widthXR, double heightYR) {
    if (shape == null) {
      throw new IllegalArgumentException();
    }
    if (!shapes.contains(shape)) {
      throw new IllegalArgumentException("Shape has not been created");
    }
    shape.resize(widthXR, heightYR);
  }

  /**
   * Re color.
   *
   * @param shape the shape
   * @param red   the red
   * @param green the green
   * @param blue  the blue
   */
  public void reColor(IShape shape, double red, double green, double blue) {
    if (!shapes.contains(shape)) {
      throw new IllegalArgumentException("Shape has not been created");
    }
    shape.setColor(red, green, blue);
  }

  /**
   * Reset.
   */
  public void reset() {
    this.shapes.clear();
    this.snapshots.clear();
  }

  /**
   * Snapshot list.
   *
   * @param description the description
   * @return the list
   */
  public List<Snapshot> snapshot(String description) {
    if (description == null) {
      throw new IllegalArgumentException();
    }
    List<IShape> snapshotShapes = new ArrayList<>();
    for (IShape shape: shapes) {
      if (shape instanceof Rectangle) {
        snapshotShapes.add(new Rectangle(shape.getName(),"rectangle", shape.getX(),
                shape.getY(), shape.getWidthXR(), shape.getHeightYR(), shape.getRed(),
                shape.getGreen(), shape.getBlue()));
      } else if (shape instanceof Oval) {
        snapshotShapes.add(new Oval(shape.getName(), "oval", shape.getX(),
                shape.getY(), shape.getWidthXR(), shape.getHeightYR(), shape.getRed(),
                shape.getGreen(), shape.getBlue()));
      }
    }
    snapshots.add(new Snapshot(description, snapshotShapes));
    return new ArrayList<>(snapshots);
  }

  /**
   * Gets shape string.
   *
   * @return the shape string
   */
  public String getShapeString() {
    StringBuilder sb1 = new StringBuilder();
    for (IShape shape : shapes) {
      sb1.append(shape.toString());
    }
    return sb1.toString();
  }


  /**
   * Remove.
   *
   * @param name the name
   */
  public void remove(String name) {
    IShape shapeToRemove = null;
    for (IShape shape : shapes) {
      if (shape.getName().equals(name)) {
        shapeToRemove = shape;
        break;
      }
    }
    if (shapeToRemove != null) {
      shapes.remove(shapeToRemove);
    }
  }

  /**
   * Gets shapes.
   *
   * @return the shapes
   */
  public List<IShape> getShapes() {
    return shapes;
  }

  /**
   * Gets snapshots.
   *
   * @return the snapshots
   */
  public List<Snapshot> getSnapshots() {
    return snapshots;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Printing Snapshots\n");
    for (Snapshot snapshot : snapshots) {
      sb.append(snapshot.toString());
    }
    return sb.toString();
  }
}