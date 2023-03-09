package commands.colortransformation;

import model.Pixel;

/**
 * Operation class for linear kernel and matrix multiplication.
 */
public class LinearKernelOperator {
  public double[][] kernel;
  public Pixel[][] pixels;

  /**
   * Constructor for Linear Kernel Operator class.
   *
   * @param kernel the 2d array of doubles for the color transformation
   * @param pixels the 2d array of pixels to use in the operation
   */
  public LinearKernelOperator(double[][] kernel, Pixel[][] pixels) {
    this.kernel = kernel;
    this.pixels = pixels;
  }

  /**
   * Perform the operation of multiplying the matrices.
   *
   * @param row the row of the pixel
   * @param col the column of the pixel
   * @return the new values of the pixels in an array
   */
  public int[] operate(int row, int col) {
    Pixel p = this.pixels[row][col];

    int red = p.getRedVal();
    int green = p.getGreenVal();
    int blue = p.getBlueVal();

    double[][] rgb = {{red}, {green}, {blue}};
    double[][] result = new double[3][1];

    for (int r = 0; r < result.length; r++) {
      for (int c = 0; c < result[r].length; c++) {
        result[r][c] = multiplyCells(this.kernel, rgb, r, c);
      }
    }

    int[] newRes = new int[3];

    for (int i = 0; i < result.length; i++) {
      newRes[i] = (int) result[i][0];
    }

    return newRes;
  }

  /**
   * Helper method for operate method to multiply the matrices.
   *
   * @param rgb    the 2D array of rgb pixel values
   * @param kernel the 2D array of doubles representing the kernel
   * @param r      the row
   * @param c      the column
   * @return teh value after the matrices are multiplied
   */
  private double multiplyCells(double[][] rgb, double[][] kernel, int r, int c) {
    double cell = 0;
    for (int i = 0; i < kernel.length; i++) {
      cell += rgb[r][i] * kernel[i][c];
    }
    return cell;
  }
}
