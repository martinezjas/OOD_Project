package commands.filter;

import commands.filter.AFilter;

/**
 * Command class for Blurring an Image.
 */
public class Sharpen extends AFilter {
  /**
   * Constructor for sharpening an image command.
   *
   * @param imageName the image name to filter
   * @param newName   the new name to save the filtered image as
   */
  public Sharpen(String imageName, String newName) {
    super(imageName, newName);
  }

  @Override
  public double[][] getFilter() {
    return new double[][]{{-1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0},
                          {-1.0 / 8.0, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, -1.0 / 8.0},
                          {-1.0 / 8.0, 1.0 / 4.0, 1.0, 1.0 / 4.0, -1.0 / 8.0},
                          {-1.0 / 8.0, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, -1.0 / 8.0},
                          {-1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0}};
  }
}
