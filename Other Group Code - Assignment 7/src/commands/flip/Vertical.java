package commands.flip;

import commands.ImageCommand;
import model.Image;
import model.ImageDict;
import model.PPMImage;
import model.Pixel;

/**
 * A command class representing a vertical flip.
 */
public class Vertical implements ImageCommand {
  String imageName;
  String newImageName;

  /**
   * Constructor for vertical command.
   *
   * @param imageName    the image name to flip vertically.
   * @param newImageName the new image name to save.
   */
  public Vertical(String imageName, String newImageName) {
    this.imageName = imageName;
    this.newImageName = newImageName;
  }

  @Override
  public void processCommand(ImageDict images) {
    Image image = images.get(imageName);
    Pixel[][] pixelArr = image.getPixels();
    for (int i = 0; i < pixelArr.length / 2; i++) {
      for (int j = 0; j < pixelArr[i].length; j++) {
        // save the current pixel
        Pixel temp = pixelArr[i][j];
        pixelArr[i][j] = pixelArr[pixelArr.length - i - 1][j];
        pixelArr[pixelArr.length - i - 1][j] = temp;
      }
    }

    Image newImage = new PPMImage(pixelArr);
    images.add(newImageName, newImage);
  }
}
