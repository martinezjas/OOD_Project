package cs3500.modelcommands;

import java.util.Scanner;

/**
 * A command class to implement value component greyscaling.
 */
public class ValueComponent extends ModelOperation {

  /**
   * The default constructor.
   *
   * @param result The resulting color array to be modified.
   */
  public ValueComponent(String[][] result) {
    super(result);
  }

  @Override
  public void execute() {
    for (int w = 0; w < result.length; w++) {
      for (int h = 0; h < result[w].length; h++) {
        Scanner sc = new Scanner(result[w][h]).useDelimiter(",");
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        int max1 = Math.max(r, g);
        int max2 = Math.max(b, max1);
        result[w][h] = max2 + "," + max2 + "," + max2;
        sc.close();
      }
    }
  }
}
