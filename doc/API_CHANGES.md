# API_CHANGES

## Milan Bhat (mb554), Ryan Weeratunga (rkw14), Robert Chen (rec43), Jaidha Rosenblatt (jrr59)

### Backend External

The backend external API originally had methods for getting each of the Turtle elements such as its x and y. These were all removed in favor of changing the `parseStringToCommand(String input)` function from returning a list of Commands to a list of ImmutableTurtles and renaming the method `parseTurtleStatesFromCommands()`. This also removed the need for the `actOnCommand()` method because all actions are performed internall in the backend and then the state of the turtles after each action is returned in the list of ImmutableTurtles returned by `parseTurtleStatesFromCommands()`. 

`public void setLanguage(String lang)` was added to the API to be able to change the language that the backend operates in so that parsing can work correctly. 

### Backend Internal

All the update methods were removed because this is done internally in the command classes. These were replaced with the `actOnCommand(Command command, List<String> params)` method.  `createMethod()` and `createVariable()` were changed to getters for the MethodExplorer and VariableExplorer respectively because these are the way to create methods and variables now. History updating is handled in the controller so there is no need for the `updateHistory()` method in the backend. `getLanguage()` was added so that the entire backend can be aware of the current language. `getInternalStates()` was added to access all the states that were created during parsing.

### Frontend External
Originally, we had `updatePositions(double newX, double newY)`, `updateHeading(double newHeading)`, `updatePenState(int penState)` to be able to directly change the turtle's properties through the controller. We combined these methods into  updateTurtle(List<ImmutableTurtle> turtleList) where each ImmutableTurtle holds all of these properties. We originally had a `getConsoleString()` and `getLanguage()` methods that were removed since we now have added listener in the controller for when the "run" button or the language dropdown is changed. We removed `createButton()` because we decided to add buttons using properties files.

We `added setInputText(String text)` in order to be have the history view be able to update the terminal to whatever is in the history. We added setHistoryLanguage(String newLanguage) so the language in history gets updated when the gui language is switched. We added `bindTabs(String language, ObservableList history, ObservableList variables,
      ObservableMap methods, ObservableList palette)` so that we could bind the various tab views with the backend.
      
### Frontend Internal
We added `setInputText(text)` for our terminal so that we can update the terminal input based on history or methods. We also renamed the `setTrailColor()` to `setPenColor` since we made the turtle have an instance of a pen. We also added methods for `getWidth()` and `getHeight()` of the display so that we could handle out of bounds for the display. Finally, we removed createButton since, again, we are using properties files.