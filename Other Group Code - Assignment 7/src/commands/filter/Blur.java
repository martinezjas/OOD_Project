package commands.filter;

import commands.filter.AFilter;

/**
 * Command class for Blurring an Image.
 */
public class Blur extends AFilter {
  /**
   * Constructor for Blur command class.
   *
   * @param imageName the image name to filter
   * @param newName   the new name to save the filtered image as
   */
  public Blur(String imageName, String newName) {
    super(imageName, newName);
  }

  @Override
  public double[][] getFilter() {
    return (new double[][]{{1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0},
                           {1.0 / 8.0, 1.0 / 4.0, 1.0 / 8.0},
                           {1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0}});
  }
}
