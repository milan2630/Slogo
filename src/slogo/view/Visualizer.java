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

/**
 * @author jaidharosenblatt, ryanweeratunga - the main controller class for the frontend. This class
 * implements the FrontEndExternal API and holds all of the nodes in our gui. This class is
 * dependent on TurtleManager and its dependencies, Terminal and its dependencies, TabPaneView and
 * its dependencies, and LanguageHandler. It is used in the Controller class to communicate with the
 * model.
 */
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

  /**
   * Constructs a new visualizer and sets the gui to the given stage
   *
   * @param stage    the stage for our application
   * @param language the language for our gui
   * @param actions  an Actions object that holds all of the possible methods
   */
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

  /**
   * Updates the turtle display using a list of all the states of a turtle
   *
   * @param turtleList a list of turtle states from the backend
   */
  @Override
  public void updateTurtle(Map<Double, List<ImmutableTurtle>> turtleList) throws ParsingException {
    turtleManager.setPalette(tabPaneView.getImmutablePaletteList());
    turtleManager.updateTurtles(turtleList);
    tabPaneView.updateTurtleTab(turtleList);
  }

  /**
   * Handle an error and tell the User what issue occurred
   *
   * @param error the error that was thrown in the backend
   */
  @Override
  public void displayError(Exception error) {
    terminal.setErrorText(error.getMessage());
  }

  /**
   * Passes a list of tabs as observable map and lists to be able to bind to their external methods
   * in the controller
   *
   * @param language  the current language
   * @param history   a list of previous commands
   * @param variables a list of current variables
   * @param methods   a map of method name to commands
   * @param palette   a list of the current color palette
   */
  @Override
  public void bindTabs(LanguageHandler language, ObservableList history, ObservableList variables,
      ObservableMap methods, ObservableList palette) {
    tabPaneView.createHistoryTab(language, history);
    tabPaneView.createMethodTab(language, methods);
    tabPaneView.createVariableTab(language, variables);
    tabPaneView.createPaletteTab(language, palette);
  }

  /**
   * Translates the history to a new language
   *
   * @param newLanguage the new language to set history to
   */
  @Override
  public void setHistoryLanguage(String newLanguage) {
    tabPaneView.setHistoryLanguage(newLanguage);
  }

  /**
   * Updates the background color of the turtle display
   *
   * @param color the color to set it to
   */
  @Override
  public void setBackgroundColor(double color) {
    int index = (int) color;
    turtleManager.setBackgroundColor(tabPaneView.getColor(index));
  }

  /**
   * Resets a trail for a given turtle
   *
   * @param index the turtle id to change
   */
  @Override
  public void resetTrail(double index) {
    turtleManager.resetTrail(index);
  }

  /**
   * Resets the error bar
   */
  @Override
  public void resetErrorBar() {
    terminal.setErrorText("");
  }

  /**
   * Sets the terminal input box to given a given String
   *
   * @param text the text to display
   */
  @Override
  public void setInputText(String text) {
    terminal.setInputText(text);
  }


}
