package view;

import java.awt.Point;
import javafx.geometry.Insets;
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
  private TrailView trail;
  private TurtleView turtle;

  private static final double WIDTH = 600;
  private static final double HEIGHT = 400;

  protected Display() {
    this.stackPane = new StackPane();
    stackPane.setPrefSize(WIDTH, HEIGHT);
    setBackgroundColor(Color.AZURE);
    this.trail = new TrailView(3.0, Color.ORANGE);
    this.turtle = new TurtleView(turtleImage, HEIGHT / 2, WIDTH / 2, 50);

    stackPane.getChildren().addAll(turtle.getNode(), trail.getNode());

    trail.addLine(new Point(30, 20), new Point(600, 30));
    trail.setCurrentColor(Color.BLACK);
    trail.addLine(new Point(500, 20), new Point(0, 300));
  }

  protected Pane getPane() {
    return this.stackPane;
  }

  protected void setBackgroundColor(Color color) {
    stackPane.setBackground(
        new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
  }

  private Image getImageByName(String name) {
    return new Image(this.getClass().getClassLoader().getResourceAsStream(name));
  }
}
