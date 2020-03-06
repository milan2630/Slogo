# REFACTORING_DISCUSSION

## Milan Bhat (mb554), Ryan Weeratunga (rkw14), Robert Chen (rec43), Jaidha Rosenblatt (jrr59)

* Turtle will extend ImmutableTurtle to remove duplicated code
* Printing stack traces will be changed to actual error handling 
* Making resource bundle not static in Visualizer to remove issue of setting a static property in a non-static method
* Magic numbers and strings will be made constants at the top of files
* Command factory will now use LanguageConverter to get Command names so that there is no duplicated code 
* Including a ComponentFactory class that creates different objects for each tab. In a properties file, each component will map to a specific method that will return a specific command. 
* Instead of all the actions for each component having their own handle method in the Actions class, actions that are commands will pass in the corresponding String to handleCommand and then it will be passed to the parser. This will include actions such as change pen color.
* Refactor the setup of the tabs so that it uses reflection with each tab and a properties file contains all the components that it needs
* Right now there is a large switch statement in Controller in the EventListener that checks for changes in the GUI. Many of the cases can be eliminated in the future when we make Commands for them. They will all be processed through the Run button.
* The complex method will become less complex when the above mentioned refactoring of the switch statement is completed.