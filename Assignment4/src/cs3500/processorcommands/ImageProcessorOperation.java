package cs3500.processorcommands;

import java.util.HashMap;
import java.util.Scanner;

import cs3500.imagemodel.ImageProcessorModel;
import cs3500.imageview.ImageProcessorView;

/**
 * An abstract class representing IPC commands.
 */
public abstract class ImageProcessorOperation {

  protected final Scanner sc;
  protected final HashMap<String, ImageProcessorModel> images;
  protected final ImageProcessorView view;

  /**
   * The default construcotr.
   *
   * @param sc     The scanner holding user input.
   * @param images The hashmap of images being used by the controller.
   * @param view   The view being used by the controller.
   */
  public ImageProcessorOperation(Scanner sc, HashMap<String, ImageProcessorModel> images,
                                 ImageProcessorView view) {
    this.sc = sc;
    this.images = images;
    this.view = view;
  }

  public abstract void execute();

}
