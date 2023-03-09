package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import commands.colortransformation.Greyscale;
import commands.colortransformation.Sepia;
import commands.filter.Blur;
import commands.filter.Sharpen;
import commands.brightness.Brighten;
import commands.brightness.Darken;
import commands.flip.Horizontal;
import commands.greyscale.Blue;
import commands.greyscale.Green;
import commands.greyscale.Intensity;
import commands.greyscale.Luma;
import commands.greyscale.Red;
import commands.ImageCommand;
import commands.load.Load;
import commands.Save;
import commands.greyscale.Value;
import commands.flip.Vertical;
import model.ImageDict;
import view.ImageProcessView;
import third_party_code.Mosaic;

/**
 * A class representing an implementation of the Image Processor Controller.
 */
public class ImageProcessControllerImpl implements ImageProcessorController {
  private ImageDict dict;
  private ImageProcessView view;
  private final Readable input;

  /**
   * Constructor for the Image Processor controller that takes in a model, view,
   * and readable input.
   *
   * @param input the inputs from the user
   */
  public ImageProcessControllerImpl(Readable input, ImageProcessView view, ImageDict dict) {
    if (input == null) {
      throw new IllegalArgumentException("ImageProcessControllerImpl constructor fields cannot" +
              "be null!");
    }
    this.input = input;
    this.view = view;
    this.dict = dict;
  }

  @Override
  public void execute() throws IllegalStateException {
    Scanner scanner = new Scanner(input);
    String imageName;
    String newImage;
    int quit = 0;

    Map<String, Integer> commands = new HashMap<>();
    commands.put("load", 2);
    commands.put("brighten", 3);
    commands.put("vertical-flip", 2);

    try {
      while (quit != 1) {
        if (scanner.hasNext()) {
          String in = scanner.next();
          switch (in.toLowerCase()) {
            case "quit":
              view.renderMessage("Image processor quit.");
              return;
            case "load":
              String filename = scanner.next();
              imageName = scanner.next();
              ImageCommand loadCmd = new Load(filename, imageName);
              loadCmd.processCommand(dict);
              view.renderMessage("Image loaded as " + imageName + ".");
              break;
            case "brighten":
              int value = scanner.nextInt();
              imageName = scanner.next();
              newImage = scanner.next();
              ImageCommand brightenCmd = new Brighten(value, imageName, newImage);
              brightenCmd.processCommand(dict);
              view.renderMessage(imageName + " brightened by " + value + " as " + newImage);
              break;
            case "darken":
              int darkval = scanner.nextInt() * -1;
              imageName = scanner.next();
              newImage = scanner.next();
              ImageCommand darkenCmd = new Darken(darkval, imageName, newImage);
              darkenCmd.processCommand(dict);
              view.renderMessage(imageName + " darkened by " + darkval + " as " + newImage);
              break;
            case "vertical-flip":
              imageName = scanner.next();
              newImage = scanner.next();
              ImageCommand verticalCmd = new Vertical(imageName, newImage);
              verticalCmd.processCommand(dict);
              view.renderMessage(imageName + " vertically flipped as " + newImage);
              break;
            case "horizontal-flip":
              imageName = scanner.next();
              newImage = scanner.next();
              ImageCommand horizontalCmd = new Horizontal(imageName, newImage);
              horizontalCmd.processCommand(dict);
              view.renderMessage(imageName + " horizontally flipped as " + newImage);
              break;
            case "value-component":
              imageName = scanner.next();
              newImage = scanner.next();
              ImageCommand valueCmd = new Value(imageName, newImage);
              valueCmd.processCommand(dict);
              view.renderMessage(imageName + " greyscale using value component as " + newImage);
              break;
            case "intensity-component":
              imageName = scanner.next();
              newImage = scanner.next();
              ImageCommand intensityCmd = new Intensity(imageName, newImage);
              intensityCmd.processCommand(dict);
              view.renderMessage(imageName + " greyscale using intensity component as " + newImage);
              break;
            case "luma-component":
              imageName = scanner.next();
              newImage = scanner.next();
              ImageCommand lumaCmd = new Luma(imageName, newImage);
              lumaCmd.processCommand(dict);
              view.renderMessage(imageName + " greyscale using luma component as " + newImage);
              break;
            case "red-component":
              imageName = scanner.next();
              newImage = scanner.next();
              ImageCommand redCmd = new Red(imageName, newImage);
              redCmd.processCommand(dict);
              view.renderMessage(imageName + " greyscale using red component as " + newImage);
              break;
            case "green-component":
              imageName = scanner.next();
              newImage = scanner.next();
              ImageCommand greenCmd = new Green(imageName, newImage);
              greenCmd.processCommand(dict);
              view.renderMessage(imageName + " greyscale using green component as " + newImage);
              break;
            case "blue-component":
              imageName = scanner.next();
              newImage = scanner.next();
              ImageCommand blueCmd = new Blue(imageName, newImage);
              blueCmd.processCommand(dict);
              view.renderMessage(imageName + " greyscale using blue component as " + newImage);
              break;
            case "save":
              String filepath = scanner.next();
              imageName = scanner.next();
              ImageCommand saveCommand = new Save(filepath, imageName);
              saveCommand.processCommand(dict);
              view.renderMessage(imageName + " saved to " + filepath);
              break;
            case "blur":
              imageName = scanner.next();
              newImage = scanner.next();
              ImageCommand blurCommand = new Blur(imageName, newImage);
              blurCommand.processCommand(dict);
              view.renderMessage(imageName + " has been blurred as " + newImage);
              break;
            case "sharpen":
              imageName = scanner.next();
              newImage = scanner.next();
              ImageCommand sharpenCommand = new Sharpen(imageName, newImage);
              sharpenCommand.processCommand(dict);
              view.renderMessage(imageName + " has been sharpened as " + newImage);
              break;
            case "greyscale":
              imageName = scanner.next();
              newImage = scanner.next();
              ImageCommand greyscaleCommand = new Greyscale(imageName, newImage);
              greyscaleCommand.processCommand(dict);
              view.renderMessage(imageName + " has been greyscale using color transformation as "
                      + newImage);
              break;
            case "sepia":
              imageName = scanner.next();
              newImage = scanner.next();
              ImageCommand sepiaCommand = new Sepia(imageName, newImage);
              sepiaCommand.processCommand(dict);
              view.renderMessage(imageName + " has been sepia using color transformation as "
                      + newImage);
              break;
            // due to the way this has been implemented, the code has to be directly modified in
            // order to add support for mosaicking.
            case "mosaic":
              int numSeeds = scanner.nextInt();
              imageName = scanner.next();
              newImage = scanner.next();
              ImageCommand Mosaic = new Mosaic(imageName, newImage, numSeeds);
              Mosaic.processCommand(dict);
              view.renderMessage(imageName + " has been mosaicked using " + numSeeds + " as " + newImage);
              break;
            default:
              break;
          }
        } else {
          quit = 1;
        }
      }
      scanner.close();
    } catch (IOException e) {
      throw new IllegalStateException("Inputs or outputs not read correctly.");
    }
  }
}
