package cs3500.modelcommands;

import java.util.Scanner;

/**
 * A command class the implements luma and intensity component greyscaling.
 */
public class LumaOrIntensityComponent extends ModelOperation {

  private final String desired;

  /**
   * The default constructor.
   *
   * @param result  The resulting color array to be modified.
   * @param desired The desired operation.
   */
  public LumaOrIntensityComponent(String[][] result, String desired) {
    super(result);
    this.desired = desired;
  }

  @Override
  public void execute() {
    for (int w = 0; w < result.length; w++) {
      for (int h = 0; h < result[w].length; h++) {
        Scanner sc = new Scanner(result[w][h]).useDelimiter(",");
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        double before = getIntensityOrLuma(r, g, b, desired);
        int rounded = (int) Math.round(before);
        result[w][h] = rounded + "," + rounded + "," + rounded;
        sc.close();
      }
    }
  }

  /**
   * A helper method to calculate the intensity or luma of a given pixel.
   *
   * @param r       The value of the red component.
   * @param g       The value of the green component.
   * @param b       The value of the blue component.
   * @param desired The desired operation.
   * @return The luma or intensity value.
   */
  private double getIntensityOrLuma(int r, int g, int b, String desired) {
    double result = 0.0;
    if (desired.equalsIgnoreCase("luma") || desired.equalsIgnoreCase("l")) {
      result = ((r * 0.2126) + (g * 0.7152) + (b * 0.0722));
    } else if (desired.equalsIgnoreCase("intensity") ||
            desired.equalsIgnoreCase("i")) {
      result = ((r + g + b) / 3.0);
    }
    return result;
  }
}
