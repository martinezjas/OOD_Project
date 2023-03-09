package cs3500.gui.imagecontroller;

import cs3500.imagemodel.ImageProcessorModel;

import java.util.Set;

/**
 * A class to represent the controller for the image processor system at GUI.
 * Accepts input and directs the model to perform actions based off of it.
 */
public interface GuiImageProcessorController {
  /**
   * A method that accepts input for the controller.
   */
  void operate(String... params);

  /**
   * get all image name keys.
   *
   * @return set of strings
   */
  Set<String> getKeys();

  /**
   * get the ImageProcessorModel by image key.
   *
   * @param imageKey a string
   * @return a image processor model
   */
  ImageProcessorModel getImageProcessorModel(String imageKey);
}
