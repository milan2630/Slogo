# SLOGO_QUESTIONS.md

## Milan Bhat (mb554), Ryan Weeratunga (rkw14), Robert Chen (rec43), Jaidha Rosenblatt (jrr59)

The purpose of the SLogo is to create an IDE to control a turtle with a pen. To do so, we will need a console to input directions from the user, a window displaying the turtle and its movements, and a backend to parse the commands. 

Parsing will occur when the user inputs a direction into the console. At this point, we will determine what action they are trying to have happen. 

The backend of the project will parse the information and then determine how to change the turtle. In this way, the backend both parses and receives the parsed information to decide the course of action. This action is then sent to the visualizer to display.

Error checking of the user's commands will be handled by the backend not being able to parse the command, and then sending an error message to the visualizer to display. 

The command does not know anything besides what is passed in from the user. For example, a command of moving forward 50 does not know the turtle's location and if this is allowed. This means that it is the backed parser's responsibility to determine if the command is a valid command and act appropriately. 

After a command is processed, the turtle in the visualizer moves in the desired way, a trail is created, and the command is added to the history. 