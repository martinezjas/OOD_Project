package model;

/**
 * An interface representing an image and the methods that can be performed on an Image.
 */
public interface Image {
  /**
   * Gets a copy of the 2d array of pixels of this image.
   *
   * @return the 2d array of pixels
   */
  Pixel[][] getPixels();

  /**
   * Gets the height of the image, or how many rows in the 2d array of pixels.
   *
   * @return the height of the image
   */
  int getHeight();

  /**
   * Gets the width of the image, or how many columns in the 2d array of pixels.
   *
   * @return the width of the image
   */
  int getWidth();

  /**
   * Turns the image into a ppm string.
   *
   * @return the ppm string
   */
  String toString();
}
