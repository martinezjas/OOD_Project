package cs3500.processorcommands;


import cs3500.imagemodel.ImageProcessorModel;
import cs3500.imagemodel.ImageProcessorModelImpl;
import cs3500.imageview.ImageProcessorView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * The command class that handles a load image request.
 */
public class LoadImage extends ImageProcessorOperation {

  /**
   * The default constructor.
   *
   * @param sc    The scanner holding user input.
   * @param model The hashmap of images being used by the controller.
   * @param view  The view being used by the controller.
   */
  public LoadImage(Scanner sc, HashMap<String, ImageProcessorModel> model,
                   ImageProcessorView view) {
    super(sc, model, view);
  }

  @Override
  public void execute() {
    String path = sc.next();
    String imageName = sc.next();
    String extension;
    int index = path.lastIndexOf('.');
    extension = path.substring(index + 1).toUpperCase();
    try {
      images.put(imageName, new ImageProcessorModelImpl(path, extension));
      view.renderMessage("Loaded " + extension + " image at " + path +
              " with name " + imageName + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Something went wrong.");
    } catch (IllegalArgumentException e) {
      try {
        view.renderMessage("File not found!\n");
      } catch (IOException i) {
        throw new IllegalStateException("Something went wrong.");
      }
    }
  }

}
