package slogo;

import java.security.spec.ECField;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import view.Actions;
import view.HistoryView;
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

  private String language;
  private List<ImmutableTurtle> turtleList;

  public Controller(Stage stage, String language) {
    myVisualizer = new Visualizer(stage, language);
    myME = new MethodExplorer();
    myVE = new VariableExplorer();
    this.language = language;
    myParser = new Parser(language, myME);
    myTurtle = new Turtle(myME, myVE, language);
    myHistory = new History();
    myVisualizer.bindTabs(this.language, myHistory.getInputs(), myVE.getDisplayVariables(),
        myME.getMethodNames());
  }

  public Controller(){
    myParser = new Parser(language, myME);
    myTurtle = new Turtle(myME, myVE, language);
  }

  public void handleRun(TextArea input) {
    if (input.getText().length() > 0) {
      language = myVisualizer.getLanguage();
      myParser.setLanguage(language);
      myTurtle.changeLanguage(language);
      String command = input.getText();
      try {
        turtleList = myParser.parseCommands(command, myTurtle);
        myHistory.addInput(command);
        myVisualizer.updateTurtle(turtleList);
      } catch (ParsingException e) {
        myVisualizer.displayError(e);
      }
    }
  }

  public void handleClear(TextArea input) {
    input.clear();
  }

  public void handleReset(TextArea input) {
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    if (evt.getPropertyName().equals("Run")) {

    }
    if (evt.getPropertyName().equals("Reset")) {
      myTurtle.setToHome();
      myTurtle.setHeading(0);
    }
  }

  //TODO: set language
  // check for screen bounds
}
