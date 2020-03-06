package slogo.Controller;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import slogo.Model.BackEndExternal;
import slogo.Model.Explorers.MethodExplorer;
import slogo.Model.Explorers.PaletteExplorer;
import slogo.Model.Explorers.Variables.VariableExplorer;
import slogo.Model.Parsing.CommandManager;
import slogo.Model.Parsing.LanguageConverter;
import slogo.Model.TurtleModel.ImmutableTurtle;
import slogo.view.Actions;
import slogo.view.Visualizer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class Controller implements PropertyChangeListener {

  private Visualizer myVisualizer;
  private BackEndExternal backendManager;
  private Actions myActions;
  private History myHistory;
  private static final String DEFAULT_LANGUAGE = "English";
  private String language = DEFAULT_LANGUAGE;
  private LanguageConverter languageConverter;

  private List<ImmutableTurtle> turtleList;

  public Controller(Stage stage) {
    myActions = new Actions();
    myActions.addChangeListener(this);
    languageConverter = new LanguageConverter(language);
    myVisualizer = new Visualizer(stage, languageConverter, myActions);
    PaletteExplorer myPE = new PaletteExplorer(languageConverter, myActions);
    MethodExplorer myME = new MethodExplorer(languageConverter);
    VariableExplorer myVE = new VariableExplorer();
    backendManager = new CommandManager(myVisualizer, myME, myVE, myPE, languageConverter);
    myHistory = new History();
    myVisualizer.bindTabs(languageConverter, myHistory.getInputs(), myVE.getDisplayVariables(),
        myME.getMethodNames(), myPE.getList());
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    String value = evt.getNewValue().toString();
    switch (evt.getPropertyName()){
      case "Run":
        Color c = Color.RED;
        System.out.println(c.toString());
        handleRun(value);
        break;
      case "Reset":
        handleReset();
        break;
      case "Language":
        backendManager.setLanguage(value);
        myVisualizer.setHistoryLanguage(value);
        languageConverter.updateLanguage(value);
        this.language = value;
        break;
      case "Pen Color":
        //myVisualizer.setPenColor(Color.web(value));
        myVisualizer.setPenColor(Integer.parseInt(value));
        break;
      case "Background Color":
        //myVisualizer.setBackgroundColor(Color.web(value));
        myVisualizer.setBackgroundColor(Integer.parseInt(value));
        break;
      case "Turtle Image":
        myVisualizer.setTurtleImage(value);
        break;
      case "Thickness":
        myVisualizer.setPenThickness(Double.parseDouble(value));
        break;
      case "Pen Status":
        //FIXME update pen status in backend
        myVisualizer.setPenStatus(Integer.parseInt(value));
        break;
      case "HistoryVariable":
        myVisualizer.setInputText(value);
        break;
      case "Change Turtle State":
        myVisualizer.setInputText(evt.getPropagationId().toString()+" "+evt.getNewValue().toString());
        break;
      case "Method Display":
        myVisualizer.setInputText(value);
        break;
      case "Background Color Index":
        //TODO update backend index of background color

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
      turtleList = backendManager.parseTurtleStatesFromCommands(command);;
      myHistory.addInput(command);
      myVisualizer.updateTurtle(turtleList);
    }
    catch(Exception e) {
      myVisualizer.displayError(e);
    }
  }

  //TODO: set language
  // check for screen bounds
}
