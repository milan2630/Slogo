package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Terminal {

  private BorderPane pane;
  private static ResourceBundle resourceBundle;
  private Button runButton;
  private Button clearButton;
  private Button resetButton;
  private BorderPane buttonPane;
  private TextArea input;
  private TextField errors;
  private String data;
  private static final double HEIGHT = 150;
  private static final double BUTTON_PANE_WIDTH = 100;
  private static final double ERROR_PANE_HEIGHT = 30;
  private static final String RUN = "Run";
  private static final String RESET = "Reset";

  private List<PropertyChangeListener> listener = new ArrayList<>();

  protected Terminal(String language) {
    resourceBundle = ResourceBundle
        .getBundle("resources/ui/" + language);
    this.pane = new BorderPane();
    setBackgroundColor(pane, Color.web("E5E5E5"));
    pane.setPrefHeight(HEIGHT);

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
    button.getStyleClass().add("button");
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
    double width = BUTTON_PANE_WIDTH;
    double height = HEIGHT - ERROR_PANE_HEIGHT;

    buttonPane.setPrefSize(width, height);
    setBackgroundColor(buttonPane, Color.web("D2D2D2"));

    createRunButton();
    createClearButton();
    createResetButton();

    pane.setLeft(buttonPane);
  }

  private void createInput() {
    input = new TextArea();
    input.setPrefHeight(HEIGHT - ERROR_PANE_HEIGHT);
    input.setPromptText(resourceBundle.getString("TerminalPrompt"));
    input.setFocusTraversable(false);
    pane.setCenter(input);
  }

  private void createErrorPane() {
    errors = new TextField();
    errors.setEditable(false);
    errors.setBackground(
        new Background(new BackgroundFill(Color.web("999999"), CornerRadii.EMPTY, Insets.EMPTY)));
    errors.setPrefHeight(ERROR_PANE_HEIGHT);
    pane.setTop(errors);
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

  protected void setBackgroundColor(Pane pane, Color color) {
    pane.setBackground(
        new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
  }

  protected void setInputText(String text) {
    input.setText(text);
  }

  protected void setErrorText(String text) {
    errors.setText(text);
  }

}
