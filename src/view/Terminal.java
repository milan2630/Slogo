package view;

import java.util.Collections;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import slogo.Controller;

public class Terminal extends BorderPane{

  private static ResourceBundle actionResources;
  private static ResourceBundle uiResources;
  private Actions actions;
  private VBox buttonPane;
  private TextArea input;
  private Label errors;
  private static final double HEIGHT = 150;
  private static final double BUTTON_PANE_WIDTH = 100;
  private static final double ERROR_PANE_HEIGHT = 25;

  protected Terminal(String language, Actions actions) {
    this.actions = actions;
    actionResources = ResourceBundle
        .getBundle("resources/Actions/"+language);
    uiResources = ResourceBundle.getBundle("resources/ui/" + language);
    setPrefHeight(HEIGHT);
    getStyleClass().add("terminal");
    createInput();
    createButtonPane();
    createErrorPane();
  }

  private void createButtonPane() {
    buttonPane = new VBox();
    buttonPane.setPadding((new Insets(10, 5, 10, 5)));
    buttonPane.getStyleClass().add("button-box");

    double width = BUTTON_PANE_WIDTH;
    double height = HEIGHT - ERROR_PANE_HEIGHT;

    buttonPane.setPrefSize(width, height);

    for (String key : Collections.list(actionResources.getKeys())) {
      buttonPane.getChildren().add(new InputButton(actionResources.getString(key), key, actions, input));
    }
    setLeft(buttonPane);
  }

  private void createInput() {
    input = new TextArea();
    input.getStyleClass().add("input-box");
    input.setPadding((new Insets(0, 5, 5, 5)));
    input.setPrefHeight(HEIGHT - ERROR_PANE_HEIGHT);
    input.setPromptText(uiResources.getString("TerminalPrompt"));
    input.setFocusTraversable(false);
    setCenter(input);
  }

  private void createErrorPane() {
    BorderPane errorPane = new BorderPane();
    errorPane.getStyleClass().add("error-box");
    errors = new Label();
    errors.getStyleClass().add("error-label");
    errorPane.setLeft(errors);
    setTop(errorPane);
  }

  protected void setInputText(String text) {
    input.setText(text);
  }

  protected void setErrorText(String text) {
    errors.setText(text);
  }

}
