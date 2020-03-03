# SLOGO_APIs

## Milan Bhat (mb554), Robert Chen (rec43), Ryan Weeratunga (rkw14), Jaidha Rosenblatt (jrr59),

### External Backend

The backend will be running external modules so there will be no methods that other modules can access to manipulate the state of the program. 


### Internal Backend

The backend must be able to read the xml file 
and distinguish which commands corresponds to particular actions. The backend must have methods for determining actions based on the input. This means that there will be an API that returns transfers of data of the commands between classes.

### External slogo.view

The backend will be controlling the updating of the slogo.view based on when it receives the parsing information. Therefore, the slogo.view must have an external API to control the turtle's movement on the screen. Additionally, the backend will be receiving input from the Console in the slogo.view so the API must include the ability to get commands from the console. 


### Internal slogo.view
The internal slogo.view will handle the rendering process for the visualization of our turtle and the terminal.

