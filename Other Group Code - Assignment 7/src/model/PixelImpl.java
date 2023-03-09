package model;

import java.util.Objects;

/**
 * A class that implements the IPixel and represents a pixel on the image.
 */
public class PixelImpl implements Pixel {
  private final int row;
  private final int col;
  private int red;
  private int green;
  private int blue;

  /**
   * Constructor for a pixel that takes a position and rgb value.
   *
   * @param row   the row of the pixel
   * @param col   the column of the pixel
   * @param red   the red value of the pixel (0 <= red < 256)
   * @param green the green value of the pixel (0 <= red < 256)
   * @param blue  the blue value of the pixel (0 <= red < 256)
   */
  public PixelImpl(int row, int col, int red, int green, int blue) {
    if (red < 0 || red >= 256 || green < 0 || green >= 256 || blue < 0 || blue >= 256) {
      throw new IllegalArgumentException("rgb values must be between 0 and 255!");
    }
    if (row < 0 || col < 0) {
      throw new IllegalArgumentException("row or col must be non-negative!");
    }
    this.row = row;
    this.col = col;
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  @Override
  public int getRedVal() {
    return this.red;
  }

  @Override
  public int getGreenVal() {
    return this.green;
  }

  @Override
  public int getBlueVal() {
    return this.blue;
  }

  /**
   * Set the values of the rgb values to the new values.
   *
   * @param newRed   the new red value
   * @param newGreen the new green value
   * @param newBlue  the new blue value
   */
  @Override // see note in Pixel interface for reason of override
  public void set(int newRed, int newGreen, int newBlue) {
    this.red = newRed;
    this.green = newGreen;
    this.blue = newBlue;
  }

  @Override
  public String toString() {
    String s = String.format("(%d, %d, %d, %d, %d)", this.row, this.col,
            this.red, this.green, this.blue);
    return s;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    PixelImpl object = (PixelImpl) obj;
    return (object.row == this.row && object.col == this.col && object.red == this.red
            && object.green == this.green && object.blue == this.blue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(row, col, red, green, blue);
  }
}
