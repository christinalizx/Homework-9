# Shapes_Photo_Album
This is a project that implemented the concepts of Object-Oriented Programming and MVC using Java and Html. The controller takes inputs from local text file and displays the image through viewer.
## Model
A good model should have only one class connects to the controller, so I created a ShapeModel class to summarize the methods. The shape model has the method of creating photo, move/resize/recolor, reset, getShapes, snapshot, and potentially two toString method. Everytime the class snapshot is called, it will return the current list of shapes by creating a new list, so it doesn't modify the shapes list. the getShapeString is the toString method for shapes whereas the actual toString method is for the snapshot list.
 ## Controller
For a MVC structure, there has to be a controller as well as model. What I had in mind was to draw the shapes on a canvas according to the shape info in snapshot lists, then save them as images, then in another class I extract the images from the folder that saved them to display in the pop-up window and html. Therefore, I wrote the controller class to loop over each snapshot, create a new canvas for this snapshot and then loop over each shape in this snapshot and draw it on the canvas, finally save the canvas to a file with a filename based on the snapshot timestamp.

## View
For the view part, I first developed a file-reader, the development of it is very time-consuming and requires precision, but it is not difficult. I simply used switch cases to tell if what was read from the file should lead to what action, and which part of the text equals to which parameter in the action methods. This is where it made me found out there was something wrong with my snapshot method in the ShapeModel class.

In addition, the Command Set told us that they take name as one of the parameters to change/modify the shapes, whereas I used the IShape object as the first parameters in those action methods. I thought it would be time-consuming and easily causing errors to change parameters in the action methods, instead, I loop through the shape lists using the name to find the matching object and it worked well:
```java
            String shapeName = parts[1];
            IShape shapeToMove = null;
            for (IShape shape : shapeModel.getShapes()) {
              if (shape.getName().equals(shapeName)) {
                shapeToMove = shape;
                break;
              }
            }
```

Later, I built a Window class to implement the GUI functions. To perform the following functionalities, I created four buttons and a jumpComboBox to allow users to select which photo they want to "jump" to. The information of id and description are displayed at the right corner of the window. In addition, I used MyCloseListener class from Lecture resource as the "quit" button's action-listener.

```text
View the snapshot information (unique id and description)
"page forward" and show the next snapshot if it exists. If no further snapshots exist, a message to the user should indicate that
"page backward" and show the previous snapshot, if there is a previous one, If there is no "previous" a message to the user should be shown
"jump" to a snapshot the user explicitly selects from a list of options
```
In the end, I developed a HtmlView class to help me generate .html file and save it to the local repository every time I chose to display a web view. This is pretty straightforward that what I did is self-studyed html language, and asked the program to read the snapshots lists and write variation part for me.
