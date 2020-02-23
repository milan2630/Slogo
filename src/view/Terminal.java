package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Terminal {

  private Pane pane;
  private static ResourceBundle resourceBundle;
  private Button runButton;
  private Button clearButton;
  private TextArea input;
  private String data;
  private static final double WIDTH = 800;
  private static final double HEIGHT = 150;
  private static final double PADDING = 5;
  private static final double BUTTON_PANE_WIDTH = 100;
  public static final String RUN = "Run";

  private List<PropertyChangeListener> listener = new ArrayList<>();

  protected Terminal(String language) {
    resourceBundle = ResourceBundle
        .getBundle("resources/ui/" + language);
    this.pane = new AnchorPane();
    pane.setBackground(
        new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    pane.setPrefSize(WIDTH, HEIGHT);

    createInput();
    makeButtonPane();
  }

  private void makeButtonPane() {
    AnchorPane buttonPane = new AnchorPane();
    double width = BUTTON_PANE_WIDTH - PADDING * 2;
    double height = HEIGHT - PADDING * 2;

    buttonPane.setPrefSize(width, height);
    buttonPane.setBackground(
        new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
    AnchorPane.setTopAnchor(buttonPane, PADDING);
    AnchorPane.setLeftAnchor(buttonPane, PADDING);

    createRunButton(width, height);
    createClearButton(width, height);

    buttonPane.getChildren().addAll(runButton, clearButton);
    pane.getChildren().add(buttonPane);

  }

  private void createClearButton(double width, double height) {
    clearButton = createButton(width, height, resourceBundle.getString("ClearButton"));
    AnchorPane.setBottomAnchor(clearButton, PADDING);
    clearButton.setOnAction(event -> handleClear());
  }

  private void createRunButton(double width, double height) {
    runButton = createButton(width, height, resourceBundle.getString("RunButton"));
    AnchorPane.setTopAnchor(runButton, PADDING);
    runButton.setOnAction(event -> handleRun());

  }

  private Button createButton(double width, double height, String text) {
    Button button = new Button();
    button.setText(text);
    button.setPrefWidth(width - PADDING * 2);
    button.setPrefHeight(height / 2 - PADDING * 2);
    AnchorPane.setLeftAnchor(button, PADDING);
    return button;
  }

  private void handleRun() {
    notifyListeners(RUN, this.data, this.data = input.getText());
  }

  private void handleClear() {
    input.clear();
  }

  private void createInput() {
    input = new TextArea();
    input.setPrefHeight(HEIGHT);
    AnchorPane.setTopAnchor(input, PADDING);
    AnchorPane.setBottomAnchor(input, PADDING);
    AnchorPane.setLeftAnchor(input, BUTTON_PANE_WIDTH);
    AnchorPane.setRightAnchor(input, PADDING);
    pane.getChildren().add(input);
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
  public void addChangeListener(PropertyChangeListener newListener) {
    listener.add(newListener);
  }

  protected Pane getPane() {
    return this.pane;
  }

  protected double getHeight() {
    return HEIGHT;
  }
}
