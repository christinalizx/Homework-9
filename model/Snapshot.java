package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Snapshot.
 */
public class Snapshot {
  private final LocalDateTime timestamp;
  private final String description;
  private final List<IShape> shapes;

  /**
   * Instantiates a new Snapshot.
   *
   * @param description the description
   * @param shapes      the shapes
   */
  public Snapshot(String description, List<IShape> shapes) {
    this.timestamp = LocalDateTime.now();
    this.description = description;
    this.shapes = new ArrayList<>(shapes);
  }

  /**
   * Gets timestamp.
   *
   * @return the timestamp
   */
  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  /**
   * Gets description.
   *
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Gets shape by name.
   *
   * @return the shape by name
   */
  public List<IShape>getShapes() {
    return new ArrayList<>(shapes);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Snapshot ID: ").append(timestamp).append("\n");
    sb.append("Timestamp: ").append(timestamp.format(DateTimeFormatter.ofPattern
            ("dd-MM-yyyy HH:mm:ss"))).append("\n");
    sb.append("Description: ").append(description).append("\n");
    sb.append("Shape Information:").append("\n");
    for (IShape shape : shapes) {
      sb.append(shape.toString()).append("\n");
    }
    return sb.toString();
  }
}
