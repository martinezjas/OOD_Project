package cs3500.processorcommands;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import cs3500.imagemodel.ImageProcessorModel;
import cs3500.imagemodel.ImageProcessorModelImpl;
import cs3500.imageview.ImageProcessorView;

/**
 * The command class to handle sepia or simple greyscale requests.
 */
public class SepiaOrSimpleGreyscaleImage extends ImageProcessorOperation {

  private final String desired;

  /**
   * The default constructor.
   *
   * @param sc      The scanner holding user input.
   * @param images  The hashmap of images being used by the controller.
   * @param view    The view being used by the controller.
   * @param desired The desired operation.
   */
  public SepiaOrSimpleGreyscaleImage(Scanner sc, HashMap<String, ImageProcessorModel> images,
                                     ImageProcessorView view, String desired) {
    super(sc, images, view);
    this.desired = desired;
  }

  @Override
  public void execute() {
    String imageName = sc.next();
    String destName = sc.next();
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
