package cs3500.processorcommands;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import cs3500.imagemodel.ImageProcessorModel;
import cs3500.imagemodel.ImageProcessorModelImpl;
import cs3500.imageview.ImageProcessorView;

/**
 * The command class to handle brighten or darken requests.
 */
public class BrightenImage extends ImageProcessorOperation {
  /**
   * The default constructor.
   *
   * @param sc     The scanner holding user input.
   * @param images The hashmap of images being used by the controller.
   * @param view   The view being used by the controller.
   */
  public BrightenImage(Scanner sc, HashMap<String, ImageProcessorModel> images,
                       ImageProcessorView view) {
    super(sc, images, view);
  }

  @Override
  public void execute() {
    int brightnessNum = sc.nextInt();
    String imageName = sc.next();
    String destName = sc.next();
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
