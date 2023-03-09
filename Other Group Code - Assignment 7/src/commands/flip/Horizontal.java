package commands.flip;

import commands.ImageCommand;
import model.Image;
import model.ImageDict;
import model.PPMImage;
import model.Pixel;

/**
 * Command class for flipping an Image Horizontally.
 */
public class Horizontal implements ImageCommand {
  String imageName;
  String newImageName;

  /**
   * Constructor for Horizontal command class.
   *
   * @param imageName    the given image name
   * @param newImageName the new image name
   */
  public Horizontal(String imageName, String newImageName) {
    this.imageName = imageName;
    this.newImageName = newImageName;
  }

  @Override
  public void processCommand(ImageDict images) {
    Image image = images.get(imageName);
    Pixel[][] pixelArr = image.getPixels();
    for (int i = 0; i < pixelArr.length; i++) {
      for (int j = 0; j < pixelArr[i].length / 2; j++) {
        // save the current pixel
        Pixel temp = pixelArr[i][j];
        pixelArr[i][j] = pixelArr[i][pixelArr[i].length - j - 1];
        pixelArr[i][pixelArr[i].length - j - 1] = temp;
      }
    }

    Image newImage = new PPMImage(pixelArr);
    images.add(newImageName, newImage);
  }
}
