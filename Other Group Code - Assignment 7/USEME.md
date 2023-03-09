# Image Processor Application

## Script Commands Supported
<ul>
<li><h3>load filepath name</h3>
<ul>
<li>e.g. load src/images/cat.jpg cat</li>
<li>loads the given image from the filepath and maps it to the given name in the image
dictionary</li>
</ul>
</li>
<li><h3>save filepath name</h3>
<ul>
<li>e.g. save res/cat.jpg cat</li>
<li>saves the image mapped to the given name to the given filepath location</li>
</ul>
</li>
<li><h3>quit</h3>
<ul>
<li>e.g. quit</li>
<li>quit the image processor application</li>
</ul>
</li>
<li><h3>brighten value name newName</h3>
<ul>
<li>e.g. brighten 50 cat cat-brighter</li>
<li>brighten the image mapped to the given name by the given value and saves the image
as the given new name to the image dictionary</li>
</ul>
</li>
<li><h3>darken value name newName</h3>
<ul>
<li>e.g. darken 50 cat cat-darker</li>
<li>darkens the image mapped to the given name by the given value and saves the image
as the given new name to the image dictionary</li>
</ul>
</li>
<li><h3>vertical-flip name newName</h3>
<ul>
<li>e.g. vertical-flip cat cat-vertical-flip</li>
<li>vertically flips the image mapped to the given name and saves the new image as the new name to 
the image dictionary</li>
</ul>
</li>
<li><h3>horizontal-flip name newName</h3>
<ul>
<li>e.g. horizontal-flip cat cat-horizontal-flip</li>
<li>horizontally flips the image mapped to the given name and saves the new image as the new name to 
the image dictionary</li>
</ul>
</li>
<li><h3>value-component name newName</h3>
<ul>
<li>e.g. horizontal-flip cat cat-horizontal-flip</li>
<li>horizontally flips the image mapped to the given name and saves the new image as the new name to 
the image dictionary</li>
</ul>
</li>
<li><h3>intensity-component name newName</h3>
<ul>
<li>e.g. intensity-component cat cat-intensity</li>
<li>Turns the image mapped to the given name into greyscale using the intensity component 
calculation and saves the image as the given new name in the image dictionary</li>
</ul>
</li>
<li><h3>luma-component name newName</h3>
<ul>
<li>e.g. luma-component cat cat-luma</li>
<li>Turns the image mapped to the given name into greyscale using the luma component 
calculation and saves the image as the given new name in the image dictionary</li>
</ul>
</li>
<li><h3>red-component name newName</h3>
<ul>
<li>e.g. red-component cat cat-red</li>
<li>Turns the image mapped to the given name into greyscale using the red component 
calculation and saves the image as the given new name in the image dictionary</li>
</ul>
</li>
<li><h3>green-component name newName</h3>
<ul>
<li>e.g. green-component cat cat-green</li>
<li>Turns the image mapped to the given name into greyscale using the green component 
calculation and saves the image as the given new name in the image dictionary</li>
</ul>
</li>
<li><h3>blue-component name newName</h3>
<ul>
<li>e.g. blue-component cat cat-blue</li>
<li>Turns the image mapped to the given name into greyscale using the blue component 
calculation and saves the image as the given new name in the image dictionary</li>
</ul>
</li>
<li><h3>blur name newName</h3>
<ul>
<li>e.g. blur cat cat-blur</li>
<li>Blurs the image mapped to the given name using the gaussian blur matrix and saves
the image with the given new name to the image dictionary</li>
</ul>
</li>
<li><h3>sharpen name newName</h3>
<ul>
<li>e.g. sharpen cat cat-sharpen</li>
<li>Sharpens the image mapped to the given name using the sharpening matrix and saves
the image with the given new name to the image dictionary</li>
</ul>
</li>
<li><h3>greyscale name newName</h3>
<ul>
<li>e.g. greyscale cat cat-greyscale</li>
<li>Turns the image mapped to the given name into greyscale using the luma component 
matrix and saves the image as the given new name to the image dictionary</li>
</ul>
</li>
<li><h3>sepia name newName</h3>
<ul>
<li>e.g. sepia cat cat-sepia</li>
<li>Turns the image mapped to the given name into sepia color transformation using the linear matrix
provided for sepia and saves the image as the given new name to the image dictionary</li>
</ul>
</li>
</ul>