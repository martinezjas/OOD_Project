package model;

/**
 * An interface that represents a Pixel of the Image and its methods.
 */
public interface Pixel {

  /**
   * Gets the red value of the pixel.
   *
   * @return the red value of the pixel
   */
  int getRedVal();

  /**
   * Gets the green value of the pixel.
   *
   * @return the green value of the pixel
   */
  int getGreenVal();

  /**
   * Gets the blue value of the pixel.
   *
   * @return the blue value of the pixel
   */
  int getBlueVal();


  /*
  Note from receiving authors:
  In order to have mosaic work for all future implementations of the interface Pixel, the following
  methods have to be added.
  Depending on how colors will be implemented in the future, this set function may or may not
  need to change.
   */

  /**
   * Set the values of the rgb values to the new values.
   *
   * @param newRed   the new red value
   * @param newGreen the new green value
   * @param newBlue  the new blue value
   */
  void set(int newRed, int newGreen, int newBlue);
}
