package cs3500.imagemodel;

import java.io.IOException;

import cs3500.ImageUtil;
import cs3500.modelcommands.BrightenOrDarken;
import cs3500.modelcommands.ColorComponent;
import cs3500.modelcommands.FlipImage;
import cs3500.modelcommands.GaussianOrSharpen;
import cs3500.modelcommands.GreyscaleOrSepia;
import cs3500.modelcommands.LumaOrIntensityComponent;
import cs3500.modelcommands.ModelInvoker;
import cs3500.modelcommands.ValueComponent;

/**
 * A class implementation for an ImageProcessorModel.
 */
public class ImageProcessorModelImpl implements ImageProcessorModel {

  private final int height;
  private final int width;
  private final int maxValue;
  private final String fileLocation;
  private final String extension;
  private final ModelInvoker invoker;
  private String[][] colorInfo;


  /**
   * The default constructor for an ImageProcessorModel.
   *
   * @param filename The same of the image that is desired to be manipulated.
   */
  public ImageProcessorModelImpl(String filename, String extension) throws IOException {
    this.colorInfo = ImageUtil.getColors(filename, extension);
    this.extension = extension;
    this.fileLocation = filename;
    this.height = ImageUtil.getInfo(filename, "h", extension);
    this.width = ImageUtil.getInfo(filename, "w", extension);
    this.maxValue = ImageUtil.getInfo(filename, "m", extension);
    this.invoker = new ModelInvoker();
  }

  /**
   * A Constructor that enables a deep copy to be made.
   *
   * @param model The model to be copied.
   */
  public ImageProcessorModelImpl(ImageProcessorModel model) {
    this.extension = model.getExtension();
    this.fileLocation = model.getFileLocation();
    this.colorInfo = model.getColorInfo();
    this.height = model.getHeight();
    this.width = model.getWidth();
    this.maxValue = model.getMaxValue();
    this.invoker = new ModelInvoker();
  }


  /**
   * A method to securely get the height of an image in an IPM.
   *
   * @return the height of this image.
   */
  public int getHeight() {
    return this.height;
  }

  public String getFileLocation() {
    return this.fileLocation;
  }

  public String[][] getColorInfo() {
    return this.colorInfo;
  }

  public String getExtension() {
    return this.extension;
  }

  /**
   * A method to securely get the width of an image in an IPM.
   *
   * @return the width of this image.
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * A method to securely get the maximum color value of an image in an IPM.
   *
   * @return the maximum color value of this image.
   */
  public int getMaxValue() {
    return this.maxValue;
  }

  private String[][] deepCopyColorArray() {
    String[][] result = new String[colorInfo.length][];
    for (int i = 0; i < result.length; i++) {
      result[i] = colorInfo[i].clone();
    }
    return result;
  }

  /**
   * A method that modifies the IPM's colorInfo to reflect a desired to show a color component's
   * grayscale.
   *
   * @param desiredComponent A string to indicate the color component that is desired to stand out.
   */
  public void getColorComponent(String desiredComponent) {
    // create a deep copy of the color info array to modify
    String[][] result = deepCopyColorArray();

    // perform the actual operation
    switch (desiredComponent) {
      case "red":
      case "r": {
        this.invoker.executeOperation(new ColorComponent(result, "red"));
        break;
      }
      case "green":
      case "g": {
        this.invoker.executeOperation(new ColorComponent(result, "green"));
        break;
      }
      case "blue":
      case "b": {
        this.invoker.executeOperation(new ColorComponent(result, "blue"));
        break;
      }
      default: {
        break;
      }
    }
    this.colorInfo = result;
  }

  /**
   * A method to greyscale an image via the value pathway.
   */
  public void getValueComponent() {
    // create a deep copy of the color info array to modify
    String[][] result = deepCopyColorArray();

    // perform the actual operation.
    this.invoker.executeOperation(new ValueComponent(result));
    this.colorInfo = result;
  }


  /**
   * A method to greyscale an image via the intensity or luma pathways.
   *
   * @param desired The desired operation to be done (Luma or Intensity).
   */
  public void getLumaOrIntensityComponent(String desired) {
    // create a deep copy of the color info array to modify
    String[][] result = deepCopyColorArray();

    // perform the actual operation.
    this.invoker.executeOperation(new LumaOrIntensityComponent(result, desired));
    this.colorInfo = result;
  }

  /**
   * A method to modify this IPM's colorInfo to change its brightness.
   *
   * @param desiredChange A string to indicate the change desired (brighten or darken).
   * @param num           The number that it is desired to brighten/darken by.
   * @throws IllegalArgumentException if the rate is 0.
   */
  public void changeBrightness(String desiredChange, int num) throws IllegalArgumentException {
    if (num <= 0) {
      throw new IllegalArgumentException("The rate of change must be not be 0.");
    }
    String[][] result = deepCopyColorArray();
    invoker.executeOperation(new BrightenOrDarken(result, desiredChange, this.maxValue, num));
    this.colorInfo = result;
  }

  /**
   * A method to modify this IMP's colorInfo by flipping the image by a desired direction.
   *
   * @param desiredChange A string to indicate the desired direction (vertical/horizontal).
   */
  public void flipImage(String desiredChange) {
    String[][] result = deepCopyColorArray();
    invoker.executeOperation(new FlipImage(result, desiredChange, this.colorInfo));
    this.colorInfo = result;
  }

  /**
   * A method to modify this IMP's color info to create a simple (luma) greyscale image or a
   * sepia image.
   *
   * @param desired A string to indicate the desired operation.
   */
  public void simpleGreyscaleOrSepia(String desired) {
    String[][] result = deepCopyColorArray();
    invoker.executeOperation(new GreyscaleOrSepia(result, desired, this.maxValue));
    this.colorInfo = result;
  }

  /**
   * A method to modify this IMP's color info to blur or sharpen the image.
   *
   * @param desired A string to indicate the desired operation.
   */
  public void gaussianBlurOrSharpen(String desired) {
    String[][] result = deepCopyColorArray();
    invoker.executeOperation(new GaussianOrSharpen(result, desired, this.maxValue, this.colorInfo));
    this.colorInfo = result;
  }

}
