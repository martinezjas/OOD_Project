package cs3500.gui.imagecontroller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import cs3500.gui.processorcommands.ComponentGreyscale;
import cs3500.gui.processorcommands.GuiBlurOrSharpen;
import cs3500.gui.processorcommands.GuiBrightenImage;
import cs3500.gui.processorcommands.GuiFlip;
import cs3500.gui.processorcommands.GuiLoadImage;
import cs3500.gui.processorcommands.GuiLumaOrIntensityGreyscale;
import cs3500.gui.processorcommands.GuiSaveGuiImage;
import cs3500.gui.processorcommands.GuiSepiaOrSimpleGreyscaleImage;
import cs3500.gui.processorcommands.GuiValueGreyscale;
import cs3500.imagemodel.ImageProcessorModel;
import cs3500.imageview.ImageProcessorView;
import cs3500.imageview.ImageProcessorViewImpl;

/**
 * Class implementation By GUI for an GuiImageProcessorController.
 */
public class GuiImageProcessorControllerImpl implements GuiImageProcessorController {
  private final HashMap<String, ImageProcessorModel> images;
  private final ImageProcessorView view;

  /**
   * A public constructor for a GuiImageProcessorController. Creates a Hashmap, with user-defined
   * strings being image name keys, and IPMs, ready to be modified, being values.
   */
  public GuiImageProcessorControllerImpl() {
    this.images = new HashMap<>();
    Appendable out = System.out;
    view = new ImageProcessorViewImpl(out);
  }

  /**
   * Implementation of the methods.
   *
   * @param params String ...
   */
  @Override
  public void operate(String... params) {
    String whatFunction = params[0];
    try {
      switch (whatFunction) { // TODO: Transition to command pattern design
        case "load": { //handles the function to load an image
          new GuiLoadImage(params, this.images, this.view).execute();
          break;
        }
        case "save": { //handles the function to save an image
          new GuiSaveGuiImage(params, this.images, this.view).execute();
          break;
        }
        case "red-component": { //handles the function to greyscale via red component
          new ComponentGreyscale(params, this.images, this.view, "red").execute();
          break;
        }
        case "green-component": { //handles the function to greyscale via green component
          new ComponentGreyscale(params, this.images, this.view, "green").execute();
          break;
        }
        case "blue-component": {
          new ComponentGreyscale(params, this.images, this.view, "blue").execute();
          break;
        }
        case "value-component": {
          new GuiValueGreyscale(params, this.images, this.view).execute();
          break;
        }
        case "luma-component": {
          new GuiLumaOrIntensityGreyscale(params, this.images,
                  this.view, "luma").execute();
          break;
        }
        case "intensity-component": {
          new GuiLumaOrIntensityGreyscale(params, this.images,
                  this.view, "intensity").execute();
          break;
        }
        case "horizontal-flip": {
          new GuiFlip(params, this.images, this.view, "horizontal").execute();
          break;
        }
        case "vertical-flip": {
          new GuiFlip(params, this.images, this.view, "vertical").execute();
          break;
        }
        case "brighten": {
          new GuiBrightenImage(params, this.images, this.view).execute();
          break;
        }
        case "blur": {
          new GuiBlurOrSharpen(params, this.images, this.view, "gaussian").execute();
          break;
        }
        case "sharpen": {
          new GuiBlurOrSharpen(params, this.images, this.view, "sharpen").execute();
          break;
        }
        case "greyscale": {
          new GuiSepiaOrSimpleGreyscaleImage(params, this.images, this.view,
                  "greyscale").execute();
          break;
        }
        case "sepia": {
          new GuiSepiaOrSimpleGreyscaleImage(params, this.images, this.view,
                  "sepia").execute();
          break;
        }
        default: {
          //no action needed.
        }
      }
    } catch (RuntimeException e) {
      try {
        view.renderMessage(e.getMessage() + "\n");
      } catch (IOException io) {
        throw new IllegalStateException("Something went wrong.");
      }
    }
  }

  /**
   * Implemention of the mothod getKeys.
   */
  @Override
  public Set<String> getKeys() {
    return images.keySet();
  }

  /**
   * get the ImageProcessorModel by key of image.
   *
   * @param imageKey String
   * @return image key
   */
  @Override
  public ImageProcessorModel getImageProcessorModel(String imageKey) {
    if (images == null || images.isEmpty()) {
      return null;
    }
    return images.get(imageKey);
  }
}
