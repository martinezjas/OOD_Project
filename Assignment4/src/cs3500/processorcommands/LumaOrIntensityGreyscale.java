package cs3500.processorcommands;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import cs3500.imagemodel.ImageProcessorModel;
import cs3500.imagemodel.ImageProcessorModelImpl;
import cs3500.imageview.ImageProcessorView;

/**
 * The command class that handles a luma and intensity component greyscale request.
 */
public class LumaOrIntensityGreyscale extends ImageProcessorOperation {

  private final String desired;

  /**
   * The default constructor.
   *
   * @param sc      The scanner holding user input.
   * @param model   The hashmap of images being used by the controller.
   * @param view    The view being used by the controller.
   * @param desired The desired operation.
   */
  public LumaOrIntensityGreyscale(Scanner sc, HashMap<String, ImageProcessorModel> model,
                                  ImageProcessorView view, String desired) {
    super(sc, model, view);
    this.desired = desired;
  }

  @Override
  public void execute() {
    String imageName = sc.next();
    String destName = sc.next();
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
