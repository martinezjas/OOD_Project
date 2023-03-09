package third_party_code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import commands.ImageCommand;
import model.Image;
import model.ImageDict;
import model.PPMImage;
import model.Pixel;

/**
 * ImageCommand implementation for a Mosaic function.
 */
public class Mosaic implements ImageCommand {

  private final String imageName;
  private final String newName;
  private final int seedAmt;

  /**
   * Sole public constructor for a Mosaic class.
   *
   * @param imageName The name of the image desired to be mosaicked.
   * @param newName   The name of the output image.
   * @param seedAmt   The amount of seeds desired in the mosaic.
   */
  public Mosaic(String imageName, String newName, int seedAmt) {
    this.imageName = imageName;
    this.newName = newName;
    this.seedAmt = seedAmt;
  }

  /**
   * Private nested class that represents a Pixel's position as a "point". Sort of like a record
   * class.
   * This seemed necessary to implement, as the original authors of this code did not implement a
   * way to get a Pixel's coordinates safely without modifying the code.
   */
  private static class Point {
    private final int x;

    private final int y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public int getX() {
      return x;
    }

    public int getY() {
      return y;
    }
  }

  /**
   * Executes the mosaic command.
   *
   * @param images The image dictionary from which the input will come from and the output be
   *               saved to.
   */
  @Override
  public void processCommand(ImageDict images) {
    //set up the command by pulling the image and getting its Pixels and information.
    Image image = images.get(this.imageName);
    Image newImage;
    Pixel[][] Pixels = image.getPixels();
    int height = image.getHeight();
    int width = image.getWidth();
    //generate random seed centers to use for the mosaic up to the amount specified by the user.
    Random rand = new Random();
    ArrayList<Pixel> seeds = new ArrayList<>();
    for (int n = 1; n <= seedAmt; n++) {
      int randomHeight = rand.nextInt(height);
      int randomWidth = rand.nextInt(width);
      Pixel Pixel = Pixels[randomHeight][randomWidth];
      seeds.add(Pixel);
    }
    // match each seed to the nearest Pixels.
    HashMap<Pixel, ArrayList<Pixel>> matchedSeeds = matchSeeds(Pixels, seeds);
    // determine the color values per seed cluster and write
    for (HashMap.Entry<Pixel, ArrayList<Pixel>> entry : matchedSeeds.entrySet()) {
      ArrayList<Pixel> PixelsInCluster = entry.getValue();
      int r = 0;
      int g = 0;
      int b = 0;
      for (Pixel p : PixelsInCluster) {
        r += p.getRedVal();
        g += p.getGreenVal();
        b += p.getBlueVal();
      }
      r /= PixelsInCluster.size();
      g /= PixelsInCluster.size();
      b /= PixelsInCluster.size();
      for (Pixel s : PixelsInCluster) {
        s.set(r, g, b);
      }
    }
    newImage = new PPMImage(Pixels);
    images.add(this.newName, newImage);
  }


  /**
   * Private helper function to match each Pixel to its nearest seed.
   *
   * @param Pixels The array of Pixels in the input image.
   * @param seeds  The list of seeds randomly generated.
   * @return A HashMap of all Pixels matched to its nearest seed.
   */
  private HashMap<Pixel, ArrayList<Pixel>> matchSeeds(Pixel[][] Pixels, ArrayList<Pixel> seeds) {
    HashMap<Pixel, ArrayList<Pixel>> matchedSeeds = new HashMap<>();
    for (int i = 0; i < Pixels.length; i++) {
      for (int j = 0; j < Pixels[i].length; j++) {
        Pixel nearestSeed = null;
        Double currentDistance = null;
        for (Pixel t : seeds) {
          double distance = distanceCalculator(Pixels[i][j], t);
          try {
            if (distance < currentDistance) {
              currentDistance = distance;
              nearestSeed = t;
            }
          } catch (NullPointerException e) {
            currentDistance = distance;
            nearestSeed = t;
          }
        }
        if (matchedSeeds.containsKey(nearestSeed)) {
          try {
            ArrayList<Pixel> closePixels = matchedSeeds.get(nearestSeed);
            closePixels.add(Pixels[i][j]);
          } catch (NullPointerException e) {
            throw new RuntimeException("Something went wrong.");
          }
        } else {
          try {
            ArrayList<Pixel> closePixels = new ArrayList<>();
            closePixels.add(Pixels[i][j]);
            matchedSeeds.put(nearestSeed, closePixels);
          } catch (NullPointerException e) {
            throw new RuntimeException("Something went wrong.");
          }
        }
      }
    }
    return matchedSeeds;
  }

  /**
   * Private helper function to calculate the distance between two Pixels.
   *
   * @param p1 The first Pixel.
   * @param p2 The second Pixel.
   * @return The distance as a double.
   */
  private double distanceCalculator(Pixel p1, Pixel p2) {
    Scanner sc = new Scanner(p1.toString()).useDelimiter("\\(|\\)|, *");
    Scanner sc2 = new Scanner(p2.toString()).useDelimiter("\\(|\\)|, *");
    int x = sc.nextInt();
    int y = sc.nextInt();
    int x2 = sc2.nextInt();
    int y2 = sc2.nextInt();
    sc.close();
    sc2.close();
    // compute the distance between p and Pixels[i][j] using pythagorean theorem
    double ac = Math.abs(y2 - y);
    double cb = Math.abs(x2 - x);
    return Math.hypot(ac, cb);
  }
}
