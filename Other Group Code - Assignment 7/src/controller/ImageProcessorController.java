package controller;

/**
 * An interface that represents the controller for the Image Processor and runs it.
 */
public interface ImageProcessorController {
  /**
   * Executes the Image Processor.
   *
   * @throws IllegalStateException if the controller is unable to read input or transmit output
   */
  void execute() throws IllegalStateException;
}
