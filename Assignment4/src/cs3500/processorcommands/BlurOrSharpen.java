package cs3500.processorcommands;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import cs3500.imagemodel.ImageProcessorModel;
import cs3500.imagemodel.ImageProcessorModelImpl;
import cs3500.imageview.ImageProcessorView;

/**
 * The command class to handle a blur or sharpen request.
 */
public class BlurOrSharpen extends ImageProcessorOperation {

  final String desired;

  /**
   * The default constructor.
   *
   * @param sc      The scanner that holds user input.
   * @param images  The hashmap of images for the controller.
   * @param view    The view being used by the controller.
   * @param desired The desired operation.
   */
  public BlurOrSharpen(Scanner sc, HashMap<String, ImageProcessorModel> images,
                       ImageProcessorView view, String desired) {
    super(sc, images, view);
    this.desired = desired;
  }

  @Override
  public void execute() {
    String imageName = sc.next();
    String destName = sc.next();
    ImageProcessorModel modified = new ImageProcessorModelImpl(images.get(imageName));
    modified.gaussianBlurOrSharpen(this.desired);
    images.put(destName, modified);
    String message;
    if (this.desired.equalsIgnoreCase("gaussian")) {
      message = "gaussian blurred";
    } else if (this.desired.equalsIgnoreCase("sharpen")) {
      message = "sharpened";
    } else {
      throw new IllegalStateException("Unexpected argument");
    }
    try {
      view.renderMessage("Created " + message + " image of " + imageName + " and " +
              "saved to image " + destName + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Something went wrong.");
    }
  }
}
