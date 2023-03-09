package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A Junit test class for a pixel implementation.
 */
public class PixelImplTest {
  Pixel pixel1;

  @Before
  public void setup() {
    pixel1 = new PixelImpl(0, 0, 0, 0, 0);
  }

  // Test for constructor exceptions
  @Test
  public void testPixelConstructor() {
    // test for negative row in constructor
    try {
      new PixelImpl(-1, 1, 10, 10, 10);
      fail("new PixelImpl(-1, 1, 10, 10 ,10) did not throw an exception");
    } catch (IllegalArgumentException e) {
      assertEquals("row or col must be non-negative!", e.getMessage());
    }

    // test for negative col in constructor
    try {
      new PixelImpl(1, -1, 10, 10, 10);
      fail("new PixelImpl(1, -1, 10, 10 ,10) did not throw an exception");
    } catch (IllegalArgumentException e) {
      assertEquals("row or col must be non-negative!", e.getMessage());
    }

    // test for negative rgb value
    try {
      new PixelImpl(1, 1, -10, 100, 200);
      fail("new PixelImpl(1, 1, -10, 100 ,200); did not throw an exception");
    } catch (IllegalArgumentException e) {
      assertEquals("rgb values must be between 0 and 255!", e.getMessage());
    }

    // test for rgb value > 255
    try {
      new PixelImpl(1, 1, 10, 100, 256);
      fail("new PixelImpl(1, 1, 10, 100 ,256); did not throw an exception");
    } catch (IllegalArgumentException e) {
      assertEquals("rgb values must be between 0 and 255!", e.getMessage());
    }
  }
}