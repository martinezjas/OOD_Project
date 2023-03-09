package cs3500.gui.processorcommands;

import cs3500.imagemodel.ImageProcessorModel;
import cs3500.imagemodel.ImageProcessorModelImpl;
import cs3500.imageview.ImageProcessorView;

import java.io.IOException;
import java.util.HashMap;

/**
 * The command class to handle a value component greyscale request.
 */
public class GuiValueGreyscale extends GuiImageProcessorOperation {

  /**
   * The default constructor.
   *
   * @param params    The scanner holding user input.
   * @param model The hashmap of images being used by the controller.
   * @param view  The view being used by the controller.
   */
  public GuiValueGreyscale(String[] params, HashMap<String, ImageProcessorModel> model,
                           ImageProcessorView view) {
    super(params, model, view);
  }

  @Override
  public void execute() {
    String imageName = params[1];
    String destName = params[2];
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
