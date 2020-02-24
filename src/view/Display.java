package view;

import java.awt.Point;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
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


  protected Display() {
    this.stackPane = new StackPane();
    setBackgroundColor(Color.AZURE);
    this.trail = new TrailView(3.0, Color.ORANGE);
    this.turtle = new TurtleView(turtleImage, 0, 0, 0);

    stackPane.getChildren().addAll(turtle.getNode(), trail.getNode());
  }

  protected void moveTurtle(Point2D newCoordinate) {
    Point2D oldCoordinate = turtle.getPosition();
    turtle.setPosition(newCoordinate);
    if (turtle.isPenActive()) {
      trail.addLine(oldCoordinate, newCoordinate);
    }
  }

  protected void hideTurtle(){
    turtle.setTurtleVisible(false);
  }

  protected void showTurtle(){
    turtle.setTurtleVisible(true);
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
