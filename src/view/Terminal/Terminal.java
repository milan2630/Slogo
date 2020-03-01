package view.Terminal;

import java.util.ResourceBundle;
import javafx.scene.layout.BorderPane;
import view.Actions;

public class Terminal extends BorderPane {

  private static ResourceBundle actionResources;
  private static ResourceBundle uiResources;
  private TerminalButtons buttons;
  private TerminalInput input;
  private ErrorBar errorBar;
  private static final double HEIGHT = 150;

  public Terminal(String language, Actions actions) {
    actionResources = ResourceBundle
        .getBundle("resources/Actions/" + language);
    uiResources = ResourceBundle.getBundle("resources/ui/" + language);
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
