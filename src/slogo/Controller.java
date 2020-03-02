package slogo;

import javafx.scene.paint.Color;
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
  private Actions myActions;
  private History myHistory;
  private static final String DEFAULT_LANGUAGE = "English";
  private String language = DEFAULT_LANGUAGE;

  private List<ImmutableTurtle> turtleList;

  public Controller(Stage stage) {
    myActions = new Actions();
    myActions.addChangeListener(this);
    myVisualizer = new Visualizer(stage, language, myActions);
    MethodExplorer myME = new MethodExplorer();
    VariableExplorer myVE = new VariableExplorer();
    backendManager = new ModelExternal(language, myME, myVE, myVisualizer);
    myHistory = new History();
    myVisualizer.bindTabs(this.language, myHistory.getInputs(), myVE.getDisplayVariables(),
        myME.getMethodNames());
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    String value = evt.getNewValue().toString();
    switch (evt.getPropertyName()){
      case "Run":
        handleRun(value);
        break;
      case "Reset":
        handleReset();
        break;
      case "Language":
        this.language = value;
        break;
      case "Pen Color":
        myVisualizer.setPenColor(Color.web(value));
        break;
      case "Background Color":
        myVisualizer.setBackgroundColor(Color.web(value));
        break;
      case "TurtleImage":
        myVisualizer.setTurtleImage(value);
        break;
    }
  }

  //FIXME
  private void handleReset() {
    //myTurtle.setToHome();
    //myTurtle.setHeading(0);
    myVisualizer.resetDisplay();
  }

  private void handleRun(String value) {
    //myParser.setLanguage(language);
    //myTurtle.changeLanguage(language);
    String command = value;
    try {
      turtleList = backendManager.parseCommands(command);;
      myHistory.addInput(command);
      myVisualizer.updateTurtle(turtleList);
    }
    catch(ParsingException e) {
      myVisualizer.displayError(e);
    }
  }

  //TODO: set language
  // check for screen bounds
}
