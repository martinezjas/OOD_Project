package commands.greyscale;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Pixel;

/**
 * A command class for the Value component greyscale command.
 */
public class Value extends AGreyscale {
  /**
   * Constructor for value command class.
   *
   * @param imageName the name of the image
   * @param newName   the name of the new image
   */
  public Value(String imageName, String newName) {
    super(imageName, newName);
  }

  @Override
  public int getValue(Pixel[][] pixels, int row, int col) {
    Pixel curr = pixels[row][col];
    List<Integer> pixelVals = new ArrayList<>();
    pixelVals.add(curr.getRedVal());
    pixelVals.add(curr.getGreenVal());
    pixelVals.add(curr.getBlueVal());

    // find the max of the pixel values
    int max = Collections.max(pixelVals);

    return max;
  }

}
