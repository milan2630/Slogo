package view;

import javafx.geometry.Insets;
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

  private AnchorPane pane;
  private static final double WIDTH = 800;
  private static final double HEIGHT = 150;

  public Terminal() {
    this.pane = new AnchorPane();
    pane.setBackground(
        new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    pane.setPrefSize(WIDTH, HEIGHT);

    createInput();

    Button runButton  = new Button();
    runButton.setText("Run");
    AnchorPane.setTopAnchor(runButton,10.0);
    AnchorPane.setLeftAnchor(runButton,10.0);
    pane.getChildren().add(runButton);


  }

  private void createInput() {
    TextArea input = new TextArea();
    input.setPrefHeight(HEIGHT);
    AnchorPane.setTopAnchor(input,10.0);
    AnchorPane.setBottomAnchor(input,10.0);
    AnchorPane.setLeftAnchor(input,100.0);
    AnchorPane.setRightAnchor(input,10.0);
    pane.getChildren().add(input);
  }

  public Pane getPane() {
    return this.pane;
  }
}
