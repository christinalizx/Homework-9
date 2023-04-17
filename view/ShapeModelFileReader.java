package view;

import model.IShape;
import model.ShapeModel;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * Reads a shape model file and populates a ShapeModel object with the contents.
 */
public class ShapeModelFileReader {

  /**
   * Reads a shape model file and returns a ShapeModel object containing the shapes defined in the file.
   *
   * @param filename the name of the file to read
   * @return a ShapeModel object containing the shapes defined in the file
   * @throws IOException if an I/O error occurs while reading the file
   */
  public static ShapeModel read(String filename) throws IOException {
    if (filename == null) {
      throw new IllegalArgumentException("File name cannot be null");
    }
    ShapeModel shapeModel = new ShapeModel();

    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = reader.readLine()) != null) {
        line = line.trim();
        if (line.startsWith("#") || line.isEmpty()) {
          // skip comments and empty lines
          continue;
        }
        String[] parts = line.split("\\s+");
        if (parts.length == 0) {
          continue;
        }
        String command = parts[0];
        switch (command.toLowerCase()) {
          case "shape":
            if (parts.length != 10) {
              throw new IllegalArgumentException("Invalid number of arguments for CreatePhoto command");
            }
            String name = parts[1];
            String type = parts[2];
            double x = Double.parseDouble(parts[3]);
            double y = Double.parseDouble(parts[4]);
            double heightYR = Double.parseDouble(parts[5]);
            double widthXR = Double.parseDouble(parts[6]);
            double red = Double.parseDouble(parts[7]);
            double green = Double.parseDouble(parts[8]);
            double blue = Double.parseDouble(parts[9]);
            shapeModel.createPhoto(name, type, x, y, heightYR, widthXR, red, green, blue);
            break;
          case "move":
            if (parts.length != 4) {
              throw new IllegalArgumentException("Invalid number of arguments for Move command");
            }
            String shapeName = parts[1];
            IShape shapeToMove = null;
            for (IShape shape : shapeModel.getShapes()) {
              if (shape.getName().equals(shapeName)) {
                shapeToMove = shape;
                break;
              }
            }
            if (shapeToMove == null) {
              throw new IllegalArgumentException("Shape not found");
            }
            double deltaX = Double.parseDouble(parts[2]);
            double deltaY = Double.parseDouble(parts[3]);
            shapeModel.move(shapeToMove, deltaX, deltaY);
            break;
          case "resize":
            if (parts.length != 4) {
              throw new IllegalArgumentException("Invalid number of arguments for Resize command");
            }
            String resizeName = parts[1];
            IShape shapeToResize = null;
            for (IShape shape : shapeModel.getShapes()) {
              if (shape.getName().equals(resizeName)) {
                shapeToResize = shape;
                break;
              }
            }
            if (shapeToResize == null) {
              throw new IllegalArgumentException("Shape not found");
            }
            double newWidthXR = Double.parseDouble(parts[2]);
            double newHeightYR = Double.parseDouble(parts[3]);
            shapeModel.resize(shapeToResize, newWidthXR, newHeightYR);
            break;
          case "color":
            String reColorName = parts[1];
            IShape shapeToReColor = null;
            for (IShape shape : shapeModel.getShapes()) {
              if (shape.getName().equals(reColorName)) {
                shapeToReColor = shape;
                break;
              }
            }
            if (shapeToReColor == null) {
              throw new IllegalArgumentException("Shape not found");
            }
            double newRed = Double.parseDouble(parts[2]);
            double newGreen = Double.parseDouble(parts[3]);
            double newBlue = Double.parseDouble(parts[4]);
            shapeModel.reColor(shapeToReColor, newRed, newGreen, newBlue);
            break;
          case "snapshot":
            if (parts.length < 1) {
              throw new IllegalArgumentException("Invalid number of arguments for Snapshot command");
            }
            StringBuilder descriptionBuilder = new StringBuilder();
            for (int i = 1; i < parts.length; i++) {
              descriptionBuilder.append(parts[i]);
              if (i != parts.length - 1) {
                descriptionBuilder.append(" ");
              }
            }
            String description = descriptionBuilder.toString();
            shapeModel.snapshot(description);
            break;
          case "remove":
            if (parts.length != 2) {
              throw new IllegalArgumentException("Invalid number of arguments for Snapshot command");
            }
            String removeName = parts[1];
            shapeModel.remove(removeName);
            break;
          default:
            throw new IllegalArgumentException("Invalid command: " + command);
        }
      }
    }

    return shapeModel;
  }

}
