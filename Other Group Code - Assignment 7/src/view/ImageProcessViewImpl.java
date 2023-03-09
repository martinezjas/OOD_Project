package view;

import java.io.IOException;

import model.ImageDictImpl;

/**
 * An interface representing the Image Processor View implementation.
 */
public class ImageProcessViewImpl implements ImageProcessView {
  private final Appendable appendable;

  /**
   * Default constructor for Image Process View Implementation. Defaults output to System.out.
   *
   * @param imageModel the image model
   * @throws IllegalArgumentException if the image model provided is null
   */
  public ImageProcessViewImpl(ImageDictImpl imageModel) throws IllegalArgumentException {
    if (imageModel == null) {
      throw new IllegalArgumentException("Image Model cannot be null!");
    }
    this.appendable = System.out;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.appendable.append(message + "\n");
  }
}
