package slogo;

import javafx.stage.Stage;
import slogo.Commands.Command;
import view.Actions;
import view.Visualizer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Controller implements PropertyChangeListener {

  private Visualizer myVisualizer;
  private ModelExternal backendManager;
  private MethodExplorer myME;
  private VariableExplorer myVE;
  private Actions myActions;
  private Turtle myTurtle;
  private History myHistory;
  private CommandManager commandManager;

  private String language;
  private List<ImmutableTurtle> turtleList;

  public Controller(Stage stage, String language) {
    myActions = new Actions();
    myActions.addChangeListener(this);
    myVisualizer = new Visualizer(stage, language, myActions);
    myME = new MethodExplorer();
    myVE = new VariableExplorer();
    this.language = language;
    backendManager = new ModelExternal(language, myME, myVE, myVisualizer);
    myTurtle = new Turtle(myME, myVE, language);
    myHistory = new History();
    myVisualizer.bindTabs(this.language, myHistory.getInputs(), myVE.getDisplayVariables(),
        myME.getMethodNames());
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if (evt.getPropertyName().equals("Run")){
      language = myVisualizer.getLanguage();
      //myParser.setLanguage(language);
      //myTurtle.changeLanguage(language);
      String command = evt.getNewValue().toString();
      try {
        turtleList = backendManager.parseCommands(command);
        myHistory.addInput(command);
        myVisualizer.updateTurtle(turtleList);
      }
      catch(ParsingException e) {
        myVisualizer.displayError(e);
      }
    }
    if (evt.getPropertyName().equals("Reset")){
      myTurtle.setToHome();
      myTurtle.setHeading(0);
      myVisualizer.resetDisplay();
    }
  }

  //TODO: set language
  // check for screen bounds
}
