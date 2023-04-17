package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZoneOffset;
import java.util.List;

import javax.imageio.ImageIO;

import model.IShape;
import model.Oval;
import model.Rectangle;
import model.Snapshot;

/**
 * The type Html view.
 */
public class HtmlView {

  private List<Snapshot> snapshots;
  private int xmax;
  private int ymax;
  private String outputFileName;

  /**
   * Instantiates a new Html view.
   *
   * @param snapshots      the snapshots
   * @param xmax           the xmax
   * @param ymax           the ymax
   * @param outputFileName the output file name
   */
  public HtmlView(List<Snapshot> snapshots, int xmax, int ymax, String outputFileName) {
    this.snapshots = snapshots;
    this.xmax = xmax;
    this.ymax = ymax;
    this.outputFileName = outputFileName;
  }

  /**
   * Create html file.
   */
  public void createHtmlFile() {
    try {
      FileWriter fw = new FileWriter(outputFileName);
      fw.write("<html>\n<head>\n<title>Snapshot Album</title>\n<style>\n");
      fw.write("svg { border: 1px solid pink; }\n");
      fw.write("</style>\n</head>\n<body>\n");

      for (Snapshot snapshot : snapshots) {
        fw.write("<h3>Snapshot ID: " + snapshot.getTimestamp() + "</h3>");
        fw.write("<h4> " + snapshot.getDescription() + "</h4>");
        fw.write("<svg width=\"" + (xmax + 2) + "\" height=\"" + (ymax + 2) + "\">");
        // Draw the shapes in the snapshot
        for (IShape shape : snapshot.getShapes()) {
          if (shape instanceof Rectangle) {
            fw.write("<rect x=\"" + shape.getX() + "\" y=\"" + shape.getY() + "\" width=\"" +
                    shape.getWidthXR() + "\" height=\"" + shape.getHeightYR() +
                    "\" fill=\"rgb(" + (int) shape.getRed() + "," + (int) shape.getGreen() + "," +
                    (int) shape.getBlue() + ")\" />\n");
          } else if (shape instanceof Oval) {
            fw.write("<ellipse cx=\"" + shape.getX() + "\" cy=\"" + shape.getY() +
                    "\" rx=\"" + shape.getWidthXR() + "\" ry=\"" + shape.getHeightYR() +
                    "\" fill=\"rgb(" + (int) shape.getRed() + "," + (int) shape.getGreen() + "," +
                    (int) shape.getBlue() + ")\" />\n");
          }
        }
        fw.write("</svg>\n");

        // Embed the snapshot image
        long timestamp = snapshot.getTimestamp().toEpochSecond(ZoneOffset.UTC);
        File imageFile = new File(timestamp + ".png");
        if (imageFile.exists()) {
          BufferedImage image = ImageIO.read(imageFile);
          fw.write("<img src=\"" + imageFile.getName() + "\" />\n");
        }
      }

      fw.write("</body>\n</html>");
      fw.close();
    } catch (IOException e) {
      System.out.println("Failed to create HTML file: " + e.getMessage());
    }
  }
}
