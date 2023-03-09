package commands;

import model.ImageDict;

/**
 * Represents a Macro that can be given to the Macro as a function object that operates on it.
 */
public interface ImageCommand {

  void processCommand(ImageDict images);
}
