package commands.greyscale;

import model.Pixel;

/**
 * Command class to visualize the red component of an image.
 */
public class Red extends AGreyscale {
  /**
   * Constructor for Red component command class.
   *
   * @param imageName the name of the image
   * @param newName   the name of the new image
   */
  public Red(String imageName, String newName) {
    super(imageName, newName);
  }

  @Override
  protected int getValue(Pixel[][] pixels, int row, int col) {
    Pixel curr = pixels[row][col];
    int value = curr.getRedVal();
    return value;
  }
}
