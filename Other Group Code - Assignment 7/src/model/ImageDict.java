package model;

/**
 * Interface representing a dictionary of images and the methods it can use.
 */
public interface ImageDict {
  /**
   * Add an image with a name and image to the dictionary of images.
   *
   * @param name  the name to refer to the image
   * @param image the image object
   */
  void add(String name, Image image);

  /**
   * Get the Image from the specified key name.
   *
   * @param name the name that is the key
   * @return the Image or value of that key
   */
  Image get(String name);

  /**
   * Return a boolean whether the name is contained as a key.
   *
   * @param name the name of the image
   * @return true if the image name is contained, false otherwise
   */
  Boolean contains(String name);

  @Override
  String toString();
}
