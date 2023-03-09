package cs3500.modelcommands;

import java.util.Scanner;

/**
 * A command class to implement gaussian blurring and sharpening.
 */
public class GaussianOrSharpen extends ModelOperation {

  private final String desired;
  private final int maxValue;
  private final String[][] colorInfo;

  /**
   * The default constructor.
   *
   * @param result    The resulting color array to be modified.
   * @param desired   The desired operation.
   * @param maxValue  The maximum color value for this image.
   * @param colorInfo The original color info array.
   */
  public GaussianOrSharpen(String[][] result, String desired, int maxValue, String[][] colorInfo) {
    super(result);
    this.desired = desired;
    this.maxValue = maxValue;
    this.colorInfo = colorInfo;
  }

  @Override
  public void execute() {
    double[][] gaussian = {{0.0625, 0.125, 0.0625}, {0.125, 0.25, 0.125}, {0.0625, 0.125, 0.0625}};
    double[][] sharpen = {{-0.125, -0.125, -0.125, -0.125, -0.125},
      {-0.125, 0.25, 0.25, 0.25, -0.125}, {-0.125, 0.25, 1.0, 0.25, -0.125},
      {-0.125, 0.25, 0.25, 0.25, -0.125}, {-0.125, -0.125, -0.125, -0.125, -0.125}};

    double[][] toBeUsed;
    int length;

    if (desired.equalsIgnoreCase("gaussian")) {
      length = 3;
      toBeUsed = gaussian;
    } else if (desired.equalsIgnoreCase("sharpen")) {
      length = 5;
      toBeUsed = sharpen;
    } else {
      throw new IllegalStateException("Unexpected input");
    }


    for (int w = 0; w < result.length; w++) {
      for (int h = 0; h < result[w].length; h++) {
        String[][] pixels = getPixels(desired, w, h);

        double[][] redValues = new double[length][length];
        double[][] greenValues = new double[length][length];
        double[][] blueValues = new double[length][length];

        for (int sw = 0; sw < pixels.length; sw++) {
          for (int sh = 0; sh < pixels[sw].length; sh++) {
            Scanner sc = new Scanner(pixels[sw][sh]).useDelimiter(",");
            int r = sc.nextInt();
            int g = sc.nextInt();
            int b = sc.nextInt();
            redValues[sw][sh] = r;
            greenValues[sw][sh] = g;
            blueValues[sw][sh] = b;
          }
        }

        applyFilter(redValues, toBeUsed);
        applyFilter(greenValues, toBeUsed);
        applyFilter(blueValues, toBeUsed);

        int r;
        int g;
        int b;

        r = addValues(redValues);
        g = addValues(greenValues);
        b = addValues(blueValues);

        result[w][h] = r + "," + g + "," + b;
      }
    }
  }

  private void applyFilter(double[][] input, double[][] filter) {
    for (int iw = 0; iw < input.length; iw++) {
      for (int ih = 0; ih < input[iw].length; ih++) {
        input[iw][ih] = input[iw][ih] * filter[iw][ih];
      }
    }
  }

  private int addValues(double[][] input) {
    double result = 0;
    for (double[] i : input) {
      for (double j : i) {
        result += j;
      }
    }
    int beforeClamp = (int) Math.round(result);
    if (beforeClamp < 0) {
      return 0;
    } else {
      return Math.min(beforeClamp, this.maxValue);
    }
  }

  private String[][] getPixels(String type, int w, int h) {
    // can't think of a better way to write this
    String[][] result;
    switch (type) {
      case "g":
      case "gaussian": {
        result = new String[3][3];
        try {
          result[0][0] = this.colorInfo[w - 1][h + 1];
        } catch (ArrayIndexOutOfBoundsException e) {
          result[0][0] = "0,0,0";
        }
        try {
          result[0][1] = this.colorInfo[w][h + 1];
        } catch (ArrayIndexOutOfBoundsException e) {
          result[0][1] = "0,0,0";
        }
        try {
          result[0][2] = this.colorInfo[w + 1][h + 1];
        } catch (ArrayIndexOutOfBoundsException e) {
          result[0][2] = "0,0,0";
        }
        try {
          result[1][0] = this.colorInfo[w - 1][h];
        } catch (ArrayIndexOutOfBoundsException e) {
          result[1][0] = "0,0,0";
        }
        try {
          result[1][1] = this.colorInfo[w][h];
        } catch (ArrayIndexOutOfBoundsException e) {
          result[1][1] = "0,0,0";
        }
        try {
          result[1][2] = this.colorInfo[w + 1][h];
        } catch (ArrayIndexOutOfBoundsException e) {
          result[1][2] = "0,0,0";
        }
        try {
          result[2][0] = this.colorInfo[w - 1][h - 1];
        } catch (ArrayIndexOutOfBoundsException e) {
          result[2][0] = "0,0,0";
        }
        try {
          result[2][1] = this.colorInfo[w][h - 1];
        } catch (ArrayIndexOutOfBoundsException e) {
          result[2][1] = "0,0,0";
        }
        try {
          result[2][2] = this.colorInfo[w + 1][h - 1];
        } catch (ArrayIndexOutOfBoundsException e) {
          result[2][2] = "0,0,0";
        }
        break;
      }
      case "s":
      case "sharpen": {
        result = new String[5][5];
        try {
          result[0][0] = this.colorInfo[w - 2][h + 2];
        } catch (ArrayIndexOutOfBoundsException e) {
          result[0][0] = "0,0,0";
        }
        try {
          result[0][1] = this.colorInfo[w - 1][h + 2];
        } catch (ArrayIndexOutOfBoundsException e) {
          result[0][1] = "0,0,0";
        }
        try {
          result[0][2] = this.colorInfo[w][h + 2];
        } catch (ArrayIndexOutOfBoundsException e) {
          result[0][2] = "0,0,0";
        }
        try {
          result[0][3] = this.colorInfo[w + 1][h + 2];
        } catch (ArrayIndexOutOfBoundsException e) {
          result[0][3] = "0,0,0";
        }
        try {
          result[0][4] = this.colorInfo[w + 2][h + 2];
        } catch (ArrayIndexOutOfBoundsException e) {
          result[0][4] = "0,0,0";
        }
        try {
          result[1][0] = this.colorInfo[w - 2][h + 1];
        } catch (ArrayIndexOutOfBoundsException e) {
          result[1][0] = "0,0,0";
        }
        try {
          result[1][1] = this.colorInfo[w - 1][h + 1];
        } catch (ArrayIndexOutOfBoundsException e) {
          result[1][1] = "0,0,0";
        }
        try {
          result[1][2] = this.colorInfo[w][h + 1];
        } catch (ArrayIndexOutOfBoundsException e) {
          result[1][2] = "0,0,0";
        }
        try {
          result[1][3] = this.colorInfo[w + 1][h + 1];
        } catch (ArrayIndexOutOfBoundsException e) {
          result[1][3] = "0,0,0";
        }
        try {
          result[1][4] = this.colorInfo[w + 2][h + 1];
        } catch (ArrayIndexOutOfBoundsException e) {
          result[1][4] = "0,0,0";
        }
        try {
          result[2][0] = this.colorInfo[w - 2][h];
        } catch (ArrayIndexOutOfBoundsException e) {
          result[2][0] = "0,0,0";
        }
        try {
          result[2][1] = this.colorInfo[w - 1][h];
        } catch (ArrayIndexOutOfBoundsException e) {
          result[2][1] = "0,0,0";
        }
        try {
          result[2][2] = this.colorInfo[w][h];
        } catch (ArrayIndexOutOfBoundsException e) {
          result[2][2] = "0,0,0";
        }
        try {
          result[2][3] = this.colorInfo[w + 1][h];
        } catch (ArrayIndexOutOfBoundsException e) {
          result[2][3] = "0,0,0";
        }
        try {
          result[2][4] = this.colorInfo[w + 2][h];
        } catch (ArrayIndexOutOfBoundsException e) {
          result[2][4] = "0,0,0";
        }
        try {
          result[3][0] = this.colorInfo[w - 2][h - 1];
        } catch (ArrayIndexOutOfBoundsException e) {
          result[3][0] = "0,0,0";
        }
        try {
          result[3][1] = this.colorInfo[w - 1][h - 1];
        } catch (ArrayIndexOutOfBoundsException e) {
          result[3][1] = "0,0,0";
        }
        try {
          result[3][2] = this.colorInfo[w][h - 1];
        } catch (ArrayIndexOutOfBoundsException e) {
          result[3][2] = "0,0,0";
        }
        try {
          result[3][3] = this.colorInfo[w + 1][h - 1];
        } catch (ArrayIndexOutOfBoundsException e) {
          result[3][3] = "0,0,0";
        }
        try {
          result[3][4] = this.colorInfo[w + 2][h - 1];
        } catch (ArrayIndexOutOfBoundsException e) {
          result[3][4] = "0,0,0";
        }
        try {
          result[4][0] = this.colorInfo[w - 2][h - 2];
        } catch (ArrayIndexOutOfBoundsException e) {
          result[4][0] = "0,0,0";
        }
        try {
          result[4][1] = this.colorInfo[w - 1][h - 2];
        } catch (ArrayIndexOutOfBoundsException e) {
          result[4][1] = "0,0,0";
        }
        try {
          result[4][2] = this.colorInfo[w][h - 2];
        } catch (ArrayIndexOutOfBoundsException e) {
          result[4][2] = "0,0,0";
        }
        try {
          result[4][3] = this.colorInfo[w + 1][h - 2];
        } catch (ArrayIndexOutOfBoundsException e) {
          result[4][3] = "0,0,0";
        }
        try {
          result[4][4] = this.colorInfo[w + 2][h - 2];
        } catch (ArrayIndexOutOfBoundsException e) {
          result[4][4] = "0,0,0";
        }
        break;
      }
      default:
        throw new IllegalStateException("Unexpected value: " + type);
    }
    return result;
  }
}
