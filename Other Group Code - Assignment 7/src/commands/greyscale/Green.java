package commands.greyscale;

import model.Pixel;

/**
 * Command class to visualize the green component of an image.
 */
public class Green extends AGreyscale {
  /**
   * Constructor for green component command class.
   *
   * @param imageName the name of the image
   * @param newName   the name of the new image
   */
  public Green(String imageName, String newName) {
    super(imageName, newName);
  }

  @Override
  protected int getValue(Pixel[][] pixels, int row, int col) {
    Pixel curr = pixels[row][col];
    int value = curr.getGreenVal();
    return value;
  }
}
