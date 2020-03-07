parser
====

This project implements a development environment that helps users write SLogo programs.

Names:Milan Bhat (mb554), Ryan Weeratunga (rkw14), Robert Chen (rec43), Jaidha Rosenblatt (jrr59)


### Timeline

Start Date: 2/13

Finish Date: 3/7

Hours Spent: 320 total

### Primary Roles
* Milan Bhat - Backend
    * Primary Responsibilities
        * Parsing
        * Command + subclasses
        * Error Handling
        * Turtle
        * Pen
        * Trail
    * Secondary Responsibilities
        * Controller
* Ryan Weeratunga - Tabs and Explorers
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
* Jaidha Rosenblatt - Front End
    * Primary Responsibilites
        * Styles
        * SettingView
        * Components package (actions, component, factory, many objects)
        * SettingTab
        * TurtleManager
        * TurtleView
        * Turtle (gui)
        * Trail (gui)
        * Terminal
        * Impelementing listner design pattern
    * Secondary Responsibilities
        * History
        * HistoryView
        * Variable
        * VariableExplorer
        * VariableExplorerView
        * Method
* Robert Chen - Controller
    * Primary Responsibilities
        * Controller
    * Secondary Responsibilities
        * Parsing
        * Command + subclasses
        * Error Handling
        * Turtle
        * Pen
        * Trail

### Resources Used


### Running the Program

Main class: slogo.Main.java, mark /resources as resources root

Data files needed: Everything under resources/ and slogo/resources/

Features implemented:
* Basic commands for moving the turtle, math operations, boolean operations, turtle queries, variables, and control structures
* User defined commands
* Error catching for parsing issues
* Seeing results of turtle executing commands
* Set background color, pen color, pen thickness, turtle image from UI and from commands
* See current variables, user-defined commands, color palette, current turtle states, and history in different tabs
* Choose language to operate in
* Changing language can happen dynamically and will change history and user-defined commands as well
* Accessing a help page
* Clicking to execute commands
* Clicking to load history
* Moving the turtles graphically
* Change pen properties graphically
* Recognize and correctly perform multiple turtle commands
* Recognize and correctly perform display commands
* Recognize and correctly perform commands with unlimited parameters
* Allow users to save and load the state of the environment
* 


### Notes/Assumptions

Assumptions or Simplifications:

* With multiple turtles, if a command is run that involves parsing again, such as Repeat, the internal parsing command will be run on each turtle a number of times equal to number of active turtles.
* Assume no issues with properties files
* Always need at least one turtle active


Interesting data files:

Known Bugs:
* Clear screen command does not work clear since it is ovveridden by parser passing immutable turtles

Extra credit:
* Dark mode
* Slider for changing GUI turtle movement

### Impressions
Our impression of this project is that it taught us about properly separating the front end and backend, as well as how to divide responsibility. By first developing API interfaces, we created contracts with each other dictating the actions that each of us could perform in one another's area of work. While we changed these APIs significantly as we worked, we believe that this is due to the fact that this was our first time designing APIs. Now having a better understanding of what to think about while designing APIs, we will definitely be able to design future APIs that are more accurate to what is actually needed.

It was also super helfpul to practice data-driven design using reflection. It was very satisfying to see how using reflection led to shorter code and more flexible design. We also learned about how to use event listeners for various objects and the challenges that arise within them.