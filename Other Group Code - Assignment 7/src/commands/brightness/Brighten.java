package commands.brightness;

/**
 * Command class to brighten an image by a specified value.
 */
public class Brighten extends ABrightness {
  /**
   * Constructor for the Brighten command class.
   *
   * @param value     the value to brighten by
   * @param imageName the name of the image to brighten
   * @param newName   the new name to store it as
   */
  public Brighten(int value, String imageName, String newName) {
    super(value, imageName, newName);
  }
}
