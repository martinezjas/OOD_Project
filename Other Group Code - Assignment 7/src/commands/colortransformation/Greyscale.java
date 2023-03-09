package commands.colortransformation;

/**
 * Java class representing greyscale command class with color transformation.
 */
public class Greyscale extends AColorTransformation {
  /**
   * Constructor for greyscale color transformation class.
   *
   * @param imageName the name of the image to apply the color transformation on
   * @param newName   the new name of the image to save to image dictionary
   */
  public Greyscale(String imageName, String newName) {
    super(imageName, newName);
  }

  @Override
  public double[][] getFilter() {
    return new double[][]{{0.2126, 0.7152, 0.0722},
                          {0.2126, 0.7152, 0.0722},
                          {0.2126, 0.7152, 0.0722}};
  }
}
