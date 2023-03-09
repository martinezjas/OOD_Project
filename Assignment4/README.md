
# ReadMe file for ImageProcessor

*Made by Jason Martinez and Timothy Si, Northeastern University OOD Fall 2022*

==This is still a work in progress!!!==

Since the last submission, the MVC system has been interfaced in order to comply with design 
conventions, and the command pattern was implemented in the model and controller in order to 
make the code a bit more readable.

---------------------------------------------------------------------------------------------------

## ImageProcessor.java

**This interface allows an image processor program to be initiated. It holds the main function to 
this in order to allow for interaction via the command line.**

## ImageProcessorController.java

**This interface takes in input from the command line and acts on it by triggering methods within a 
model and by sending messages to be rendered by the view. Due to the nature of this program, the controller has a hashmap of images in order to allow for an original image to stay unmodified unless the user explicitly wants to, and to keep multiple transformations of an image. The user-indicated image name is the key, and the image's corresponsing model is the value.**

An implementation, ImageProcessorControllerImpl, exists, and uses the command pattern to achieve 
these modifications.

## ImageProcessorModel.java

**This interface contains the methods needed to modify and transform a PPM file. An instance of a 
model holds the information it needs from an image within its attributes so as to simplify the process.**

An implementation, ImageProcessorModelImpl, exists, and uses the command pattern to achieve
these modifications. It's methods are listed below.

`ImageProcessorModelImpl(String filename)`
This is the default constructor for an ImageProcessorModel. It takes in a String that indcates the location of the PPM file (subject to change) and determines the rest of the information needed through *ImageUtil*.

`ImageProcessorModelImpl(ImageProcessorModel model)`
This creates a deep copy of another model in order to use.

`public int getHeight()`
Provides a secure way to get the height of the image being modified by the ImageProcessorModel.

`public string getFileLocation()`
Provides a secure way to get the file location of the image being modified by the 
ImageProcessorModel.

`public String getExtension()`
Provides a secure way to get the extension of the image being modified by the ImageProcess

`public int getWidth()`
Provides a secure way to get the width of the image being modified by the ImageProcessorModel.

`public int getMaxValue()`
Provides a secure way to get the max possible value of each color component of the image being modified by the ImageProcessorModel.

`public String[][] getColorInfo()`
Provides a secure way to get the color info of the image being modified by the ImageProcessorModel.

`private String[][] deepCopyColorArray()`
A common method used to create a deep copy of the color array to modify.

`public void getColorComponent(String desiredComponent)`
This method converts the image into a greyscale image via the color component method. A color is inputed as the desired component, and the greyscale image is made by making all the pixels' color components the same value as the selected color.

`public void getValueComponent()`
This method converts the image into a greyscale image via the value method. For each pixel, whatever color component has the highest value becomes the dominant component, having all the other components in equal value to it.

`public void getLumaOrIntensityComponent(String desired)`
This method converts the image into a greyscale image either via the luma or intensity method. A helper method is used here to calculate luma or intensity as the code is similar between the two.

`private double getLumaOrIntensity(int r, int g, int b, String desired)`
This private helper method calculates the luma or intensity of a pixel.

`public void changeBrightness(String desiredChange, int num)`
This method changes the brightness of an image by an inputed number. The *desiredChange* string indicates whether it is wanted to brighten or darken the image, and the *num* int indicates the number by which it is wanted to brighten or darken by. An execption will be thrown if *num* causes the image's color components to exceed the global limt or to fall below 0.

`public void flipImage(String desiredChange)`
This method flips the image either horizontally or vertically. The *desiredChange* string indicates whether it is wanted to flip the image horizontally or vertically.

`public void simpleGreyscaleOrSepia(String desired)`
This method will either make a simple (luma) greyscale or a sepia copy of an image. The 
*desired* string indicates which operation will be performed.

`public void gaussianBlurOrSharpen(String desired)`
This method will either make a blurred or sharpened copy of an image. The desired string 
indicates which operation will be performed.

## ImageProcessorView.java

**This interface is a barebones implementation of a view, as not much is needed at this point of 
time. It simply allows for a message to be rendered to a desired destination.**


## ImageUtil.java

**This class containts various utility methods to get needed information from individual PPM images, as well as to build. Essentially, it contains methods to read and write PPM files.**

`public static int getInfo(String filename, String desired, String extension)`
This method returns information about an individual image file, with *filename* being the file's 
location, *desired* being the desired piece of information needed, and the *extension* being the 
files extension . The available information from this method are the image's width, height, and maximum color value.

`public static String[][] getColors(String filename, String extension)`
This method returns the color information of a PPM image. *filename* is the location of file. 
The color information is returned as a 2D string array, with the array's coordinates 
corresponding to the location of pixel in the image. *extension* is the extension of the image 
being read.

`public void buildImage(ImageProcessorModel model, String filename, String extension) throws 
IOException`
This method builds an image from an instance of a ImageProcessorModel. By analyzing the PPM 
specification, we are able to create a new PPM image from the ground up if we have our 
information in a string format. This infromation inclues the 2d color array, the height, the 
width, and the maximum color value. *extension* indicates the extension of the image desired.
