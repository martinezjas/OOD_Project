package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test class to test the Image Dictionary Implementation class.
 */
public class ImageDictImplTest {
  Image image1;
  Image image2;
  ImageDict dict1;

  @Before
  public void setup() {
    image1 = new PPMImage("src/PPMFiles/3by3.ppm");
    image2 = new PPMImage("src/PPMFiles/color.ppm");
    dict1 = new ImageDictImpl();
    dict1.add("original", image1);
  }

  // test the add and get method in ImageDictImpl
  @Test
  public void testAddAndGet() {
    // test that get works to get the original image added
    Image getImage = dict1.get("original");
    assertEquals(getImage, image1);

    // test that add works
    dict1.add("color", image2);
    Image getImage2 = dict1.get("color");
    assertEquals(getImage2, image2);
  }

  // test contains method for ImageDictImpl
  @Test
  public void testContains() {
    assertEquals("false", dict1.contains("color").toString());
    assertEquals("true", dict1.contains("original").toString());
  }

  // test the toString method for ImageDictImpl
  @Test
  public void testToString() {
    assertEquals("original : P3\n" + "3 3 \n" + "255\n" + "255\n" + "163\n"
            + "163\n" + "123\n" + "242\n" + "115\n" + "168\n" + "163\n" + "255\n"
            + "255\n" + "235\n" + "235\n" + "230\n" + "249\n" + "229\n" + "235\n" + "235\n"
            + "255\n" + "255\n" + "255\n" + "255\n" + "255\n" + "255\n" + "255\n" + "255\n"
            + "255\n" + "255\n\n", dict1.toString());
  }
}