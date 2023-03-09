package cs3500.imageview;

import java.io.IOException;

/**
 * A class implementation of an ImageProcessorView.
 */
public class ImageProcessorViewImpl implements ImageProcessorView {
  final Appendable destination;

  public ImageProcessorViewImpl(Appendable destination) {
    this.destination = destination;
  }

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  @Override
  public void renderMessage(String message) throws IOException {
    destination.append(message);
  }
}
