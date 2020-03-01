package slogo;

import javafx.stage.Stage;
import view.Actions;
import view.Visualizer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class Controller implements PropertyChangeListener {

  private Visualizer myVisualizer;
  private Parser myParser;
  private MethodExplorer myME;
  private VariableExplorer myVE;
  private Actions myActions;
  private Turtle myTurtle;
  private History myHistory;
  private static final String DEFAULT_LANGUAGE = "English";
  private String language = DEFAULT_LANGUAGE;
  private List<ImmutableTurtle> turtleList;

  public Controller(Stage stage, String language) {
    myActions = new Actions();
    myActions.addChangeListener(this);
    myVisualizer = new Visualizer(stage, language, myActions);
    myME = new MethodExplorer();
    myVE = new VariableExplorer();
    this.language = language;
    myParser = new Parser(language, myME);
    myTurtle = new Turtle(myME, myVE, language);
    myHistory = new History();
    myVisualizer.bindTabs(this.language, myHistory.getInputs(), myVE.getDisplayVariables(),
        myME.getMethodNames());
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    System.out.println("=================== \n " + evt.getPropertyName());
    if (evt.getPropertyName().equals("Run")){
      myParser.setLanguage(language);
      myTurtle.changeLanguage(language);
      String command = evt.getNewValue().toString();
      try {
        turtleList = myParser.parseCommands(command, myTurtle);
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
