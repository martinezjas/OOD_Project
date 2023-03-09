package cs3500.gui.processorcommands;

import cs3500.imagemodel.ImageProcessorModel;
import cs3500.imageview.ImageProcessorView;

import java.util.HashMap;

/**
 * An abstract class representing IPC commands.
 */
public abstract class GuiImageProcessorOperation {

  protected final String[] params;
  protected final HashMap<String, ImageProcessorModel> images;
  protected final ImageProcessorView view;

  /**
   * The default construcotr.
   *
   * @param  params    holding user input from GUI.
   * @param images The hashmap of images being used by the controller.
   * @param view   The view being used by the controller.
   */
  public GuiImageProcessorOperation(String[] params, HashMap<String, ImageProcessorModel> images,
                                    ImageProcessorView view) {
    this.params = params;
    this.images = images;
    this.view = view;
  }

  public abstract void execute();

}
