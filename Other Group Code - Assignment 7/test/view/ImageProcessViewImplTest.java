package view;

import org.junit.Before;
import org.junit.Test;

import model.ImageDictImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A JUnit test class for the Image Process View Implementation.
 */
public class ImageProcessViewImplTest {
  ImageDictImpl image1;
  ImageProcessViewImpl view1;

  @Before
  public void setup() {
    image1 = new ImageDictImpl();
    view1 = new ImageProcessViewImpl(image1);
  }

  @Test
  public void testConstructor() {
    try {
      new ImageProcessViewImpl(null);
      fail("new ImageProcessViewImpl(null) did not throw an exception!");
    } catch (IllegalArgumentException e) {
      assertEquals("Image Model cannot be null!", e.getMessage());
    }
  }

}