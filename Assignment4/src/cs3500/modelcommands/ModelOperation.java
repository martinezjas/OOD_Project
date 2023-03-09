package cs3500.modelcommands;

/**
 * An abstract class representing IPM operations.
 */
public abstract class ModelOperation {

  final String[][] result;

  /**
   * The default constructor.
   *
   * @param result The resulting color info array to be modified.
   */
  public ModelOperation(String[][] result) {
    this.result = result;
  }

  public abstract void execute();

}
