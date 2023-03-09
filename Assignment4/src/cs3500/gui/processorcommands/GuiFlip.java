package cs3500.gui.processorcommands;

import cs3500.imagemodel.ImageProcessorModel;
import cs3500.imagemodel.ImageProcessorModelImpl;
import cs3500.imageview.ImageProcessorView;

import java.io.IOException;
import java.util.HashMap;

/**
 * The command class to handle a flip request.
 */
public class GuiFlip extends GuiImageProcessorOperation {

  private final String desired;

  /**
   * The default constructor.
   *
   * @param params      The scanner holding user input.
   * @param model   The hashmap of images being used by the controller.
   * @param view    THe view being used by the controller.
   * @param desired The desired operation.
   */
  public GuiFlip(String[] params, HashMap<String, ImageProcessorModel> model,
                 ImageProcessorView view, String desired) {
    super(params, model, view);
    this.desired = desired;
  }

  @Override
  public void execute() {
    String imageName = params[1];
    String destName = params[2];
    ImageProcessorModel toBeFlipped = new ImageProcessorModelImpl(images.get(imageName));
    toBeFlipped.flipImage(desired);
    images.put(destName, toBeFlipped);
    String type = "";
    if (this.desired.equals("horizontal")) {
      type = "Horizontally";
    } else if (this.desired.equals("vertical")) {
      type = "Vertically";
    }
    try {
      view.renderMessage(type + " flipped image " + imageName + " and " + "saved to " +
              "image " + destName + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Something went wrong.");
    }
  }
}
