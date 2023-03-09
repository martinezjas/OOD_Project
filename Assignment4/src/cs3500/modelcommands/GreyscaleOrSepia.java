package cs3500.modelcommands;

import java.util.Scanner;

/**
 * A command class that implements simple greyscaling and sepia.
 */
public class GreyscaleOrSepia extends ModelOperation {

  private final String desired;
  private final int maxValue;

  /**
   * The default constructor.
   *
   * @param result   The resulting color info array to be modified.
   * @param desired  The desired operation.
   * @param maxValue The maximum color value for this image.
   */
  public GreyscaleOrSepia(String[][] result, String desired, int maxValue) {
    super(result);
    this.desired = desired;
    this.maxValue = maxValue;
  }

  @Override
  public void execute() {
    double[][] sepia = {{0.393, 0.769, 0.189}, {0.349, 0.686, 0.168}, {0.272, 0.534,
            0.131}};

    double[][] greyscale = {{0.2126, 0.7152, 0.0722}, {0.2126, 0.7152, 0.0722}, {0.2126, 0.7152,
            0.0722}};

    double[][] toBeUsed;

    if (desired.equalsIgnoreCase("sepia")) {
      toBeUsed = sepia;
    } else if (desired.equalsIgnoreCase("greyscale")) {
      toBeUsed = greyscale;
    } else {
      throw new IllegalStateException("Unexpected input");
    }

    for (int w = 0; w < result.length; w++) {
      for (int h = 0; h < result[w].length; h++) {
        Scanner sc = new Scanner(result[w][h]).useDelimiter(",");
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        double[][] rgb = {{r}, {g}, {b}};
        double[][] rgbResult = new double[3][1];
        for (int rw = 0; rw < rgbResult.length; rw++) {
          for (int rh = 0; rh < rgbResult[rw].length; rh++) {
            for (int k = 0; k < rgb.length; k++) {
              rgbResult[rw][rh] += (toBeUsed[rw][k]) * (rgb[k][rh]);
              if (rgbResult[rw][rh] > this.maxValue) {
                rgbResult[rw][rh] = this.maxValue;
              } else if (rgbResult[rw][rh] < 0) {
                rgbResult[rw][rh] = 0;
              }
            }
          }
        }
        r = (int) Math.round(rgbResult[0][0]);
        g = (int) Math.round(rgbResult[1][0]);
        b = (int) Math.round(rgbResult[2][0]);

        result[w][h] = r + "," + g + "," + b;
      }
    }
  }
}
