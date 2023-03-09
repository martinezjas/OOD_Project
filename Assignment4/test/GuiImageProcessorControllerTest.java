import org.junit.Assert;
import org.junit.Test;

import cs3500.gui.imagecontroller.GuiImageProcessorController;
import cs3500.gui.imagecontroller.GuiImageProcessorControllerImpl;

/**
 * Class containing tests for GuiImageProcessorController.
 */
public class GuiImageProcessorControllerTest {
  /**
   * Constructor Test.
   */
  @Test
  public void ConstructorTest() {
    GuiImageProcessorController guiImageProcessorController = new GuiImageProcessorControllerImpl();
    Assert.assertTrue("The current collection is not empty",
            guiImageProcessorController.getKeys().isEmpty());
    Assert.assertTrue("The value obtained is not empty",
            guiImageProcessorController.getImageProcessorModel("test") == null);

  }
}
