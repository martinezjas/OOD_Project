package cs3500.modelcommands;

import java.util.Scanner;

/**
 * A command class to implement the color component greyscale operation.
 */
public class ColorComponent extends ModelOperation {

  private final String desired;

  /**
   * The default constructor.
   *
   * @param result  The resulting color info array to be modified.
   * @param desired The desired color by which to greyscale.
   */
  public ColorComponent(String[][] result, String desired) {
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
        int desiredColor;
        if (this.desired.equalsIgnoreCase("red")) {
          desiredColor = r;
        } else if (this.desired.equalsIgnoreCase("green")) {
          desiredColor = g;
        } else if (this.desired.equalsIgnoreCase("blue")) {
          desiredColor = b;
        } else {
          throw new IllegalStateException("Unexpected input.");
        }
        result[w][h] = desiredColor + "," + desiredColor + "," + desiredColor;
        sc.close();
      }
    }
  }
}
