package view;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Terminal {

  private StackPane stackPane;
  private static final double WIDTH = 800;
  private static final double HEIGHT = 150;

  public Terminal() {
    this.stackPane = new StackPane();
    stackPane.setBackground(
        new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    stackPane.setPrefSize(WIDTH, HEIGHT);
  }

  public StackPane getStackPane() {
    return this.stackPane;
  }
}
