# Homework 9 Design Explanation

## Changes Made Based on Homework 8

Having read the instructions of Homework 9, I realized I have a few misunderstanding to how a few methods should work. The cleariest example is that I did not know what is RGB value, and I did not know how it should work in the Graphics classes, therefore, I wrote a setColor method for it to match the output written in Homework 9 only, without considering how it should work in Graphics classes. Therefore, for Homework 9, I changed the setColor method from:
```java
public void setColor(double red, double green, double blue) {
    this.color = String.format("(%.1f,%.1f,%.1f)", red, green, blue);
  }
```
to
```java
public void setColor(double red, double green, double blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }
```
Next, I read the Command Set and found out that the RGB values are necessary to create a photo, therefore, I change the constructor of AbstractShape (so are Rectangle and Oval classes) from taking in color to taking red, green and blue values. I changed the createPhoto method in ShapeModel class accordingly. 

Then, for move and resize, I thought the moving command is moving based on the existing center/min corner, so what I did was:
```java
  public void move(double dx, double dy) {
    x += dx;
    y += dy;
  }
```
Also, I comprehended the resizing as zoom in or out, therefore I wrote:
```java
  public void resize(double factor) {
    widthXR *= factor;
    heightYR *= factor;
  }
```

After reading the Command Set in Hw9, I changed them into:
```java
  public void move(double dx, double dy) {
    this.x = dx;
    this.y = dy;
  }
  
  public void resize(double XRwidth, double YRheight) {
    this.widthXR = XRwidth;
    this.heightYR = YRheight;
  }
```

The biggest change I made is the snapshot method. I wrote a very simple one so that it has the necessary functionality according to Hw8, but when I finished coding the view and controller part, the photos were not displaying properly. Then I realized that it is the snapshot method in ShapeModel not creating the copy of lists properly. Then I almost completely re-wrote the method to create a deep copy of each object of IShape, so that the later change into it will not affect the previous snapshots taken.
```java
 public List<Snapshot> snapshot(String description) {
    if (description == null) {
      throw new IllegalArgumentException();
    }
    List<IShape> snapshotShapes = new ArrayList<>();
    for (IShape shape: shapes) {
      if (shape instanceof Rectangle) {
        snapshotShapes.add(new Rectangle(shape.getName(),"rectangle", shape.getX(),
                shape.getY(), shape.getWidthXR(), shape.getHeightYR(), shape.getRed(),
                shape.getGreen(), shape.getBlue()));
      } else if (shape instanceof Oval) {
        snapshotShapes.add(new Oval(shape.getName(), "oval", shape.getX(),
                shape.getY(), shape.getWidthXR(), shape.getHeightYR(), shape.getRed(),
                shape.getGreen(), shape.getBlue()));
      }
    }
    snapshots.add(new Snapshot(description, snapshotShapes));
    return new ArrayList<>(snapshots);
  }
  ```
 For the sake of this method, I also created getters for RGB values in the AbstractShape which I didn't in Hw8.
 
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

## The END
The process of learning and developing the program was frustrating, but when I saw the program finally turned into what I expected, every moment of struggle just worthed the effort.

Thank you Prof.K and all the TAs, I think I participated more office hours and asked a lot more questions in this class than 5001. Without any of your patience I wouldn't have made it this far.
