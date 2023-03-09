# Image-Processor

## How to Use
### Jar File
<ol>
<li>Navigate to the jar file located in res/hw04.jar in a terminal or command prompt</li>
<li>type "java -jar hw04.jar --filename ExampleScript.txt" to run the example script</li>
<li>OR type "java -jar hw04.jar --text" to run the program through command line inputs</li>
</ol>

### Edit Configurations
<ol>
<li>Open IntelliJ project, go to Run -> Edit Configurations</li>
<li>Click the plus (+) and add a new Application Configuration</li>
<li>Make sure the SDK is set to Java 11</li>
<li>Set the Main class as "ImageMain"</li>
<li>In command line arguments type "--filename ExampleScript.txt"</li>
<li>Make sure the working directory is set to the hw04 project directory</li>
<li>Click ok and press the green run button at the top of the IDE</li>
</ol>

![Screen Shot 2022-11-03 at 1 19 32 PM](https://user-images.githubusercontent.com/85259969/199790688-d1d88027-2b37-4d95-b540-10556c23f397.png)

## MODEL
### Interfaces
<ul>
  <li>ImageDict: Interface representing a dictionary of images and the methods it can use</li>
  <li>Image: Interface representing an Image and the methods it can use</li>
  <li>Pixel: Interface representing a pixel and the methods it can use</li>
</ul>

### Classes
<ul>
  <li>ImageDictImpl: Class representing a dictionary of images to store while application is running as &ltString name, Image image&gt</li>
  <li>PPMImage: Class representing a PPM Image stored as a 2D array of Pixel objects as its field</li>
  <li>PixelImpl: Class representing a Pixel object with a row, a column, and rgb values</li>
  <li>imageUtil: Utility class to read a PPM image</li>
</ul>

<br>

## VIEW
### Interfaces
<ul>
  <li>ImageProcessView: Interface representing the methods the Image Processor view can use</li>
</ul>

### Classes
<ul>
  <li>ImageProcessViewImpl: Class representing the Image processor view</li>
</ul>

<br>

## CONTROLLER
### Interfaces
<ul>
  <li>ImageProcessorController: Interface representing the controller methods</li>
</ul>

### Classes
<ul>
  <li>ImageProcessorControllerImpl: Class representing the controller to handle user inputs</li>
</ul>

<br>

## Commands
### Interfaces
<ul>
  <li>ImageCommand: Image representing an image command class</li>
</ul>

### Classes

<ul>
<li><h4>Brightness</h4>
<ul>
<li>ABrightness: An abstract class that represents changing the brightness
of an image</li>
<li>Brighten: Brighten command class extending ABrightness to brighten
the image</li>
<li>Darken: Darken command class extending ABrightness to darken
the image</li>
</ul>
</li>
<li><h4>Flip</h4>
<ul>
<li>Horizontal: Horizontal flip command class to flip the image horizontally</li>
<li>Vertical: Vertical flip command class to flip the image vertically</li>
</ul>
</li>
<li><h4>Greyscale</h4>
<ul>
<li>AGreyScale: Abstract class that represents turning an image into greyscale</li>
<li>Red: extends AGreyscale and visualizes the red-component of the image</li>
<li>Green: extends AGreyscale and visualizes the green-component of the image</li>
<li>Blue: extends AGreyscale and visualizes the blue-component of the image</li>
<li>Value: extends AGreyscale and visualizes the image based of the max value of
any of the rgb components</li>
<li>Intensity: extends AGreyscale and visualizes the average of the three rgb components</li>
<li>Luma: extends AGreyscale and visualizes a weighted average of the three
rgb components calculated by 0.2126r + 0.7152g + 0.0722b</li>
</ul>
</li>
<li><h4>Filter</h4>
<ul>
<li>AFilter: Abstract class that represents filtering an image using a kernel</li>
<li>KernelOperator: Operation class for kernels which performs the kernel operation
on the specified pixel at (row, col)</li>
<li>Blur: extends AFilter and blurs an image using the Gaussian blur matrix</li>
<li>Sharpen: extends AFilter and sharpens an image using the sharpening filter matrix</li>
</ul>
</li>
<li><h4>Color Transformation</h4>
<ul>
<li>AColorTransformation: Abstract class that represents a color transformation of an image 
using a linear kernel operation</li>
<li>LinearKernelOperator: Operation class for kernels which performs the linear kernel operation
on the specified pixel at (row, col)</li>
<li>Greyscale: extends AColorTransformation and turns the image to greyscale using the luma
greyscale matrix</li>
<li>Sepia: extends AColorTransformation and turns the image into sepia color transformation using
the given sepia matrix</li>
</ul>
</li>
<li><h4>Load</h4>
<ul>
<li>Load: load command class that can load ppm image or any supported image type
in Java ImageIO class</li>
</ul>
</li>
<li><h4>Save</h4>
<ul>
<li>Save: save command class that can save ppm image or any supported image type
in Java ImageIO class to the given filepath</li>
</ul>
</li>
</ul>
<ul>
</ul>

<br>

## Citations
<ul>
<li>color.ppm is original work created using GIMP-2.10</li>
<li>cat.png: Photo by Pixabay from Pexels: https://www.pexels.com/photo/white-and-grey-kitten-on-brown-and-black-leopard-print-textile-45201/</li>
</ul>
