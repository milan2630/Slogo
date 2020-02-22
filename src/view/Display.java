package view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Display {

  private Image turtleImage = getImageByName("turtle.png");
  private StackPane stackPane;
  private static final double WIDTH = 600;
  private static final double HEIGHT = 400;

  public Display() {
    this.stackPane = new StackPane();
    stackPane.getChildren().add(addTurtle());
    stackPane.setBackground(
        new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
    stackPane.setPrefSize(WIDTH, HEIGHT);
  }

  public Pane getPane() {
    return this.stackPane;
  }

  private Node addTurtle() {
    return new TurtleView(turtleImage, HEIGHT / 2, WIDTH / 2, 50).getNode();
  }

  private Image getImageByName(String name) {
    return new Image(this.getClass().getClassLoader().getResourceAsStream(name));
  }
}
