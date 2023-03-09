package cs3500.gui.processorcommands;

import java.io.IOException;
import java.util.HashMap;

import cs3500.imagemodel.ImageProcessorModel;
import cs3500.imagemodel.ImageProcessorModelImpl;
import cs3500.imageview.ImageProcessorView;

/**
 * The command class to handle sepia or simple greyscale requests.
 */
public class GuiSepiaOrSimpleGreyscaleImage extends GuiImageProcessorOperation {

  private final String desired;

  /**
   * The default constructor.
   *
   * @param params  The scanner holding user input.
   * @param images  The hashmap of images being used by the controller.
   * @param view    The view being used by the controller.
   * @param desired The desired operation.
   */
  public GuiSepiaOrSimpleGreyscaleImage(String[] params, HashMap<String,
          ImageProcessorModel> images, ImageProcessorView view, String desired) {
    super(params, images, view);
    this.desired = desired;
  }

  @Override
  public void execute() {
    String imageName = params[1];
    String destName = params[2];
    ImageProcessorModel modified = new ImageProcessorModelImpl(images.get(imageName));
    modified.simpleGreyscaleOrSepia(desired);
    images.put(destName, modified);
    try {
      view.renderMessage("Created " + desired + " image copy of " + imageName + " and " +
              "saved to image " + destName + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Something went wrong.");
    }
  }
}
