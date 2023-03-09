package commands.brightness;

/**
 * Darken command class that darkens the image and implements ImageCommand Interface.
 */
public class Darken extends ABrightness {
  /**
   * Constructor for the Darken command class.
   *
   * @param value     the value to brighten by
   * @param imageName the name of the image to brighten
   * @param newName   the new name to store it as
   */
  public Darken(int value, String imageName, String newName) {
    super(value, imageName, newName);
  }
}
