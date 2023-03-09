package cs3500.processorcommands;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import cs3500.imagemodel.ImageProcessorModel;
import cs3500.imagemodel.ImageProcessorModelImpl;
import cs3500.imageview.ImageProcessorView;

/**
 * The command class to handle a value component greyscale request.
 */
public class ValueGreyscale extends ImageProcessorOperation {

  /**
   * The default constructor.
   *
   * @param sc    The scanner holding user input.
   * @param model The hashmap of images being used by the controller.
   * @param view  The view being used by the controller.
   */
  public ValueGreyscale(Scanner sc, HashMap<String, ImageProcessorModel> model,
                        ImageProcessorView view) {
    super(sc, model, view);
  }

  @Override
  public void execute() {
    String imageName = sc.next();
    String destName = sc.next();
    ImageProcessorModel toBeValued = new ImageProcessorModelImpl(images.get(imageName));
    toBeValued.getValueComponent();
    images.put(destName, toBeValued);
    try {
      view.renderMessage("Created value component greyscale of " + imageName + " and saved to"
              + " image " + destName + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Something went wrong.");
    }
  }
}
