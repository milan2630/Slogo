package view.TurtleDisplay;

import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import slogo.ImmutableTurtle;

public class Display {

  private Image turtleImage = getImageByName("Turtles/turtle.png");
  private Pane pane;
  private TrailView trail;
  private TurtleView turtle;

  public Display() {
    this.pane = new Pane();

    setBackgroundColor(Color.web("868686"));
    resetPane();
  }

  public void resetPane() {
    pane.getChildren().clear();
    this.turtle = new TurtleView(turtleImage, 0, 0, 0);
    this.trail = new TrailView(5.0, Color.WHITE);

    bindOriginToCenter();

    pane.getChildren().addAll(trail.getPane(), turtle.getPane());
  }

  private void bindOriginToCenter() {
    turtle.getPane().translateXProperty().bind(pane.widthProperty().divide(2));
    turtle.getPane().translateYProperty().bind(pane.heightProperty().divide(2));

    trail.getPane().translateXProperty().bind(pane.widthProperty().divide(2));
    trail.getPane().translateYProperty().bind(pane.heightProperty().divide(2));
  }

  public void moveTurtle(Point2D newCoordinate) {
    Point2D oldCoordinate = turtle.getPosition();
    turtle.setPosition(newCoordinate);
    if (turtle.isPenActive()==1) {
      trail.addLine(oldCoordinate, newCoordinate);
    }
  }

  public ImmutableTurtle getTurtleState() {
    return new ImmutableTurtle(turtle.getPosition().getX(),
        turtle.getPosition().getY(), turtle.getHeading(), turtle.isPenActive(),
        turtle.isTurtleVisible());
  }

  public void setTurtleHeading(double newHeading) {
    turtle.setHeading(newHeading);
  }

  public void setTurtleVisibility(int state) {
    turtle.setTurtleVisible(state);
  }

  public void setPenState(int state) {
    turtle.setPenActive(state);
  }

  public Pane getPane() {
    return this.pane;
  }

  public void setBackgroundColor(Color color) {
    pane.setBackground(
        new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
  }

  public void setTurtleImage(String filename){
    Image image = getImageByName(filename);
    turtleImage = image;
    turtle.setGraphicImage(image);
  }
  
  public void setPenColor(Color currentColor) {
    trail.setCurrentColor(currentColor);
  }

  private Image getImageByName(String name) {

    return new Image(this.getClass().getClassLoader().getResourceAsStream(name));
  }

}
