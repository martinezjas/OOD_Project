package cs3500.gui.processorcommands;

import cs3500.imagemodel.ImageProcessorModel;
import cs3500.imagemodel.ImageProcessorModelImpl;
import cs3500.imageview.ImageProcessorView;

import java.io.IOException;
import java.util.HashMap;

/**
 * The command class to handle brighten or darken requests.
 */
public class GuiBrightenImage extends GuiImageProcessorOperation {
  /**
   * The default constructor.
   *
   * @param params     The scanner holding user input.
   * @param images The hashmap of images being used by the controller.
   * @param view   The view being used by the controller.
   */
  public GuiBrightenImage(String[] params, HashMap<String, ImageProcessorModel> images,
                          ImageProcessorView view) {
    super(params, images, view);
  }

  @Override
  public void execute() {
    int brightnessNum = Integer.valueOf(params[1]);
    String imageName = params[2];
    String destName = params[3];
    ImageProcessorModel toBeModified = new ImageProcessorModelImpl(images.get(imageName));
    if (brightnessNum < 0) {
      try {
        brightnessNum = Math.abs(brightnessNum);
        toBeModified.changeBrightness("d", brightnessNum);
        images.put(destName, toBeModified);
        view.renderMessage("Darkened image " + imageName + " by " + brightnessNum
                + " and saved to " + "image " + destName + "\n");
      } catch (IOException e) {
        throw new IllegalStateException("Something went wrong.");
      } catch (IllegalArgumentException e) {
        try {
          view.renderMessage("The color values was brought below 0. Please check your input " +
                  "and try again.\n");
        } catch (IOException i) {
          throw new IllegalStateException("Something went wrong.");
        }
      }
    } else if (brightnessNum > 0) {
      try {
        toBeModified.changeBrightness("b", brightnessNum);
        images.put(destName, toBeModified);
        view.renderMessage("Brightened image " + imageName + " by " + brightnessNum
                + " and saved to " + "image " + destName + "\n");
      } catch (IOException e) {
        throw new IllegalStateException("Something went wrong.");
      } catch (IllegalArgumentException e) {
        try {
          view.renderMessage("The color values exceeded the maximum. Please check your inputs" +
                  " and try again.\n");
        } catch (IOException i) {
          throw new IllegalStateException("Something went wrong.");
        }
      }
    } else {
      try {
        view.renderMessage("The number you have provided is invalid, try again. \n");
      } catch (IOException e) {
        throw new IllegalStateException("Something went wrong.");
      }
    }
  }
}
