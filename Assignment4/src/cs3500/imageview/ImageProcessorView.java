package cs3500.imageview;

import java.io.IOException;

/**
 * A class that currently allows for messages to be rendered to the prompt.
 */
public interface ImageProcessorView {

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  void renderMessage(String message) throws IOException;
}
