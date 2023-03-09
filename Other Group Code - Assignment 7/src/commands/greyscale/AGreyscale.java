package commands.greyscale;

import commands.ImageCommand;
import model.Image;
import model.ImageDict;
import model.PPMImage;
import model.Pixel;
import model.PixelImpl;

/**
 * Abstract class representing Greyscale command.
 */
public abstract class AGreyscale implements ImageCommand {
  String imageName;
  String newName;

  /**
   * Constructor for AGreyscale command class.
   *
   * @param imageName the name of the image
   * @param newName   the name of the new image
   */
  public AGreyscale(String imageName, String newName) {
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
        // get the correct greyscale val
        int val = this.getValue(pixelArr, i, j);

        // set new pixel and (i,j)
        pixelArr[i][j] = new PixelImpl(i, j, val, val, val);
      }
    }

    newImage = new PPMImage(pixelArr);
    images.add(this.newName, newImage);
  }

  /**
   * Get the value of the pixel.
   *
   * @return the value of the pixel for the image
   */
  protected abstract int getValue(Pixel[][] pixels, int row, int col);
}
