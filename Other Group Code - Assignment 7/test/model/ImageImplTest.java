package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class for the Image Implementation.
 */
public class ImageImplTest {
  Image image1;

  @Before
  public void setup() {
    image1 = new PPMImage("src/PPMFiles/3by3.ppm");
  }

  // test the getHeight method
  @Test
  public void testGetHeight() {
    assertEquals(3, image1.getHeight());
  }

  // test the getWidth method
  @Test
  public void testGetWidth() {
    assertEquals(3, image1.getWidth());
  }

  // test the getPixels method
  @Test
  public void testGetPixels() {
    Pixel[][] image1Pixels = new PixelImpl[][]{
            {new PixelImpl(0, 0, 255, 163, 163),
                new PixelImpl(0, 1, 123, 242, 115),
                new PixelImpl(0, 2, 168, 163, 255)},
            {new PixelImpl(1, 0, 255, 235, 235),
                new PixelImpl(1, 1, 230, 249, 229),
                new PixelImpl(1, 2, 235, 235, 255)},
            {new PixelImpl(2, 0, 255, 255, 255),
                new PixelImpl(2, 1, 255, 255, 255),
                new PixelImpl(2, 2, 255, 255, 255)}};
    assertArrayEquals(image1Pixels, image1.getPixels());
  }

  // test toString method for PPM image
  @Test
  public void testToString() {
    assertEquals("P3\n" + "3 3 \n" + "255\n" + "255\n" + "163\n"
            + "163\n" + "123\n" + "242\n" + "115\n" + "168\n" + "163\n" + "255\n"
            + "255\n" + "235\n" + "235\n" + "230\n" + "249\n" + "229\n" + "235\n" + "235\n"
            + "255\n" + "255\n" + "255\n" + "255\n" + "255\n" + "255\n" + "255\n" + "255\n"
            + "255\n" + "255\n", image1.toString());
  }
}