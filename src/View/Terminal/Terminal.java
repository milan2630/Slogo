package View.Terminal;

import java.util.ResourceBundle;
import javafx.scene.layout.BorderPane;
import View.Actions;

public class Terminal extends BorderPane {

  private static final String RESOURCES_TERMINAL = "resources/Layouts/Terminal/";
  private static final String RESOURCES_UI = "resources/UI/";
  private static ResourceBundle actionResources;
  private static ResourceBundle uiResources;
  private TerminalButtons buttons;
  private TerminalInput input;
  private ErrorBar errorBar;
  private static final double HEIGHT = 150;

  public Terminal(String language, Actions actions) {
    actionResources = ResourceBundle.getBundle(RESOURCES_TERMINAL + language);
    uiResources = ResourceBundle.getBundle(RESOURCES_UI + language);
    setPrefHeight(HEIGHT);

    errorBar = new ErrorBar();
    input = new TerminalInput(uiResources.getString("TerminalPrompt"));
    buttons = new TerminalButtons(actionResources, actions, input);

    setTop(errorBar);
    setCenter(input);
    setLeft(buttons);
  }

  public void setInputText(String text) {
    input.setText(text);
  }

  public void setErrorText(String text) {
    errorBar.setText(text);
  }

}
