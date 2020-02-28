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

public class Terminal {

  private BorderPane pane;
  private static ResourceBundle actionResources;
  private static ResourceBundle uiResources;

  private Button runButton;
  private Button clearButton;
  private Button resetButton;
  private VBox buttonPane;
  private TextArea input;
  private Label errors;
  // application state
  private Controller controller;
  private static final double HEIGHT = 150;
  private static final double BUTTON_PANE_WIDTH = 100;
  private static final double ERROR_PANE_HEIGHT = 25;
  private static final String DEFAULT_RESOURCE_ACTIONS = "resources/Actions/default";

  protected Terminal(String language) {
    controller = new Controller();
    actionResources = ResourceBundle
        .getBundle("resources/Actions/"+language);
    uiResources = ResourceBundle.getBundle("resources/ui/" + language);
    this.pane = new BorderPane();
    pane.setPrefHeight(HEIGHT);
    pane.getStyleClass().add("terminal");
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
      buttonPane.getChildren().add(new InputButton(actionResources.getString(key), key, controller, input));
    }
    pane.setLeft(buttonPane);
  }

  private void createInput() {
    input = new TextArea();
    input.getStyleClass().add("input-box");
    input.setPadding((new Insets(0, 5, 5, 5)));
    input.setPrefHeight(HEIGHT - ERROR_PANE_HEIGHT);
    input.setPromptText(uiResources.getString("TerminalPrompt"));
    input.setFocusTraversable(false);
    pane.setCenter(input);
  }

  private void createErrorPane() {
    BorderPane errorPane = new BorderPane();
    errorPane.getStyleClass().add("error-box");
    errors = new Label();
    errors.getStyleClass().add("error-label");
    errorPane.setLeft(errors);
    pane.setTop(errorPane);
  }

  protected Pane getPane() {
    return this.pane;
  }

  protected void setInputText(String text) {
    input.setText(text);
  }

  protected void setErrorText(String text) {
    errors.setText(text);
  }

}
