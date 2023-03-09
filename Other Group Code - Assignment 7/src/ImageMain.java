import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

import controller.ImageProcessControllerImpl;
import model.ImageDictImpl;
import view.ImageProcessViewImpl;

/**
 * The main class for the Image Processor Program.
 */
public class ImageMain {
  /**
   * Main program to run Image Processor.
   *
   * @param args the args passed in
   */
  public static void main(String[] args) {
    switch (args[0]) {
      case "--filename":
        File file = new File(args[1]);
        try {
          InputStream input = new FileInputStream(file);
          new ImageProcessControllerImpl(new InputStreamReader(input),
                  new ImageProcessViewImpl(new ImageDictImpl()), new ImageDictImpl()).execute();
        } catch (FileNotFoundException e) {
          throw new IllegalArgumentException("file not found!");
        }
        break;
      case "--text":
        InputStreamReader input = new InputStreamReader(System.in);
        new ImageProcessControllerImpl(input, new ImageProcessViewImpl(new ImageDictImpl()),
                new ImageDictImpl()).execute();
        break;
      default:
        System.out.println("Please enter an option!");
        break;
    }
  }
}
