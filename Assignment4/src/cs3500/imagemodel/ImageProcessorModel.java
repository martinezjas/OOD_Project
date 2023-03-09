package cs3500.imagemodel;

/**
 * A class that represents a single image as a modifiable ImageProcessorModel.
 */
public interface ImageProcessorModel {

  /**
   * A method to securely get the height of an image in an IPM.
   *
   * @return the height of this image.
   */
  int getHeight();

  String getFileLocation();

  String[][] getColorInfo();

  String getExtension();

  /**
   * A method to securely get the width of an image in an IPM.
   *
   * @return the width of this image.
   */
  int getWidth();

  /**
   * A method to securely get the maximum color value of an image in an IPM.
   *
   * @return the maximum color value of this image.
   */
  int getMaxValue();

  /**
   * A method that modifies the IPM's colorInfo to reflect a desired to show a color component's
   * grayscale.
   *
   * @param desiredComponent A string to indicate the color component that is desired to stand out.
   */
  void getColorComponent(String desiredComponent);

  /**
   * A method to greyscale an image via the value pathway.
   */
  void getValueComponent();

  /**
   * A method to greyscale an image via the intensity or luma pathways.
   *
   * @param desired The desired operation to be done (Luma or Intensity).
   */
  void getLumaOrIntensityComponent(String desired);

  /**
   * A method to modify this IPM's colorInfo to change its brightness.
   *
   * @param desiredChange A string to indicate the change desired (brighten or darken).
   * @param num           The number that it is desired to brighten/darken by.
   * @throws IllegalArgumentException if the rate is 0.
   */
  void changeBrightness(String desiredChange, int num) throws IllegalArgumentException;

  /**
   * A method to modify this IMP's colorInfo by flipping the image by a desired direction.
   *
   * @param desiredChange A string to indicate the desired direction (vertical/horizontal).
   */
  void flipImage(String desiredChange);

  void simpleGreyscaleOrSepia(String desired);

  void gaussianBlurOrSharpen(String desired);


}