# Easy-JavaFX-Wrapper
Easy-JavaFX-Wrapper is a JavaFX library that helps you quickly deploy any GUI application.

# Where can I use this and why would I?

Imagine these scenarios:  
You were assigned to build a Desktop application. This app is written in Java and you need to have a GUI working as fast as possible without any fuss. You don't have time to learn JavaFX, nor do you know how to add JavaFX to the enormous application that you already have currently built.

OR:

Most of your application is executed on the command line. For some cases, you feel like displaying a graph would be a good idea, but don't feel like changing the current code to comply with JavaFX's Application thread. You just want to call a single function in your code and that's it.


If this is you, then this library is perfect for your case.

# How it works

This library lets you build GUI applications simply by creating a folder named `app`.
Within this `app` lives any GUI component that you want to display on your main application. This can be a full complex GUI application (web-page like) which consists of many different windows, or it can be a simple modal that you might want to display in your command line application.

For instance, imagine you have this directory structure:

`app\`  
&nbsp;&nbsp;&nbsp;&nbsp;`component1`  
&nbsp;&nbsp;&nbsp;&nbsp;`component2`  
&nbsp;&nbsp;&nbsp;&nbsp;`window1`  
&nbsp;&nbsp;&nbsp;&nbsp;`window2`  

Within each folder you might find these files:


`app\`  
&nbsp;&nbsp;&nbsp;&nbsp;`template\`  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`Scene.java`  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`Scene.fxml`  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`Controller.java`  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`strings.properties`  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`styles.css`  

These are in fact template files. Each folder (also known as Scene), has to have these files inside in order for that folder to be interpreted by the library as an actual Scene.

# Library Usage

The minimal files that need to exist inside each Scene to satisfy the requirement is the file `Scene.java`, and it can be templated as such:

```java
package app.start;

import gui.GenericScene;
import javafx.stage.Stage;

public class Scene extends GenericScene {

	public Scene(Stage stage) {
		super(stage);
		
		// Add your code here to modify this scene specifically
	}
}
```

In this case the complete folder structure of your project would be:   
`app\`  
&nbsp;&nbsp;&nbsp;&nbsp;`start\`  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`Scene.java`  

`config\`  
&nbsp;&nbsp;&nbsp;&nbsp;`config.properties`  

`Main\`  
&nbsp;&nbsp;&nbsp;&nbsp;`Main.java`  

`start` would be equivalent to a general purpose start page, like a login web page for instance. Also, notice the folder `config`, within which lies the file `config.properties`.
This file contains all startup configuration settings that the library needs in order to start up correctly.

An example of the `config.properties` file:
```
applicationName = Myapp
topApplicationPackage = app
locale = en
firstStage = start
```


Then, in your main application, you could have this:
```java
package Main;

import gui.JFX;

public class Main {
	public static void main(String[] args) {
		JFX.initialize(args);
		System.out.println("Welcome to " + JFX.APP_NAME);
	}
}
```

After executing `JFX.initialize(args)`, the class `app.start.Scene` is instantiated, where you can basically do anything you want.

Here's an example:


```java
package app.start;

import gui.GenericScene;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Scene extends GenericScene {
	
	public Scene(Stage stage) {
		super(stage);

		//Drawing a Rectangle 
		Rectangle rectangle = new Rectangle();  
		
		//Setting the properties of the rectangle 
		rectangle.setWidth(300.0f); 
		rectangle.setHeight(150.0f);   
		rectangle.setFill(Color.GREENYELLOW);
		
		// Add it to the window
		Group group = (Group)root;
		group.getChildren().add(rectangle);
	}
}
```

The program will display the following:  
![Example 1](https://image.prntscr.com/image/xf0_JyOYRaKDpIe716AJMQ.png)

One disadvantage might be the fact that you need to create a folder for such a simple thing (for instance, a Modal which requests user confirmation). If this is such a big problem for you, then you could use the Swing library, however, if you want to create your own customized modal then this library is not a bad idea after all. Besides, you are able to reuse the Scenes like this:
```java
package Main;

import gui.JFX;

public class Main {
	public static void main(String[] args) {
		JFX.initialize(args, false);
		JFX.launchScene("start");
		System.out.println("Welcome to " + JFX.APP_NAME);
		
		// Do your thing here...
		
		// Launch start again
		JFX.launchScene("start");
	}
}
```
Output:  
![Example 2](https://image.prntscr.com/image/bROnEzhdQPyQ3pi-D4yX9A.png)

# Handling Events

In addition to the `Scene` class, you have the `Controller` class, which is responsible for handling any kind of event.

```java
package app.start;

import gui.GenericController;

import javafx.event.Event;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller extends GenericController {

	public Controller(Stage stage, Scene scene, Parent root) {
		super(stage, scene, root);
		// Add your code here
	}

	@Override
	public void handle(Event event) {
		// Add your code here
	}
}
```

If you want to trigger an event whenever the user passes the mouse over the Window, go to the `Scene` class and add the following to the constructor:
```java
Group group = (Group)root;
group.getChildren().add(rectangle);

// Add the following line
addEvent(root, MouseEvent.MOUSE_MOVED, "mouseMoved");
```

Then, go to the Controller class and add the following method:

```java
@FXML private void mouseMoved(Event event) {
	System.out.println("Mouse moved");
}
```

With only two steps, the library was capable of binding that specific `MOUSE_MOVED` event to the controller's private handler method.

The output of this is:  
![Example 3](https://image.prntscr.com/image/OSCDxfanRP_PE2s8d7z3Tw.png)

Remember that you are not forced to add events only through the constructor. You can bind the events through the FXML file just as easily.

# Screenshot

Here's a slightly more complex example:

![Example 4](https://image.prntscr.com/image/0KpgFDdoQ_qvdF_Ybr4EBQ.png)
![Example 5](https://image.prntscr.com/image/N_ZMCwMuSseneStz84qfZQ.png)


The folder structure and code for these images can be found in this repository.   
Just enter the folder `src` and you'll find the `Main` folder (where the `main` function lies), the `app` folder (which is the example application in this case), `config` and finally `gui` (which contains this library).
