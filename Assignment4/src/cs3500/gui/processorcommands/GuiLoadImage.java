package cs3500.gui.processorcommands;


import cs3500.imagemodel.ImageProcessorModel;
import cs3500.imagemodel.ImageProcessorModelImpl;
import cs3500.imageview.ImageProcessorView;

import java.io.IOException;
import java.util.HashMap;

/**
 * The command class that handles a load image request.
 */
public class GuiLoadImage extends GuiImageProcessorOperation {

  /**
   * The default constructor.
   *
   * @param params    The scanner holding user input.
   * @param model The hashmap of images being used by the controller.
   * @param view  The view being used by the controller.
   */
  public GuiLoadImage(String[] params, HashMap<String, ImageProcessorModel> model,
                      ImageProcessorView view) {
    super(params, model, view);
  }

  @Override
  public void execute() {
    String path = params[1];
    String imageName = params[2];
    String extension;
    int index = path.lastIndexOf('.');
    extension = path.substring(index + 1).toUpperCase();
    try {
      images.put(imageName, new ImageProcessorModelImpl(path, extension));
      view.renderMessage("Loaded " + extension + " image at " + path +
              " with name " + imageName + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Something went wrong.");
    } catch (IllegalArgumentException e) {
      try {
        view.renderMessage("File not found!\n");
      } catch (IOException i) {
        throw new IllegalStateException("Something went wrong.");
      }
    }
  }

}
