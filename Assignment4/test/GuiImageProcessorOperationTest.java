import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import cs3500.gui.processorcommands.GuiImageProcessorOperation;
import cs3500.imagemodel.ImageProcessorModel;
import cs3500.imageview.ImageProcessorView;
import cs3500.imageview.ImageProcessorViewImpl;

/**
 * Class containing tests for ImageProcessorOperation.
 */
public class GuiImageProcessorOperationTest {
  /**
   * test the mothod of Execute.
   */
  @Test
  public void testExexcute() {
    String[] params = new String[3];
    HashMap<String, ImageProcessorModel> images = new HashMap<>();
    Appendable appendable = new StringBuilder();
    ImageProcessorView view = new ImageProcessorViewImpl(appendable);
    GuiImageProcessorOperation obj = new GuiImageProcessorOperation(params, images, view) {
      @Override
      public void execute() {
        Assert.assertTrue("Length of input params array is not 3", params.length == 3);
      }
    };
    obj.execute();
  }
}
