package cs3500.gui.processorcommands;

import cs3500.imagemodel.ImageProcessorModel;
import cs3500.imagemodel.ImageProcessorModelImpl;
import cs3500.imageview.ImageProcessorView;

import java.io.IOException;
import java.util.HashMap;

/**
 * The command class to handle a blur or sharpen request.
 */
public class GuiBlurOrSharpen extends GuiImageProcessorOperation {

  final String desired;

  /**
   * The default constructor.
   *
   * @param params      The scanner that holds user input.
   * @param images  The hashmap of images for the controller.
   * @param view    The view being used by the controller.
   * @param desired The desired operation.
   */
  public GuiBlurOrSharpen(String[] params, HashMap<String, ImageProcessorModel> images,
                          ImageProcessorView view, String desired) {
    super(params, images, view);
    this.desired = desired;
  }

  @Override
  public void execute() {
    String imageName = params[1];
    String destName = params[2];
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
