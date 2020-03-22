package slogo.view.terminal;

import java.util.ResourceBundle;
import javafx.scene.layout.BorderPane;
import slogo.Model.Parsing.LanguageHandler;
import slogo.Controller.Actions;

/**
 * @author Jaidha Rosenblatt This class represents the terminal aspect of the gui. It controls the
 * error bar, text input box, and buttons for running, resetting, and clearing our editor. It is
 * dependent on ErrorBar, TerminalButtons, and TerminalInput The terminal can be contructed and
 * added directly added to a scene since it is a node.
 */

public class Terminal extends BorderPane {

  private static final String RESOURCES_UI = "resources/UI/";
  private static ResourceBundle uiResources;
  private TerminalButtons buttons;
  private TerminalInput input;
  private ErrorBar errorBar;
  private static final double HEIGHT = 150;

  /**
   * Constructor for a terminal
   *
   * @param language the language used in the gui
   * @param actions  an instance of a Actions class that holds the methods that will be executed
   *                 with buttons
   */
  public Terminal(LanguageHandler language, Actions actions) {
    uiResources = ResourceBundle.getBundle(RESOURCES_UI + language.getLanguage());
    setPrefHeight(HEIGHT);

    errorBar = new ErrorBar();
    input = new TerminalInput(uiResources.getString("TerminalPrompt"));
    buttons = new TerminalButtons(language, actions, input);

    setTop(errorBar);
    setCenter(input);
    setLeft(buttons);
  }

  /**
   * Sets the terminal input box to a given string
   *
   * @param text the text to display
   */
  public void setInputText(String text) {
    input.setText(text);
  }

  /**
   * Sets the terminal error bar to a given error message string
   *
   * @param text an error message to display
   */
  public void setErrorText(String text) {
    errorBar.setText(text);
  }

}
