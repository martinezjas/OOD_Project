package commands.filter;

import java.util.ArrayList;
import java.util.List;

import model.Pixel;
import model.PixelImpl;

/**
 * Command class to perform kernel operations given the kernel size.
 */
public class KernelOperator {
  public double[][] kernel;
  public Pixel[][] pixels;

  /**
   * Constructor for kernel operation command class.
   *
   * @param kernel the 2d array kernel
   * @param pixels the 2d array of pixels of the image
   */
  public KernelOperator(double[][] kernel, Pixel[][] pixels) {
    this.kernel = kernel;
    this.pixels = pixels;
  }

  /**
   * Operate method that operates the kernel on the given pixel coordinate.
   *
   * @param row the row of the pixel
   * @param col the col of the pixel
   * @return the array of new rgb values
   */
  public int[] operate(int row, int col) {
    int center = (kernel.length - 1) / 2;
    int size = kernel.length;
    int[] pixelVals = new int[3];

    // create a new 2d array list with size x size elements initialized to 0
    List<List<Pixel>> pixelRange = new ArrayList<>();
    Pixel p = new PixelImpl(0, 0, 0, 0, 0);
    for (int i = 0; i < size; i++) {
      ArrayList<Pixel> temp = new ArrayList<>();
      for (int j = 0; j < size; j++) {
        temp.add(p);
      }
      pixelRange.add(temp);
    }

    // get the pixels of size x size array from row - center to row + center
    int r = 0;
    for (int i = row - center; i < row + center + 1; i++) {
      int c = 0;
      for (int j = col - center; j < col + center + 1; j++) {
        if (i >= 0 && j >= 0 && i < this.pixels.length && j < this.pixels[0].length) {
          pixelRange.get(r).set(c, this.pixels[i][j]);
        }
        c += 1;
      }
      r += 1;
    }

    int newRed = 0;
    int newGreen = 0;
    int newBlue = 0;

    // calculate the rgb values for the pixel
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        double kernelVal = this.kernel[i][j];
        int tempRed = (int) (pixelRange.get(i).get(j).getRedVal() * kernelVal);
        int tempGreen = (int) (pixelRange.get(i).get(j).getGreenVal() * kernelVal);
        int tempBlue = (int) (pixelRange.get(i).get(j).getBlueVal() * kernelVal);

        newRed += tempRed;
        newGreen += tempGreen;
        newBlue += tempBlue;
      }
    }

    pixelVals[0] = newRed;
    pixelVals[1] = newGreen;
    pixelVals[2] = newBlue;

    return pixelVals;
  }

}
