package view;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class Terminal {

  private BorderPane pane;
  private static ResourceBundle resourceBundle;
  private Button runButton;
  private Button clearButton;
  private Button resetButton;
  private BorderPane buttonPane;
  private TextArea input;
  private Label errors;
  private String data;
  private static final double HEIGHT = 150;
  private static final double BUTTON_PANE_WIDTH = 100;
  private static final double ERROR_PANE_HEIGHT = 25;
  private static final String RUN = "Run";
  private static final String RESET = "Reset";

  private List<PropertyChangeListener> listener = new ArrayList<>();

  protected Terminal(String language) {
    resourceBundle = ResourceBundle
        .getBundle("resources/ui/" + language);
    this.pane = new BorderPane();
    pane.setPrefHeight(HEIGHT);
    pane.getStyleClass().add("terminal");
    createInput();
    createButtonPane();
    createErrorPane();
  }

  private void createClearButton() {
    clearButton = createButton(resourceBundle.getString("ClearButton"));
    clearButton.setOnAction(event -> handleClear());
    buttonPane.setCenter(clearButton);
  }

  private void createRunButton() {
    runButton = createButton(resourceBundle.getString("RunButton"));
    runButton.setOnAction(event -> handleRun());
    buttonPane.setTop(runButton);
    buttonPane.setAlignment(runButton, Pos.CENTER);
  }

  private void createResetButton() {
    resetButton = createButton(resourceBundle.getString("ResetButton"));
    resetButton.setOnAction(event -> handleReset());
    buttonPane.setBottom(resetButton);
    buttonPane.setAlignment(resetButton, Pos.CENTER);
  }

  private Button createButton(String text) {
    Button button = new Button();
    button.setText(text);
    button.setPrefWidth(BUTTON_PANE_WIDTH);
    button.getStyleClass().add("terminal-button");
    return button;
  }

  private void handleRun() {
    if (input.getText().length()>0){
      notifyListeners(RUN, this.data, this.data = input.getText());
    }
  }

  private void handleClear() {
    input.clear();
  }

  private void handleReset() {
    notifyListeners(RESET, this.data, this.data = input.getText());
    errors.setText("");
  }

  private void createButtonPane() {
    buttonPane = new BorderPane();
    buttonPane.setPadding((new Insets(10, 5, 10, 5)));
    buttonPane.getStyleClass().add("button-box");


    double width = BUTTON_PANE_WIDTH;
    double height = HEIGHT - ERROR_PANE_HEIGHT;

    buttonPane.setPrefSize(width, height);

    createRunButton();
    createClearButton();
    createResetButton();

    pane.setLeft(buttonPane);
  }

  private void createInput() {
    input = new TextArea();
    input.getStyleClass().add("input-box");
    input.setPadding((new Insets(0, 5, 5, 5)));
    input.setPrefHeight(HEIGHT - ERROR_PANE_HEIGHT);
    input.setPromptText(resourceBundle.getString("TerminalPrompt"));
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

  private void notifyListeners(String property, String oldValue, String newValue) {
    for (PropertyChangeListener name : listener) {
      name.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
    }
  }

  /**
   * Implements Observer Design pattern
   *
   * @param newListener
   */
  protected void addChangeListener(PropertyChangeListener newListener) {
    listener.add(newListener);
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
