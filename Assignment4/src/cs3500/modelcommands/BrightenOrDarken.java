package cs3500.modelcommands;

import java.util.Scanner;

/**
 * A command class to implement the brighten or darken command.
 */
public class BrightenOrDarken extends ModelOperation {

  private final String desired;
  private final int maxValue;
  private final int num;

  /**
   * Default constructor for BrightenOrDarken.
   *
   * @param result   The resulting colorInfo array to be modified.
   * @param desired  A string to indicate either brightening or darkening.
   * @param maxValue The maximum color value of the image being modified.
   * @param num      The number by which it will be darkened or brightened.
   */
  public BrightenOrDarken(String[][] result, String desired, int maxValue, int num) {
    super(result);
    this.desired = desired;
    this.maxValue = maxValue;
    this.num = num;
  }

  @Override
  public void execute() {
    switch (desired) {
      case "brighten":
      case "bright":
      case "b": {
        for (int w = 0; w < result.length; w++) {
          for (int h = 0; h < result[w].length; h++) {
            Scanner sc = new Scanner(result[w][h]).useDelimiter(",");
            int r = sc.nextInt();
            int g = sc.nextInt();
            int b = sc.nextInt();
            r += num;
            g += num;
            b += num;
            if (r > this.maxValue) {
              r = this.maxValue;
            }
            if (g > this.maxValue) {
              g = this.maxValue;
            }
            if (b > this.maxValue) {
              b = this.maxValue;
            }
            result[w][h] = r + "," + g + "," + b;
            sc.close();
          }
        }
        break;
      }
      case "darken":
      case "dark":
      case "d": {
        for (int w = 0; w < result.length; w++) {
          for (int h = 0; h < result[w].length; h++) {
            Scanner sc = new Scanner(result[w][h]).useDelimiter(",");
            int r = sc.nextInt();
            int g = sc.nextInt();
            int b = sc.nextInt();
            r -= num;
            g -= num;
            b -= num;
            if (r < 0) {
              r = 0;
            }
            if (g < 0) {
              g = 0;
            }
            if (b < 0) {
              b = 0;
            }
            result[w][h] = r + "," + g + "," + b;
            sc.close();
          }
        }
        break;
      }
      default:
        break;
    }
  }
}
