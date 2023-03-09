package commands.brightness;

import commands.ImageCommand;
import model.Image;
import model.ImageDict;
import model.PPMImage;
import model.Pixel;
import model.PixelImpl;

/**
 * An abstract command class that represents changing the brightness of images.
 */
public abstract class ABrightness implements ImageCommand {
  int value;
  String imageName;
  String newName;

  /**
   * Constructor for the Brighten command class.
   *
   * @param value     the value to brighten by
   * @param imageName the name of the image to brighten
   * @param newName   the new name to store it as
   */
  public ABrightness(int value, String imageName, String newName) {
    this.value = value;
    this.imageName = imageName;
    this.newName = newName;
  }

  @Override
  public void processCommand(ImageDict images) {
    Image image = images.get(imageName);
    Pixel[][] pixelArr = image.getPixels();
    Image newImage;

    // loop through the pixels of the image and perform brightening
    for (int i = 0; i < pixelArr.length; i++) {
      for (int j = 0; j < pixelArr[i].length; j++) {
        int newRed = enforceCap(pixelArr[i][j].getRedVal() + this.value);
        int newGreen = enforceCap(pixelArr[i][j].getGreenVal() + this.value);
        int newBlue = enforceCap(pixelArr[i][j].getBlueVal() + this.value);
        Pixel newPixel = new PixelImpl(i, j, newRed, newGreen, newBlue);
        pixelArr[i][j] = newPixel;
      }
    }

    newImage = new PPMImage(pixelArr);
    images.add(this.newName, newImage);
  }

  /**
   * Enforces the cap to make sure value is between 0 and 255.
   *
   * @param rgb the rgb value
   * @return the new rgb value
   */
  private int enforceCap(int rgb) {
    int newRgb;
    if (rgb < 0) {
      newRgb = 0;
    } else if (rgb > 255) {
      newRgb = 255;
    } else {
      newRgb = rgb;
    }
    return newRgb;
  }
}
