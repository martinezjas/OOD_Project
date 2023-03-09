package commands.greyscale;

import model.Pixel;

import static java.lang.Math.round;

/**
 * Command class for Luma greyscale component.
 */
public class Luma extends AGreyscale {
  /**
   * Constructor for Luma command class.
   *
   * @param imageName the name of the image
   * @param newName   the name of the new image
   */
  public Luma(String imageName, String newName) {
    super(imageName, newName);
  }

  @Override
  protected int getValue(Pixel[][] pixels, int row, int col) {
    Pixel curr = pixels[row][col];
    int weighted = (int) round(0.2126 * curr.getRedVal() + 0.7152 * curr.getGreenVal()
            + 0.0722 * curr.getBlueVal());
    return weighted;
  }
}
