package slogo.view;

import java.util.*;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import slogo.Controller.Actions;
import slogo.FrontEndExternal;
import slogo.Model.Parsing.LanguageHandler;
import slogo.Model.TurtleModel.ImmutableTurtle;
import slogo.Model.ErrorHandling.ParsingException;
import slogo.view.tabdisplay.TabPaneView;
import slogo.view.terminal.Terminal;
import slogo.view.turtledisplay.TurtleManager;

public class Visualizer extends BorderPane implements FrontEndExternal {

  private static final String DEFAULT_LANGUAGE = "English";
  private static ResourceBundle resourceBundle;
  private String language;
  private TurtleManager turtleManager;
  private TabPaneView tabPaneView;
  private Terminal terminal;
  private LanguageHandler languageHandler;
  private static final double SCENE_WIDTH = 800;
  private static final double SCENE_HEIGHT = 600;

  public Visualizer(Stage stage, LanguageHandler language, Actions actions) {
    languageHandler = language;
    setBundle();
    stage.setTitle(resourceBundle.getString("Title"));

    terminal = new Terminal(languageHandler, actions);

    turtleManager = new TurtleManager();

    tabPaneView = new TabPaneView(languageHandler, actions);
    addPanesToRoot();

    Scene scene = new Scene(this, SCENE_WIDTH, SCENE_HEIGHT);
    scene.getStylesheets().add("resources/Styles/default.css");
    stage.setScene(scene);
    stage.show();
  }

  private void addPanesToRoot() {
    Pane displayNode = turtleManager;
    displayNode.getStyleClass().add("display");
    TabPane tabNode = tabPaneView.getTabPane();

    setCenter(displayNode);
    BorderPane.setAlignment(tabNode, Pos.TOP_LEFT);
    setLeft(tabNode);
    setBottom(terminal);
  }

  private void setBundle() {
    try {
      resourceBundle = ResourceBundle
          .getBundle("resources/UI/" + language);
    } catch (MissingResourceException e) {
      this.language = DEFAULT_LANGUAGE;
      resourceBundle = ResourceBundle
          .getBundle("resources/UI/" + language);
    }
  }
  
  public void setInputText(String text) {
    terminal.setInputText(text);
  }

  @Override
  public void updateTurtle(Map<Double, List<ImmutableTurtle>> turtleList) throws ParsingException {
    turtleManager.setPalette(tabPaneView.getImmutablePaletteList());
    turtleManager.updateTurtles(turtleList);
    tabPaneView.updateTurtleTab(turtleList);
  }
  @Override
  public void displayError(Exception error) {
    terminal.setErrorText(error.getMessage());
  }

  @Override
  public void bindTabs(LanguageHandler language, ObservableList history, ObservableList variables,
                       ObservableMap methods, ObservableList palette) {
    tabPaneView.createHistoryTab(language, history);
    tabPaneView.createMethodTab(language, methods);
    tabPaneView.createVariableTab(language, variables);
    tabPaneView.createPaletteTab(language, palette);
  }

  @Override
  public void setHistoryLanguage(String newLanguage) {
    tabPaneView.setHistoryLanguage(newLanguage);
  }

  @Override
  //FIXME
  public void setBackgroundColor(double color) {
    int index = (int) color;
    turtleManager.setBackgroundColor(tabPaneView.getColor(index));
  }

  public void resetTrail(double index){
    turtleManager.resetTrail(index);
  }

  public void resetErrorBar(){
    terminal.setErrorText("");
  }

}
