package cs3500.processorcommands;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import cs3500.ImageUtil;
import cs3500.imagemodel.ImageProcessorModel;
import cs3500.imageview.ImageProcessorView;

/**
 * A command class to handle a save request.
 */
public class SaveImage extends ImageProcessorOperation {

  /**
   * The default constructor.
   *
   * @param sc    The scanner holding user input.
   * @param model The hashmap of images being used by the controller.
   * @param view  The view being used by the controller.
   */
  public SaveImage(Scanner sc, HashMap<String, ImageProcessorModel> model,
                   ImageProcessorView view) {
    super(sc, model, view);
  }

  @Override
  public void execute() {
    String path = sc.next();
    String imageName = sc.next();
    try {
      ImageUtil.buildImage(images.get(imageName), path, images.get(imageName).getExtension());
    } catch (IOException | NullPointerException e) {
      throw new IllegalStateException("Something went wrong.");
    }
    try {
      view.renderMessage("Saved image " + imageName + " to " + path + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Something went wrong.");
    }
  }
}
