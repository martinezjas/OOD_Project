package cs3500;

import cs3500.imagecontroller.ImageProcessorController;
import cs3500.imagecontroller.ImageProcessorControllerImpl;
import cs3500.imageview.ImageProcessorView;
import cs3500.imageview.ImageProcessorViewImpl;
import cs3500.gui.view.MainGui;
import cs3500.gui.imagecontroller.GuiImageProcessorController;
import cs3500.gui.imagecontroller.GuiImageProcessorControllerImpl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;


/**
 * This class allows for the initiation of an image processor instance.
 */
public final class ImageProcessor {


  /**
   * Starts the image processor application.
   *
   * @param args No arguments needed.
   */
  public static void main(String[] args) {
    try {
      if (args[0].equalsIgnoreCase("-file")) {
        try {
          Readable in = new InputStreamReader(new FileInputStream(args[1]));
          Appendable out = System.out;
          ImageProcessorView view = new ImageProcessorViewImpl(out);
          ImageProcessorController controller = new ImageProcessorControllerImpl(view, in);
          controller.acceptInput();
        } catch (FileNotFoundException e) {
          System.err.println("FIle not found");
        }
      } else if (args[0].equalsIgnoreCase("-text")) {
        Readable in = new InputStreamReader(System.in);
        Appendable out = System.out;
        ImageProcessorView view = new ImageProcessorViewImpl(out);
        ImageProcessorController controller = new ImageProcessorControllerImpl(view, in);
        controller.acceptInput();
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      // go to gui
      try {
        SwingUtilities.invokeAndWait(() -> {
          startGui();
        });
      } catch (InterruptedException ex) {
        startGui();
      } catch (InvocationTargetException ex) {
        startGui();
      }
    }
  }

  private static void startGui() {
    GuiImageProcessorController guiImageProcessorController = new GuiImageProcessorControllerImpl();
    new MainGui(guiImageProcessorController);
  }
}
