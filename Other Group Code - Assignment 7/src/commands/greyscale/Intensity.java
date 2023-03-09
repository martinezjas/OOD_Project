package commands.greyscale;

import model.Pixel;

/**
 * Command class for intensity greyscale component.
 */
public class Intensity extends AGreyscale {

  /**
   * Constructor for intensity greyscale component command class.
   *
   * @param imageName the name of the image
   * @param newName   the name of the new image
   */
  public Intensity(String imageName, String newName) {
    super(imageName, newName);
  }

  @Override
  protected int getValue(Pixel[][] pixels, int row, int col) {
    Pixel curr = pixels[row][col];
    int value = (curr.getRedVal() + curr.getGreenVal() + curr.getBlueVal()) / 3;
    return value;
  }
}
