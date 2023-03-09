package commands.colortransformation;

import commands.ImageCommand;
import model.Image;
import model.ImageDict;
import model.PPMImage;
import model.Pixel;
import model.PixelImpl;

/**
 * Abstract class representing a color transformation command.
 */
public abstract class AColorTransformation implements ImageCommand {
  public String imageName;
  public String newName;

  /**
   * Constructor for abstract color transformation class.
   *
   * @param imageName the name of the image to apply the color transformation on
   * @param newName   the new name of the image to save to image dictionary
   */
  public AColorTransformation(String imageName, String newName) {
    this.imageName = imageName;
    this.newName = newName;
  }

  @Override
  public void processCommand(ImageDict images) {
    double[][] kernel = this.getFilter();
    Image image = images.get(this.imageName);
    Pixel[][] pixels = image.getPixels();
    Pixel[][] newPixels = new Pixel[pixels.length][pixels[0].length];

    for (int row = 0; row < pixels.length; row++) {
      for (int col = 0; col < pixels[0].length; col++) {
        int[] newValues = new LinearKernelOperator(kernel, pixels).operate(row, col);
        // clamp values
        int r = enforceCap(newValues[0]);
        int g = enforceCap(newValues[1]);
        int b = enforceCap(newValues[2]);

        Pixel tempPixel = new PixelImpl(row, col, r, g, b);
        newPixels[row][col] = tempPixel;
      }
    }

    Image newImage = new PPMImage(newPixels);
    images.add(this.newName, newImage);
  }

  /**
   * Abstract method that returns the kernel that the filter uses.
   *
   * @return the 2D array of doubles that represents the kernel
   */
  public abstract double[][] getFilter();

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
