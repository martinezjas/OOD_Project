package cs3500;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import cs3500.imagemodel.ImageProcessorModel;

/**
 * This class contains utility methods to read a PPM image from file and simply print its
 * contents. Feel free to change this method
 * as required.
 */
public class ImageUtil {

  private ImageUtil() {
    throw new IllegalStateException("This is a utility class, how did you get here?");
  }

  /**
   * A method to get information of a PPM image.
   *
   * @param filename The location of the PPM image file.
   * @param desired  The desired information needed (width, height, or max color value).
   * @return The desired information in integer form.
   */
  public static int getInfo(String filename, String desired, String extension) throws IOException {
    int result;
    switch (extension) {
      case "PPM": {
        result = getPPMInfo(filename, desired);
        break;
      }
      case "PNG":
      case "BMP":
      case "GIF":
      case "WBMP":
      case "JPG":
      case "JPEG":
      case "JFIF":
      case "PJPEG":
      case "PJP": {
        result = getOtherInfo(filename, desired);
        break;
      }
      default:
        throw new IllegalStateException("Unexpected value: " + extension);
    }
    return result;
  }

  private static int getPPMInfo(String filename, String desired) {
    @SuppressWarnings("DuplicatedCode") Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + filename + " not found!");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }
    switch (desired) {
      case "width":
      case "w":
        return sc.nextInt();
      case "height":
      case "h":
        sc.nextInt();
        return sc.nextInt();
      case "maxvalue":
      case "max":
      case "m":
        sc.nextInt();
        sc.nextInt();
        return sc.nextInt();
      default:
        throw new IllegalArgumentException("Something went wrong");
    }
  }

  private static int getOtherInfo(String filename, String desired) throws IOException {
    BufferedImage img = ImageIO.read(new File(filename));
    int result;
    switch (desired) {
      case "width":
      case "w": {
        result = img.getWidth();
        break;
      }
      case "height":
      case "h": {
        result = img.getHeight();
        break;
      }
      case "maxvalue":
      case "max":
      case "m": {
        result = 255;
        break;
      }
      default:
        throw new IllegalStateException("Unexpected value: " + desired);
    }
    return result;
  }

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static String[][] getColors(String filename, String extension) throws IOException {
    String[][] result;
    switch (extension) {
      case "PPM": {
        result = getPPMColors(filename);
        break;
      }
      case "PNG":
      case "BMP":
      case "GIF":
      case "JPG":
      case "JPEG":
      case "JFIF":
      case "PJPEG":
      case "PJP": {
        result = getOtherColors(filename);
        break;
      }
      default:
        throw new IllegalStateException("Unexpected value: " + extension);
    }
    return result;
  }

  private static String[][] getPPMColors(String filename) {
    @SuppressWarnings("DuplicatedCode") Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + filename + " not found!");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with " +
              "P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    sc.nextInt();

    String[][] output = new String[width][height];
    for (int i = 0; i < output.length; i++) {
      for (int j = 0; j < output[i].length; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        output[i][j] = r + "," + g + "," + b;
      }
    }
    return output;
  }

  private static String[][] getOtherColors(String filename) throws IOException {
    BufferedImage img = ImageIO.read(new File(filename));
    int width = img.getWidth();
    int height = img.getHeight();
    String[][] output = new String[width][height];
    for (int i = 0; i < output.length; i++) {
      for (int j = 0; j < output[i].length; j++) {
        Color c = new Color(img.getRGB(i, j));
        int r = c.getRed();
        int g = c.getGreen();
        int b = c.getBlue();
        output[i][j] = r + "," + g + "," + b;
      }
    }
    return output;
  }

  /**
   * Creates a new PPM file and writes to it with information an IPM.
   *
   * @param model    The model that will be used to construct a new PPM file.
   * @param filename The file location of the new PPM file.
   * @throws IOException if the file is unable to be made.
   */
  public static void buildImage(ImageProcessorModel model, String filename, String extension)
          throws IOException {
    switch (extension) {
      case "PPM": {
        buildPPM(model, filename);
        break;
      }
      case "PNG":
      case "BMP":
      case "GIF":
      case "WBMP":
      case "JPG":
      case "JPEG":
      case "JFIF":
      case "PJPEG":
      case "PJP": {
        buildOther(model, filename, extension.toLowerCase());
        break;
      }
      default: {
        throw new IllegalStateException("Unexpected value: " + extension);
      }
    }
  }

  @SuppressWarnings("StringConcatenationInsideStringBufferAppend")
  private static void buildPPM(ImageProcessorModel model, String filename) throws IOException {
    File output = new File(filename);
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(output))) {
      bw.append("P3\n");
      bw.append("# Created by Jason and Tim's ImageProcessor. Project for Northeastern " +
              "University's Object Oriented Design Class, Fall 2022.\n");
      bw.append(model.getWidth() + " " + model.getHeight() + "\n");
      bw.append(model.getMaxValue() + "\n");

      Scanner sc;
      String[][] colorInfo = model.getColorInfo();
      //noinspection ForLoopReplaceableByForEach
      for (int w = 0; w < colorInfo.length; w++) {
        for (int h = 0; h < colorInfo[w].length; h++) {
          sc = new Scanner(colorInfo[w][h]).useDelimiter(",");
          int r = sc.nextInt();
          int g = sc.nextInt();
          int b = sc.nextInt();
          bw.append(r + "\n" + g + "\n" + b + "\n");
        }
      }
      bw.flush();
    }
  }

  private static void buildOther(ImageProcessorModel model, String filename, String extension)
          throws IOException {
    File output = new File(filename);
    BufferedImage bi = new BufferedImage(model.getWidth(), model.getHeight(),
            BufferedImage.TYPE_INT_RGB);
    String[][] colors = model.getColorInfo();
    for (int i = 0; i < colors.length; i++) {
      for (int j = 0; j < colors[i].length; j++) {
        String[] numbers = (colors[i][j]).split(",");
        int r = Integer.parseInt(numbers[0]);
        int g = Integer.parseInt(numbers[1]);
        int b = Integer.parseInt(numbers[2]);
        Color c = new Color(r, g, b);
        bi.setRGB(i, j, c.getRGB());
      }
    }
    ImageIO.write(bi, extension, output);
  }
}

