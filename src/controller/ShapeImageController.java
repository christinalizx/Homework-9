package controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import model.IShape;
import model.Oval;
import model.Rectangle;
import model.Snapshot;

public class ShapeImageController {

  private Map<Long, List<BufferedImage>> timestampToImages;

  public ShapeImageController() {
    timestampToImages = new HashMap<>();
  }

  public void applyShapes(List<Snapshot> snapshots) {
    // Loop over each snapshot
    for (int i = 0; i < snapshots.size(); i++) {
      Snapshot snapshot = snapshots.get(i);

      // Create a new canvas for this snapshot
      BufferedImage canvas = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
      Graphics2D g2d = canvas.createGraphics();
      g2d.setColor(Color.GRAY);
      g2d.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
      // Loop over each shape in this snapshot and draw it on the canvas
      for (IShape shape : snapshot.getShapes()) {
        if (shape instanceof Rectangle) {
          g2d.setColor(new Color((int) shape.getRed(), (int) shape.getGreen(), (int) shape.getBlue()));
          g2d.fillRect((int) shape.getX(), (int) shape.getY(), (int) shape.getWidthXR(), (int) shape.getHeightYR());
        } else if (shape instanceof Oval) {
          g2d.setColor(new Color((int) shape.getRed(), (int) shape.getGreen(), (int) shape.getBlue()));
          g2d.fillOval((int) shape.getX(), (int) shape.getY(), (int) shape.getWidthXR(), (int) shape.getHeightYR());
        }
      }

      // Save the canvas to a file with a filename based on the snapshot timestamp
      long timestamp = snapshot.getTimestamp().toEpochSecond(ZoneOffset.UTC);
      File outputFile = new File(snapshot.getTimestamp() + ".png");
      try {
        ImageIO.write(canvas, "png", outputFile);
      } catch (IOException e) {
        System.out.println("Failed to save image: " + e.getMessage());
      }
    }
  }
}
