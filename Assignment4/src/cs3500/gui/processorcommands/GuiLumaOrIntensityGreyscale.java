package cs3500.gui.processorcommands;

import cs3500.imagemodel.ImageProcessorModel;
import cs3500.imagemodel.ImageProcessorModelImpl;
import cs3500.imageview.ImageProcessorView;

import java.io.IOException;
import java.util.HashMap;

/**
 * The command class that handles a luma and intensity component greyscale request.
 */
public class GuiLumaOrIntensityGreyscale extends GuiImageProcessorOperation {

  private final String desired;

  /**
   * The default constructor.
   *
   * @param sc      The scanner holding user input.
   * @param model   The hashmap of images being used by the controller.
   * @param view    The view being used by the controller.
   * @param desired The desired operation.
   */
  public GuiLumaOrIntensityGreyscale(String[] sc, HashMap<String, ImageProcessorModel> model,
                                     ImageProcessorView view, String desired) {
    super(sc, model, view);
    this.desired = desired;
  }

  @Override
  public void execute() {
    String imageName = params[1];
    String destName = params[2];
    ImageProcessorModel modified = new ImageProcessorModelImpl(images.get(imageName));
    modified.getLumaOrIntensityComponent(desired);
    images.put(destName, modified);
    try {
      view.renderMessage("Created " + desired + " component greyscale of " + imageName + " and " +
              "saved to image " + destName + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Something went wrong.");
    }
  }

}
