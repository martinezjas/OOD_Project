package cs3500.gui.processorcommands;

import cs3500.imagemodel.ImageProcessorModel;
import cs3500.imagemodel.ImageProcessorModelImpl;
import cs3500.imageview.ImageProcessorView;

import java.io.IOException;
import java.util.HashMap;

/**
 * The command class to handle component greyscale requests.
 */
public class ComponentGreyscale extends GuiImageProcessorOperation {

  final String color;

  /**
   * The default constructor.
   *
   * @param sc    The scanner holding user input.
   * @param model The hashmap of images being used by the controller.
   * @param view  The view being used by the controller.
   * @param color The color component desired.
   */
  public ComponentGreyscale(String[] sc, HashMap<String, ImageProcessorModel> model,
                            ImageProcessorView view, String color) {
    super(sc, model, view);
    this.color = color;
  }

  @Override
  public void execute() {
    String imageName = params[1];
    String destName = params[2];
    ImageProcessorModel modified = new ImageProcessorModelImpl(images.get(imageName));
    modified.getColorComponent(color);
    images.put(destName, modified);
    try {
      view.renderMessage("Created " + color + " component greyscale of " + imageName + " and " +
              "saved to image " + destName + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Something went wrong.");
    }
  }
}
