package commands.load;

import model.Image;

/**
 * An interface for the image type loader methods.
 */
public interface LoadImageType {
  /**
   * Load the image.
   * @return the image object that is loaded
   */
  Image load();
}
