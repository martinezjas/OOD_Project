package commands;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.Image;
import model.ImageDict;
import model.Pixel;

/**
 * The save command class.
 */
public class Save implements ImageCommand {
  String filepath;
  String imageName;

  /**
   * The constructor the Save command.
   *
   * @param filepath  the filepath to save to
   * @param imageName the name of the image to save
   */
  public Save(String filepath, String imageName) {
    this.filepath = filepath;
    this.imageName = imageName;
  }

  @Override
  public void processCommand(ImageDict images) {
    if (!images.contains(this.imageName)) {
      throw new IllegalArgumentException("Image name not in dictionary!");
    }
    int index = this.filepath.lastIndexOf(".");
    String ext = this.filepath.substring(index + 1);
    try {
      if (ext.equalsIgnoreCase("ppm")) {
        File savedFile = new File(filepath);
        FileOutputStream savedImage = new FileOutputStream(savedFile);
        Image imageToWrite = images.get(this.imageName);
        savedImage.write(imageToWrite.toString().getBytes());
      }
      else {
        Image imageToWrite = images.get(this.imageName);
        BufferedImage image = new BufferedImage(imageToWrite.getWidth(), imageToWrite.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < imageToWrite.getHeight(); i++) {
          for (int j = 0; j < imageToWrite.getWidth(); j++) {
            Pixel temp = imageToWrite.getPixels()[i][j];
            Color color = new Color(temp.getRedVal(), temp.getGreenVal(), temp.getBlueVal());
            image.setRGB(j, i, color.getRGB());
          }
        }

        try {
          ImageIO.write(image, ext, new File(filepath));
        } catch (IOException e) {
          throw new IllegalStateException("Saving failed!");
        }
      }
    } catch (IOException e) {
      throw new IllegalStateException("Error saving!");
    }
  }
}
