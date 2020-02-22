package view;

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
  private static final double WIDTH = 800;
  private static final double HEIGHT = 150;
  private static final double PADDING = 5;
  private static final double BUTTON_PANE_WIDTH = 100;

  public Terminal() {
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

    Button runButton = createButton(width, height, "Run");
    AnchorPane.setTopAnchor(runButton, PADDING);

    Button clearButton = createButton(width, height, "Clear");
    AnchorPane.setBottomAnchor(clearButton, PADDING);

    buttonPane.getChildren().addAll(runButton, clearButton);
    pane.getChildren().add(buttonPane);
  }

  private Button createButton(double width, double height, String text) {
    Button runButton = new Button();
    runButton.setText(text);
    runButton.setPrefWidth(width - PADDING * 2);
    runButton.setPrefHeight(height / 2 - PADDING * 2);
    AnchorPane.setLeftAnchor(runButton, PADDING);
    return runButton;
  }

  private void createInput() {
    TextArea input = new TextArea();
    input.setPrefHeight(HEIGHT);
    AnchorPane.setTopAnchor(input, PADDING);
    AnchorPane.setBottomAnchor(input, PADDING);
    AnchorPane.setLeftAnchor(input, BUTTON_PANE_WIDTH);
    AnchorPane.setRightAnchor(input, PADDING);
    pane.getChildren().add(input);
  }

  public Pane getPane() {
    return this.pane;
  }
}
