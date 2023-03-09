package view;

import java.io.IOException;

/**
 * An interface representing the Image Process View and the methods.
 */
public interface ImageProcessView {
  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  void renderMessage(String message) throws IOException;
}
