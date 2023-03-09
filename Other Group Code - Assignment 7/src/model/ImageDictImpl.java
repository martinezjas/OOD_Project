package model;

import java.util.HashMap;
import java.util.Map;

/**
 * A class implementing Image Dict Interface and representing an image with a name.
 */
public class ImageDictImpl implements ImageDict {
  private Map<String, Image> dict;

  /**
   * Default constructor for ImageDictImpl that creates a new Hashmap for the dictionary of Images.
   */
  public ImageDictImpl() {
    this.dict = new HashMap<>();
  }

  @Override
  public void add(String name, Image image) {
    this.dict.put(name, image);
  }

  @Override
  public Image get(String name) {
    Image copy = this.dict.get(name);
    return copy;
  }

  @Override
  public Boolean contains(String name) {
    if (this.dict.containsKey(name)) {
      return true;
    }
    return false;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    for (Map.Entry<String, Image> entry : dict.entrySet()) {
      s.append(entry.getKey() + " : " + entry.getValue().toString() + "\n");
    }
    return s.toString();
  }
}
