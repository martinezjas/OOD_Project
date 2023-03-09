package commands.greyscale;

import model.Pixel;

/**
 * Command class to visualize the blue component of an image.
 */
public class Blue extends AGreyscale {
  /**
   * Constructor for Blue component command class.
   *
   * @param imageName the name of the image
   * @param newName   the name of the new image
   */
  public Blue(String imageName, String newName) {
    super(imageName, newName);
  }

  @Override
  protected int getValue(Pixel[][] pixels, int row, int col) {
    Pixel curr = pixels[row][col];
    int value = curr.getBlueVal();
    return value;
  }
}
