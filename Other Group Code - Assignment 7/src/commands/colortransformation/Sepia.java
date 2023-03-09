package commands.colortransformation;

/**
 * Command class for Sepia tone color transformation.
 */
public class Sepia extends AColorTransformation {
  /**
   * Constructor for abstract color transformation class.
   *
   * @param imageName the name of the image to apply the color transformation on
   * @param newName   the new name of the image to save to image dictionary
   */
  public Sepia(String imageName, String newName) {
    super(imageName, newName);
  }

  @Override
  public double[][] getFilter() {
    return new double[][]{{0.393, 0.769, 0.189},
                          {0.349, 0.686, 0.168},
                          {0.272, 0.534, 0.131}};
  }
}
