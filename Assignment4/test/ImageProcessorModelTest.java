import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import cs3500.imagemodel.ImageProcessorModel;
import cs3500.imagemodel.ImageProcessorModelImpl;

import static org.junit.Assert.assertEquals;

/**
 * A class to test ImageProcessorModel.
 */
public class ImageProcessorModelTest {

  ImageProcessorModel model;

  /**
   * A method to setup examples to test on.
   */
  @Before
  public void setup() throws IOException {
    model = new ImageProcessorModelImpl("Assignment4/res/PPM/sign_1.ppm", "PPM");
  }

  /**
   * Testing the getHeight method.
   */
  @Test
  public void getHeightTest() {
    assertEquals(99, model.getHeight());
  }

  /**
   * Testing the getWidth method.
   */
  @Test
  public void getWidthTest() {
    assertEquals(99, model.getWidth());
  }

  /**
   * Testing the getMaxValue method.
   */
  @Test
  public void getMaxValueTest() {
    assertEquals(255, model.getMaxValue());
  }


  // unsure of how to test the rest of the methods without spending a lot of time manually
  // picking out the info from the files themselves.
}