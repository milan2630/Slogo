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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Terminal {

  private Pane pane;
  private static ResourceBundle resourceBundle;
  private Button runButton;
  private Button clearButton;
  private Button resetButton;
  private TextArea input;
  private String data;
  private static final double WIDTH = 800;
  private static final double HEIGHT = 150;
  private static final double PADDING = 5;
  private static final double BUTTON_PANE_WIDTH = 100;
  public static final String RUN = "Run";
  public static final String RESET = "Reset";


  private List<PropertyChangeListener> listener = new ArrayList<>();

  protected Terminal(String language) {
    resourceBundle = ResourceBundle
        .getBundle("resources/ui/" + language);
    this.pane = new AnchorPane();
    setBackgroundColor(pane, Color.BISQUE);
    pane.setPrefSize(WIDTH, HEIGHT);

    createInput();
    makeButtonPane();
  }

  private void makeButtonPane() {
    BorderPane buttonPane = new BorderPane();
    double width = BUTTON_PANE_WIDTH - PADDING * 2;
    double height = HEIGHT - PADDING * 2;

    buttonPane.setPrefSize(width, height);
    setBackgroundColor(buttonPane, Color.SIENNA);

    AnchorPane.setTopAnchor(buttonPane, PADDING);
    AnchorPane.setLeftAnchor(buttonPane, PADDING);

    createButtons(width, height);
    addButtonsToPane(buttonPane);

    pane.getChildren().add(buttonPane);
  }

  private void createButtons(double width, double height) {
    createRunButton(width, height);
    createClearButton(width, height);
    createResetButton(width, height);
  }

  private void addButtonsToPane(BorderPane buttonPane) {
    buttonPane.setTop(runButton);
    buttonPane.setAlignment(runButton, Pos.CENTER);
    buttonPane.setCenter(clearButton);
    buttonPane.setBottom(resetButton);
    buttonPane.setAlignment(resetButton, Pos.CENTER);
    buttonPane.setMargin(runButton, new Insets(PADDING, PADDING, PADDING, PADDING));
    buttonPane.setMargin(clearButton, new Insets(PADDING, PADDING, PADDING, PADDING));
    buttonPane.setMargin(resetButton, new Insets(PADDING, PADDING, PADDING, PADDING));
  }

  private void createClearButton(double width, double height) {
    clearButton = createButton(width, height, resourceBundle.getString("ClearButton"));
    clearButton.setOnAction(event -> handleClear());
  }

  private void createRunButton(double width, double height) {
    runButton = createButton(width, height, resourceBundle.getString("RunButton"));
    runButton.setOnAction(event -> handleRun());
  }

  private void createResetButton(double width, double height) {
    resetButton = createButton(width, height, resourceBundle.getString("ResetButton"));
    resetButton.setOnAction(event -> handleReset());
  }

  private Button createButton(double width, double height, String text) {
    Button button = new Button();
    button.setText(text);
    button.setPrefWidth(width - PADDING * 2);
    button.setPrefHeight(height / 3 - PADDING * 2);
    return button;
  }

  private void handleRun() {
    notifyListeners(RUN, this.data, this.data = input.getText());
  }

  private void handleClear() {
    input.clear();
  }

  private void handleReset() {
    System.out.println("reset");
    notifyListeners(RESET, this.data, this.data = input.getText());
  }

  private void createInput() {
    input = new TextArea();
    input.setPrefHeight(HEIGHT);
    input.setPromptText(resourceBundle.getString("TerminalPrompt"));
    input.setFocusTraversable(false);
    AnchorPane.setTopAnchor(input, PADDING);
    AnchorPane.setBottomAnchor(input, PADDING);
    AnchorPane.setLeftAnchor(input, BUTTON_PANE_WIDTH);
    AnchorPane.setRightAnchor(input, PADDING);
    pane.getChildren().add(input);
  }

  private void notifyListeners(String property, String oldValue, String newValue) {
    //TODO make single prop listener
    for (PropertyChangeListener name : listener) {
      name.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
    }
  }

  /**
   * Implements Observer Design pattern
   *
   * @param newListener
   */
  public void addChangeListener(PropertyChangeListener newListener) {
    listener.add(newListener);
  }

  protected Pane getPane() {
    return this.pane;
  }

  protected double getHeight() {
    return HEIGHT;
  }

  protected void setBackgroundColor(Pane pane, Color color) {
    pane.setBackground(
        new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
  }

  protected void setInputText(String text){
    input.setText(text);
  }
}
