package commands.load;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import commands.ImageCommand;
import model.Image;
import model.ImageDict;
import model.PPMImage;
import model.Pixel;
import model.PixelImpl;

/**
 * Command class that represents loading an Image.
 */
public class Load implements ImageCommand {
  String filename;
  String name;

  /**
   * Constructor for Load command class.
   *
   * @param filename the given filename where the image is
   * @param name     the name of the image
   */
  public Load(String filename, String name) {
    this.filename = filename;
    this.name = name;
  }

  @Override
  public void processCommand(ImageDict images) {
    String fileType = this.filename.split("\\.")[1];
    if (fileType.equalsIgnoreCase("ppm")) {
      System.out.println("image is ppm");
      Image ppm = new PPMImage(this.filename);
      images.add(this.name, ppm);
    }
    // load another type of image that is not a ppm ascii file
    else {
      System.out.println("Not PPM");
      try {
        BufferedImage image = ImageIO.read(new File(filename));
        int height = image.getHeight();
        int width = image.getWidth();

        Pixel[][] imagePixels = new Pixel[height][width];

        // create a 2d array of pixels from the buffered image
        for (int i = 0; i < width; i++) {
          for (int j = 0; j < height; j++) {
            int color = image.getRGB(i, j);
            int red = (color >> 16) & 0xff;
            int green = (color >> 8) & 0xff;
            int blue = color & 0xff;
            Pixel pixel = new PixelImpl(j, i, red, green, blue);
            imagePixels[j][i] = pixel;
          }
        }

        // turn the buffered image to a ppm
        Image bufferedImage = new PPMImage(imagePixels);
        images.add(this.name, bufferedImage);
      } catch (IOException e) {
        throw new IllegalStateException("Could not load image!");
      }
    }
  }
}
