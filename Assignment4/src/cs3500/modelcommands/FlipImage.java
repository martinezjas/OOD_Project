package cs3500.modelcommands;

/**
 * A command class to implement the flip image function.
 */
public class FlipImage extends ModelOperation {

  private final String desired;
  private final String[][] colorInfo;

  /**
   * The default constructor.
   *
   * @param result    The resulting color info array to be modified.
   * @param desired   The desired operation.
   * @param colorInfo The original color info array.
   */
  public FlipImage(String[][] result, String desired, String[][] colorInfo) {
    super(result);
    this.desired = desired;
    this.colorInfo = colorInfo;
  }

  @Override
  public void execute() {
    switch (desired) {
      case "horizontal":
      case "h": {
        for (int w = 0; w < result.length; w++) {
          for (int h = 0; h < result[w].length; h++) {
            String origin = this.colorInfo[w][h];
            result[w][result[w].length - h - 1] = origin;
          }
        }
        break;
      }
      case "vertical":
      case "v": {
        for (int w = 0; w < result.length; w++) {
          for (int h = 0; h < result[w].length; h++) {
            String origin = this.colorInfo[w][h];
            result[result.length - w - 1][h] = origin;
          }
        }
        break;
      }
      default:
        break;
    }
  }
}
