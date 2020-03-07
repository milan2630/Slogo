package slogo.Controller;

import javafx.stage.Stage;
import slogo.Model.BackEndExternal;
import slogo.Model.Explorers.MethodExplorer;
import slogo.Model.Explorers.PaletteExplorer;
import slogo.Model.Explorers.Variables.VariableExplorer;
import slogo.Model.Parsing.CommandManager;
import slogo.Model.Parsing.LanguageHandler;
import slogo.Model.TurtleModel.ImmutableTurtle;
import slogo.view.Visualizer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Map;

public class Controller implements PropertyChangeListener {

  private Visualizer myVisualizer;
  private BackEndExternal backendManager;
  private Actions myActions;
  private History myHistory;
  private static final String DEFAULT_LANGUAGE = "English";
  private String language = DEFAULT_LANGUAGE;
  private LanguageHandler languageHandler;


  public Controller(Stage stage) {
    myActions = new Actions();
    myActions.addChangeListener(this);
    languageHandler = new LanguageHandler(language);
    myVisualizer = new Visualizer(stage, languageHandler, myActions);
    PaletteExplorer myPE = new PaletteExplorer(languageHandler, myActions);
    MethodExplorer myME = new MethodExplorer(languageHandler);
    VariableExplorer myVE = new VariableExplorer();
    backendManager = new CommandManager(myVisualizer, myME, myVE, myPE, languageHandler);
    myHistory = new History();
    myVisualizer.bindTabs(languageHandler, myHistory.getInputs(), myVE.getDisplayVariables(),
        myME.getMethodNames(), myPE.getList());
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    String value = evt.getNewValue().toString();
    switch (evt.getPropertyName()){
      case "Command":
        handleRun(value);
        break;
      case "Reset":
        handleReset();
        break;
      case "Language":
        backendManager.setLanguage(value);
        myVisualizer.setHistoryLanguage(value);
        languageHandler.updateLanguage(value);
        this.language = value;
        break;
      case "Background Color":
        //myVisualizer.setBackgroundColor(Color.web(value));
        myVisualizer.setBackgroundColor(Integer.parseInt(value));
        break;
      case "Input Change":
        myVisualizer.setInputText(value);
        break;
      case "Change Turtle State":
        myVisualizer.setInputText(evt.getPropagationId().toString()+" "+evt.getNewValue().toString());
        break;
      case "Load XML":
        break;
    }
  }

  private void handleReset() {
    try {
      String command = languageHandler.getLanguageCommand("ClearScreen");
      backendManager.parseTurtleStatesFromCommands(command);
    }
    catch(Exception e) {
      myVisualizer.displayError(e);
    }
  }

  private void handleRun(String command) {
    try {
      Map<Double, List<ImmutableTurtle>> turtleList = backendManager.parseTurtleStatesFromCommands(command);
      myHistory.addInput(command);
      myVisualizer.updateTurtle(turtleList);
    }
    catch(Exception e) {
      myVisualizer.displayError(e);
    }
  }

}
