import org.junit.Test;

import java.io.InputStreamReader;

import cs3500.imagecontroller.ImageProcessorControllerImpl;
import cs3500.imageview.ImageProcessorViewImpl;

import static org.junit.Assert.assertThrows;

/**
 * Class containing tests for ImageProcessorController.
 */
public class ImageProcessorControllerTest {

  /**
   * Tests to see if invalid constructors fail.
   */
  @Test
  public void failConstructorTest() {
    assertThrows(IllegalArgumentException.class, () -> new ImageProcessorControllerImpl(null,
            null));
    assertThrows(IllegalArgumentException.class, () -> new ImageProcessorControllerImpl(null,
            new InputStreamReader(System.in)));
    assertThrows(IllegalArgumentException.class, () -> new ImageProcessorControllerImpl(
            new ImageProcessorViewImpl(System.out), null));
  }

  // unsure how to test the rest by manually inserting input, along with the same problems in IPMT.

}