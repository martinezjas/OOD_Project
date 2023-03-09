package commands.filter;

import commands.ImageCommand;
import model.Image;
import model.ImageDict;
import model.PPMImage;
import model.Pixel;
import model.PixelImpl;

/**
 * Abstract class for commands that involve filtering an image.
 */
public abstract class AFilter implements ImageCommand {
  String imageName;
  String newName;

  /**
   * Constructor for AFilter abstract class for filtering an image command.
   *
   * @param imageName the image name to filter
   * @param newName   the new name to save the filtered image as
   */
  public AFilter(String imageName, String newName) {
    this.imageName = imageName;
    this.newName = newName;
  }

  /**
   * processes the filter command and performs the kernel operation on the image.
   *
   * @param images the image dictionary
   */
  public void processCommand(ImageDict images) {
    double[][] kernel = this.getFilter();
    Image image = images.get(this.imageName);
    Pixel[][] pixels = image.getPixels();
    Pixel[][] newPixels = new Pixel[pixels.length][pixels[0].length];

    for (int row = 0; row < pixels.length; row++) {
      for (int col = 0; col < pixels[0].length; col++) {
        int[] newValues = new KernelOperator(kernel, pixels).operate(row, col);
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

  public abstract double[][] getFilter();
}
