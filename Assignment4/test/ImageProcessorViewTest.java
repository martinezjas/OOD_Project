import org.junit.Test;

import java.io.IOException;

import cs3500.imageview.ImageProcessorView;
import cs3500.imageview.ImageProcessorViewImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A class to test the ImageProcessorView class.
 */
public class ImageProcessorViewTest {

  /**
   * Testing the renderMessage method.
   */

  @Test
  public void renderMessageTest() {
    StringBuilder sb = new StringBuilder();
    ImageProcessorView view = new ImageProcessorViewImpl(sb);
    try {
      view.renderMessage("Hello World!");
    } catch (IOException e) {
      fail("Something went wrong.");
    }
    assertEquals("Hello World!", sb.toString());
  }
}