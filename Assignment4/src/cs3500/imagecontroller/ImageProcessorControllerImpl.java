package cs3500.imagecontroller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import cs3500.imagemodel.ImageProcessorModel;
import cs3500.imageview.ImageProcessorView;
import cs3500.processorcommands.BlurOrSharpen;
import cs3500.processorcommands.BrightenImage;
import cs3500.processorcommands.ComponentGreyscale;
import cs3500.processorcommands.Flip;
import cs3500.processorcommands.ImageProcessorInvoker;
import cs3500.processorcommands.LoadImage;
import cs3500.processorcommands.LumaOrIntensityGreyscale;
import cs3500.processorcommands.SaveImage;
import cs3500.processorcommands.SepiaOrSimpleGreyscaleImage;
import cs3500.processorcommands.ValueGreyscale;

/**
 * Class implementation for an ImageProcessorController.
 */
public class ImageProcessorControllerImpl implements ImageProcessorController {
  private final HashMap<String, ImageProcessorModel> images;
  private final ImageProcessorView view;
  private final Readable readable;
  private final Scanner userScanner;
  private boolean running;

  /**
   * A public constructor for a ImageProcessorController. Creates a Hashmap, with user-defined
   * strings being image name keys, and IPMs, ready to be modified, being values.
   *
   * @param view     A ImageProcessorView to render command line prompts.
   * @param readable A readable object to accept input.
   * @throws IllegalArgumentException if any of the above are null.
   */
  public ImageProcessorControllerImpl(ImageProcessorView view, Readable readable)
          throws IllegalArgumentException {
    if ((view != null) && (readable != null)) {
      this.images = new HashMap<>();
      this.view = view;
      this.readable = readable;
      this.running = true;
      this.userScanner = new Scanner(readable);
    } else {
      throw new IllegalArgumentException("One or more inputs are null. Please check your inputs"
              + " and try again.");
    }
  }

  /**
   * A method that accepts input for the controller.
   */
  public void acceptInput() {
    try {
      view.renderMessage("Welcome to this image processor.\n");
    } catch (IOException e) {
      throw new IllegalStateException("Something went wrong.");
    }

    while (this.running) {
      acceptInputHelper(userScanner);
    }
  }

  /**
   * A helper method that executes commands on behalf of the acceptInput method.
   *
   * @param sc A scanner that contains user input.
   */
  private void acceptInputHelper(Scanner sc) {
    ImageProcessorInvoker invoker = new ImageProcessorInvoker();
    String whatFunction = sc.next();
    try {
      switch (whatFunction) { // TODO: Transition to command pattern design
        case "load": { //handles the function to load an image
          invoker.executeOperation(new LoadImage(sc, this.images, this.view));
          break;
        }
        case "save": { //handles the function to save an image
          invoker.executeOperation(new SaveImage(sc, this.images, this.view));
          break;
        }
        case "red-component": { //handles the function to greyscale via red component
          invoker.executeOperation(new ComponentGreyscale(sc, this.images, this.view, "red"));
          break;
        }
        case "green-component": { //handles the function to greyscale via green component
          invoker.executeOperation(new ComponentGreyscale(sc, this.images, this.view, "green"));
          break;
        }
        case "blue-component": {
          invoker.executeOperation(new ComponentGreyscale(sc, this.images, this.view, "blue"));
          break;
        }
        case "value-component": {
          invoker.executeOperation(new ValueGreyscale(sc, this.images, this.view));
          break;
        }
        case "luma-component": {
          invoker.executeOperation(new LumaOrIntensityGreyscale(sc, this.images,
                  this.view, "luma"));
          break;
        }
        case "intensity-component": {
          invoker.executeOperation(new LumaOrIntensityGreyscale(sc, this.images,
                  this.view, "intensity"));
          break;
        }
        case "horizontal-flip": {
          invoker.executeOperation(new Flip(sc, this.images, this.view, "horizontal"));
          break;
        }
        case "vertical-flip": {
          invoker.executeOperation(new Flip(sc, this.images, this.view, "vertical"));
          break;
        }
        case "brighten": {
          invoker.executeOperation(new BrightenImage(sc, this.images, this.view));
          break;
        }
        case "blur": {
          invoker.executeOperation(new BlurOrSharpen(sc, this.images, this.view, "gaussian"));
          break;
        }
        case "sharpen": {
          invoker.executeOperation(new BlurOrSharpen(sc, this.images, this.view, "sharpen"));
          break;
        }
        case "greyscale": {
          invoker.executeOperation(new SepiaOrSimpleGreyscaleImage(sc, this.images, this.view,
                  "greyscale"));
          break;
        }
        case "sepia": {
          invoker.executeOperation(new SepiaOrSimpleGreyscaleImage(sc, this.images, this.view,
                  "sepia"));
          break;
        }
        case "quit":
        case "q": {
          try {
            view.renderMessage("Thank you. Goodbye.\n");
          } catch (IOException e) {
            throw new IllegalStateException("Something went wrong.");
          }
          this.running = false;
          System.exit(0);
          break;
        }
        case "debug": {
          System.out.println(System.getProperty("user.dir"));
          break;
        }
        default: {
          try {
            view.renderMessage("Please enter a valid command.\n");
          } catch (IOException e) {
            throw new IllegalStateException("Something went wrong.");
          }
        }
          sc.nextLine();
      }
    } catch (RuntimeException e) {
      try {
        view.renderMessage(e.getMessage() + "\n");
      } catch (IOException io) {
        throw new IllegalStateException("Something went wrong.");
      }
    }
  }
}
