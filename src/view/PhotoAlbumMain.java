package view;

import java.io.IOException;
import java.util.List;

import controller.ShapeImageController;
import model.ShapeModel;
import model.Snapshot;

/**
 * The type Photo album main.
 */
public class PhotoAlbumMain {
  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    try {
      String inputFileName = null;
      String outputFileName = null;
      String viewType = null;
      int xmax = 1000;
      int ymax = 1000;


      // Parse command-line arguments
      for (int i = 0; i < args.length; i++) {
        if (args[i].equals("-in")) {
          inputFileName = args[++i];
        } else if (args[i].equals("-out")) {
          outputFileName = args[++i];
        } else if (args[i].equals("-view") || args[i].equals("-v")) {
          viewType = args[++i];
        } else if (i == args.length - 2) {
          xmax = Integer.parseInt(args[i]);
          ymax = Integer.parseInt(args[i + 1]);
        }
      }
      if (inputFileName == null || viewType == null) {
        System.err.println("Usage: MyProgram -in \"input-file\" -view \"type-of-view\" " +
                "[-out \"where-output-should-go\"] [xmax] [ymax]");
        System.exit(1);
      }


      // Read the shape model file
      ShapeModel shapeModel = ShapeModelFileReader.read(inputFileName);
      List<Snapshot> snapshots = shapeModel.getSnapshots();
      for (Snapshot snapshot : snapshots) {
        System.out.println(snapshot);
      }

      // Use ShapeImageController to draw and save the images
      ShapeImageController imageController = new ShapeImageController();
      imageController.applyShapes(snapshots);

      if (viewType.equalsIgnoreCase("graphical")) {
        if (args.length == 4) {
          xmax = Integer.parseInt(args[2]);
          ymax = Integer.parseInt(args[3]);
        }
        Window window = new Window(snapshots, xmax, ymax);
      } else if (viewType.equalsIgnoreCase("web")) {
        HtmlView htmlView;
        if (outputFileName == null) {
          System.out.println("Warning: Output file name not provided for HTML view. " +
                  "Using default name \"output.html\"");
        } else {
          htmlView = new HtmlView(snapshots, xmax, ymax, outputFileName);
          htmlView.createHtmlFile();
        }
      } else {
        System.err.println("Error: Invalid view type.");
        System.exit(1);
      }


    } catch (IOException e) {
      e.printStackTrace();
    } catch (NumberFormatException e) {
      System.err.println("Error: Invalid value provided for xmax and/or ymax.");
      System.exit(1);
    }
  }
}
