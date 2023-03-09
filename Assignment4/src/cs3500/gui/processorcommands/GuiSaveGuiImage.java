package cs3500.gui.processorcommands;

import cs3500.ImageUtil;
import cs3500.imagemodel.ImageProcessorModel;
import cs3500.imageview.ImageProcessorView;

import java.io.IOException;
import java.util.HashMap;

/**
 * A command class to handle a save request.
 */
public class GuiSaveGuiImage extends GuiImageProcessorOperation {

  /**
   * The default constructor.
   *
   * @param params    The scanner holding user input.
   * @param model The hashmap of images being used by the controller.
   * @param view  The view being used by the controller.
   */
  public GuiSaveGuiImage(String[] params, HashMap<String, ImageProcessorModel> model,
                         ImageProcessorView view) {
    super(params, model, view);
  }

  @Override
  public void execute() {
    String path = params[1];
    String imageName = params[2];
    try {
      ImageUtil.buildImage(images.get(imageName), path, images.get(imageName).getExtension());
    } catch (IOException | NullPointerException e) {
      throw new IllegalStateException("Something went wrong.");
    }
    try {
      view.renderMessage("Saved image " + imageName + " to " + path + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Something went wrong.");
    }
  }
}
