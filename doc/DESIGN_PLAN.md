# DESIGN_PLAN.md

## Milan Bhat (mb554), Ryan Weeratunga (rkw14), Robert Chen (rec43), Jaidha Rosenblatt (jrr59)


Our program design involves a front-end view which handles everything displayed to the user, a backend Model which handles the logic of commands, and a Controller to send information back and forth between appropriate classes. The Controller is responsible for communicating with both the front end and backend. 


### Introduction:
The primary design goal of this project is to organize our code into internal and external APIs, that rely on abstractions for flexibility without needing to know specific implementations of our methods. The design is closed in the backend for the translation of commands in the parser and the execution of those commands in the turtle/pen, and closed in the frontend for updating the display from the imageviews. The design is open in the backend for parsing strings into commands, acting on the commands, and getting various variables such as the position of the turtle, the status of the pen, etc. The design is open in the frontend for getting the string input from the console, and updating the display of all the variables mentioned above.


### Overview
![Image of Display](https://i.imgur.com/kD51KRg.png)

We will have frontend and backend APIs and a controller to connect them.
#### Frontend External API
This API will take in a string from whatever the user inputs into the console. It will take in adjustsments to the turtle position and heading as well as the pen path.

* Visualizer
    * Variables
        * Instance of Display
        * Instance of ConsoleView
        * Instance of UserInterface
        * Instance of VariableExplorerView
    * Methods
        * public Visualizer (Display display, Console console, UserInterface ui, VariableExplorerView variableExplorerView)
        * public Scene getVisualizer()
        * public void createButton(EventHandler event, String property)
        * public String getLanguage()
        *  public String getConsoleString()
        
* TurtleView
    * Variables
        * image
            * ImageView
        * position
            * Point
        * heading
            * double
    * Methods
        * public double updateHeading(double heading)
        * public double updatePositions(double x, double y)
* PenView
    * Variables
        * image
            * ImageView
        * isDisplayed
            * boolean
    * Methods
        * public boolean updatePenState()
    
* ViewException

#### Frontend Internal API
This API handles updating the display by rendering the TurtleView, PenView, and TrailView.

* Display
    * Variables
        * Instance of TurtleView
        * Instance of TrailView
        * Instance of PenView
    * Methods
        * public Visualizer(TurtleView turtle, TrailView trail, PenView pen)
        * public HBox getDisplay()
* VariableExplorerView (binded with VariableExplorer)
    * Variables
        * variables
            * list
            * map
    * Methods
        * public HBox getVariableExplorerView()
* ConsoleView
    * Variables
        * size
            * double
    * Methods
        *  public void displayError(Exception error)
        *  public HBox getConsoleView()
* User Interface
    * Methods
        * public void createButton(EventHandler event, String property)
        * public HBox getUserInterface()


* TurtleView
    * Variables
        * image
            * ImageView
        * position
            * Point
            * double x, double y
        * heading
            * double
            * Heading class
    * Methods
        * public void updateTurtleDisplay(TurtleView turtle)
        * public void setTurtleImage(Image image);
* PenView
    * Variables
        * image
            * ImageView
        * isDisplayed
            * boolean
    * Methods
        * public void setPenImage(Image image);
        * public void updatePenDisplay(PenView pen);
        
* TrailView
    * Variables
        * color
            * Color
            * String (hex)
        * coordinates
            * List of coordinates
            * Point start
            * Point end
    * Methods
        * public void updateTrailDisplay(TrailView trail)
        * public void setTrailColor(Color color)

#### Backend External API
This API takes in a commands as a strings. It also can get properties for the turtle and pen (position, color, heading, pen state).

* Turtle
    * Variables
    * Methods
        * public double getTurtleX()
        * public double getTutleY()
        * public double getTurtleHeading()
        * public boolean isVisible()
* Pen
    * Variables
    * Methods
        * public int getPenState()
* Parser
    * Variables
        * commands
            * list
            * queue
    * Methods
        * public List(Command) parseStringToCommands(String input)
        * public void actOnCommand(Command command)

#### Backend Internal API
This API handles the creation of a command in the parser and translates commands into changes of the turtle and pen.
* Turtle
    * Variables
        * double x
        * double y
        * double heading
        * boolean isVisible
    * Methods
        * public void updateTurtle(Command command)
* Pen
    * Variables
        * double x
        * double y
        * boolean isVisible
    * Methods
        * public void updatePen(Command command)
* Trail
    * Variables
        * double startX
        * double startY
        * double endX
        * double endY
        * double heading
        * boolean isVisible
    * Methods
        * public void updateTrail(Command command)
* Command
    * Interface class that holds an action
* Method
    * Variables
        * commands
            * List of commands
            * Queue of commands
    * Methods
        * public Method (List(Command) commandList)
* Variable
    * Interface class that holds an variable
* History
    * Variables
        * History
            * Queue of commands
            * List of commands
    * Methods
        * public void updateHistory()
* Error Handling Classes
    * ParsingException
    * SlogoRuntimeException

### User Interface

The user will interact with the program by typing commands into the console. There will also be a button to load an image for the display of the turtle. When pressed, this will display the File Manager screen where the user can pick the image they would like to use. The user will also be able to set the color of the pen by typing in the hexcode value of the desired color into a text field. The change will occur once the user presses 'Enter'. A similar text field will be used to set the color of the background where the user will be asked to enter the hexcode value of the desired color. There will also be a combo box object where the user can choose which language the console will read. On the top right, there will be a display that contains the location of the turtle, the pen, and the trail. On the bottom, there will be the console where the user can type commands. To run the commands, the user must press the button "Run" before the commands are parsed. 

![Image of Display](https://i.imgur.com/dWgRv30.jpg)



### Design Details
#### Frontend External API
The idea behind this API is that it receives the string inputted to the console and also has methods to update variables like the turtle's position, the heading, the pen state, etc. It supports features in Basic like setting a background color for the turtle, turning the pen on or off, setting the pen's color, getting the text commands from the console, etc. It can be extended to allow other visual changes, like toggling the turtle on or off. This will include classes like TurtleView, PenView, TrailView, which are necessary to communicate with the controller that will link the frontend to the corresponding backend Turtle, Pen, and Trail classes, and include the Console.

#### Frontend Internal API
The frontend internal API updates the display based on the views. This represents the visual display of the results of the commands. It includes the Display class, which holds the visualization of the turtle, pen and trail as well as GUI elements and is necessary in order to be able to update those values and throw errors.

#### Backend External API
The backend external API handles the parsing of the string taken from console to the Command class so that it can be interpreted. It also acts on the commands and is able to return information about the frontend variables (turtle position, pen status, etc.). This includes classes like Turtle, Pen, and Trail, which hold information about its state and values, like color, position, on or off, etc. It also includes Parser, which will read the string and pass it to Command.

#### Backend Internal API
The backend internal API recognizes and interprets the commands read in from the Parser, and also acts on the commands in the Turtle, Pen, and Trail classes. Classes such as Command, Method, and History will be included in the backend internal API. Command will be an abstract class that represents a text command, and each possible command from the list will be its own subclass of Command. Method is a group of Commands and is executed in sequence in order to account for entering several commands to the console at once. History stores past commands. A Variable abstract class is also necessary to store variables that the user creates in the console.

### API as Code

The classes FrontEndExternal, FrontEndInternal, BackEndExternal, and BackEndInternal in the slogo package of the repository are the interfaces with the APIs. 





### Design Considerations

- One design decision we made was to have a Turle, Pen, and Trail class for the backend implementation and a TurtleView, PenView, and TrailView class for the frontend implementation. We decided to do this so our program would follow open principles and so the display of these features would be enclosed separately from the actual implementation of the commands. An alternative would have been to only have one class for the Turtle, Pen, and Trail that would contain the methods for completing the commands and the display, but we decided against it so our program would have frontend and backend classes. By doing this, we created a dependency between these frontend and backend classes. 
- Another design decision we made was to create an abstract Command class and have subclasses for each category of command: syntax, turtle commands, turtle queries, math operations, boolean operations, and user-defined commands. We decided to do this because we wanted to ensure that every command subclass object would have its own implementation for doing its command. In addition, the command subclass object would store the information for how long to do the command, such as the amount of steps to move forward or the amount to turn. An alternative to this would be to only have syntax, turtle, and operation subclasses, but we decided the additional subclasses would allow for more flexibility. 
- We also decided to have a Trail and TrailView class instead of just marking the pixels that were touched by the pen because we wanted to store the location of the trail in case the turtle would have to interact with the trail in furture implementations. 
- We also decided on a Method class and a History class to store past commands from the user. The Method class will hold a List of Command objects and the History class will hold a List of Method objects. The History class will store the methods created by the user and will eventually display an immutable List of past actions. In addition, the user will be able to rerun past Method objects that they have created. We decided to make these two classes because it allows us to encapsulate a sequence of commands as supposed to singular commands. An alternative that we discussed was only having a History class that had a List of Command objects, but this would only allow the user to rerun commands one at a time. This creates a dependency between the Command subclasses, Method class, and the History Class. 
- When the user inputs a sequence of commands, we will first parse all the information and create a list of commands. Then, this list of commands will executed one at a time. If a runtime error occurs where the turtle is going out of bounds, the turtle will stop and an alert will appear that will show a runtime error that we have written. For example, if the user writes 5 commands and then runs the sequence, but the turtle goes out of bounds on the second command, then the turtle will stop and the following commands will not be executed. An alternative to our decision would be to make the turtle turn around once it reaches the end or make the turtle appear on the other side of the screen, but we decided it would make more sense if it stopped because it would resemble a Java index out of bounds exception. This creates a dependency between the Turtle class and the subscene on the display because the backend Turtle class must know the height and width of the screen. 


### Use Cases

* fd 50
    * Controller gets string from console
    * Controller passes string to parser and gets a list of commands in return
    * Controller passes command for fd 50 to Turtle
    * Turtle updates itself, Pen, and Trail
    * Controller updates history
    * Controller gets new Turtle, Pen, and Trail states
    * Controller passes new states to TurtleView, PenView, and TrailView to update them
    * Controller passes Views to Display and Display updates the GUI
    
* HIDETURTLE
    *  Controller gets string from console
    * Controller passes string to parser and gets a list of commands in return
    * Controller passes command for HIDETURTLE to Turtle
    * Turtle isVisible is set to true
    * Controller updates history
    * Controller gets new Turtle state
    * Controller passes new states to TurtleView
    * TurtleView sets imageView to null
    * Controller passes Views to Display and Display updates the GUI
* User sets the pen color with RGB value in user interface
    * When RGB value is set, it is passed to the UI
    * Controller gets RGB value from the UI
    * Controller passes RGB value to PenView
    * PenView updates color

* User enters an invalid command
    * Controller gets command string from console
    * Controller passes string to Parser
    * Parser attempts to parse the string but throws a Parser error
    * Controller catches parser error
    * Controller calls on the Console to create an error message dialog

* User has a list of commands, one of which causes the Turtle to go off the screen
    * Controller gets string from console
    * Controller passes string to parser and gets a list of commands in return
    * Controller loops through list and passes one command at a time to the Turtle
    * Turtle handles 1st command properly, in the way of the first use case
    * Turtle reaches command that causes it to leave screen
    * Turtle knows screen limit and throws SlogoRuntimeException: out of bounds
    * Controller catches exception
    * Controller stops the list of commands from being run
    * Controller calls on console to create an error message dialog

* User changes language to French
    * Change in language is passed to the UI
    * Controller gets new language from UI
    * Controller sets property file in Parser to the appropriate property file

* User creates a variable
    * Controller gets the input from the console
    * Controller passes the input to the parser.
    * Parser parses the input and recognizes that a new variable must be created.
    * A new Variable object is created and stored in a list in VariableExplorer.
    * History is notified that a new variable was created.

* User accesses help about available commands
    * A pop-up window is created that displays the list of possible commands and examples of them.

* User sets an image for the turtle
    * User presses button in the User Interface to change Image of Turtle
    * This will open a File Manager and the user will have to select a jpeg image
    * Change in Image is passed into the UI
    * Controller gets new Image
    * Controller passes in the Image to the TurtleView Class
    * Image is reshaped so that it is the correct height and width
    * Image is set as the new ImageView object for the TurtleView object

* User prompts console to clearscreen
    * User types "CLEARSCREEN" or "CS" into console
    * User presses the button "Run"
    * Controller gets String from console
    * Controller passes String into the Parser Class and gets a list of commands in return
    * Controller passes command for CS to Turtle
    * Turtle updates itself, Pen, and Trail
    * Controller updates History
    * Controller gets new Turtle, Pen, and Trail states
    * Controller passes new states to TurtleView, PenView, and TrailView to update them 
    * Controller passes Views to Display and Display updates the GUI

### Team Responsibilities

* Milan Bhat
    * Primary Responsibilities
        * Parsing
        * Command + subclasses
        * Error Handling
        * Turtle
        * Pen
        * Trail
    * Secondary Responsibilities
        * Controller
* Ryan Weeratunga
    * Primary Responsibilities
        * History
        * HistoryView
        * Variable
        * VariableExplorer
        * VariableExplorerView
        * Method
    * Secondary Responsibilities
        * PenView
        * TurtleView
        * TrailView
        * Visualizer
        * Console
        * UserInterface
        * Display
* Jaidha Rosenblatt
    * Primary Responsibilites
        * PenView
        * TurtleView
        * TrailView
        * Visualizer
        * Console
        * UserInterface
        * Display
    * Secondary Responsibilities
        * History
        * HistoryView
        * Variable
        * VariableExplorer
        * VariableExplorerView
        * Method
* Robert Chen
    * Primary Responsibilities
        * Controller
    * Secondary Responsibilities
        * Parsing
        * Command + subclasses
        * Error Handling
        * Turtle
        * Pen
        * Trail