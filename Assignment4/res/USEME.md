## Available commands
> An example script file is unable to be provided as we could not figure out how to get the jar 
> to accept relative paths. The jar itself does work, and will accept absolute paths via scripts 
> and terminal input. If running the program directly in an IDE, relative paths will work.

`load image-path image-name` 
This command will load an image from the specified path and make a model out of it ready to be used.
**This command must be run first for the rest of the commands to run, especially with the jar 
file.**

`save image-path image-name`
This command will save an image model to an image in the specified path. 

`red-component origin-image destination-image`
This command will create a greyscale image via the red component of the origin image and create 
a new model with the name of destination image.

`green-component origin-image destination-image`
This command will create a greyscale image via the green component of the origin image and create
a new model with the name of destination image.

`blue-component origin-image destination-image`
This command will create a greyscale image via the blue component of the origin image and create
a new model with the name of destination image.

`value-component origin-image destination-image`
This command will create a greyscale image via the value component of the origin image and create
a new model with the name of destination image.

`luma-component origin-image destination-image`
This command will create a greyscale image via the luma component of the origin image and create
a new model with the name of destination image.

`intensity-component origin-image destination-image`
This command will create a greyscale image via the intensity component of the origin image and 
create a new model with the name of destination image.

`horizontal-flip origin-image destination-image`
This command will horizontally flip the origin image and save it to a new model with the name of 
the destination image.

`vertical-flip origin-image destination-image`
This command will horizontally flip the origin image and save it to a new model with the name of
the destination image.

`brigthen num origin-image destination-image`
This command will either brighten or darken the origin image and save it to a new model with the 
name of the destination image. To brighten, use a positive number. To darken, use a negative number.

`blur origin-image destination-image`
This command will blur the origin image and save it to a new model with the name of the destination image.

`sharpen origin-image destination-image`
This command will sharpen the origin image and save it to a new model with the name of the 
destination image.

`greyscale origin-image destination-image`
This command will create a luma greyscale image of the origin image and save it to a new model with 
the name of the destination image.

`sepia origin-image destination-image`
This command will create a sepia image of the origin image and save it to a new model with the 
name of the destination image.

`debug`
This command will display the working directory of the main instance.

## GUI Interface Operation Manual
> Operate all actions of the command line mode through the visual interface, and the corresponding picture and its color scale histogram will be displayed at the same time

`load a image`
Click the load button, and a file selection box pops up. Select a prepared picture, and then click the select button. Then the file is loaded into the system.

`save a image`
Select a key from the image list, click the save a image button, and then select a save directory to save an image.

`red-component`
This buttion will create a greyscale image via the red component of the origin image and create
a new model with the name of destination image,Then display the new picture and its color scale on the interface

`green-component`
This buttion will create a greyscale image via the green component of the origin image and create
a new model with the name of destination image,Then display the new picture and its color scale on the interface

`blue-component`
This buttion will create a greyscale image via the blue component of the origin image and create
a new model with the name of destination image,Then display the new picture and its color scale on the interface

`value-component`
This button will create a greyscale image via the value component of the origin image and create
a new model with the name of destination image,Then display the new picture and its color scale on the interface

`luma-component`
This button will create a greyscale image via the luma component of the origin image and create
a new model with the name of destination image,,Then display the new picture and its color scale on the interface

`intensity-component`
This button will create a greyscale image via the intensity component of the origin image and
create a new model with the name of destination image,Then display the new picture and its color scale on the interface

`horizontal-flip`
This button will horizontally flip the origin image and save it to a new model with the name of
the destination image,Then display the new picture and its color scale on the interface

`vertical-flip`
This button will horizontally flip the origin image and save it to a new model with the name of
the destination image,Then display the new picture and its color scale on the interface

`brigthen`
This button will either brighten or darken the origin image and save it to a new model with the
name of the destination image. To brighten, use a positive number. To darken, use a negative number,
Then display the new picture and its color scale on the interface

`blur`
This button will blur the origin image and save it to a new model with the name of the destination image,
,Then display the new picture and its color scale on the interface

`sharpen`
This button will sharpen the origin image and save it to a new model with the name of the
destination image,Then display the new picture and its color scale on the interface

`greyscale`
This button will create a luma greyscale image of the origin image and save it to a new model with
the name of the destination image,,Then display the new picture and its color scale on the interface

`sepia`
This button will create a sepia image of the origin image and save it to a new model with the
name of the destination image,Then display the new picture and its color scale on the interface