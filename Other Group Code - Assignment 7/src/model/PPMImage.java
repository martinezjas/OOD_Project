package model;

/**
 * An Image class that implements the Image Interface.
 */
public class PPMImage implements Image {
  private final Pixel[][] pixels;

  /**
   * Constructor for PPMImage class that takes in a file name and reads the pixels.
   *
   * @param filename the file name of the image.
   */
  public PPMImage(String filename) {
    this.pixels = ImageUtil.readPPM(filename);
  }

  /**
   * Constructor for PPM Imageclass that takes in a 2D array of pixels.
   *
   * @param pixels the 2D array of pixels
   */
  public PPMImage(Pixel[][] pixels) {
    this.pixels = pixels;
  }


  @Override
  public Pixel[][] getPixels() {
    Pixel[][] pixelList = new Pixel[this.getHeight()][this.getWidth()];

    // loop through the pixel list and get the copy the pixel the image
    for (int i = 0; i < this.getHeight(); i++) {
      for (int j = 0; j < this.getWidth(); j++) {
        // the pixel of the image at position (i, j)
        Pixel pixel = this.pixels[i][j];
        pixelList[i][j] = new PixelImpl(i, j, pixel.getRedVal(),
                pixel.getGreenVal(), pixel.getBlueVal());
      }
    }

    return pixelList;
  }

  @Override
  public int getHeight() {
    return this.pixels.length;
  }

  @Override
  public int getWidth() {
    return this.pixels[0].length;
  }

  @Override
  public String toString() {
    StringBuilder out = new StringBuilder();
    out.append("P3\n" + this.getWidth() + " " + this.getHeight() + " \n" + "255\n");

    Pixel[][] pixelList = this.getPixels();

    // loop through the pixel list and get the copy the pixel the image
    for (int i = 0; i < this.getHeight(); i++) {
      for (int j = 0; j < this.getWidth(); j++) {
        // the pixel of the image at position (i, j)
        Pixel pixel = this.pixels[i][j];
        out.append(pixel.getRedVal() + "\n");
        out.append(pixel.getGreenVal() + "\n");
        out.append(pixel.getBlueVal() + "\n");
      }
    }

    return out.toString();
  }
}
