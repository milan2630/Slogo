package slogo.view.turtledisplay;

import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import slogo.Model.TurtleModel.ImmutableTurtle;

public class Display extends Pane {

  private TrailView trail;
  private TurtleView turtle;
  private static final String TURTLE_PATH = "turtles/";
  private static final String DEFAULT_TURTLE_IMAGE = "turtle.png";
  private Image turtleImage = getImageByName(TURTLE_PATH + DEFAULT_TURTLE_IMAGE);


  private static final Color DEFAULT_PEN_COLOR = Color.NAVY;
  private static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;


  public Display() {
    resetPane();
  }

  public void resetPane() {
    getChildren().clear();
    this.turtle = new TurtleView(turtleImage, 0, 0, 0);
    this.trail = new TrailView(5.0, DEFAULT_PEN_COLOR);
    setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
    bindOriginToCenter();

    getChildren().addAll(trail, turtle);
  }

  private void bindOriginToCenter() {
    turtle.translateXProperty().bind(widthProperty().divide(2));
    turtle.translateYProperty().bind(heightProperty().divide(2));

    trail.translateXProperty().bind(widthProperty().divide(2));
    trail.translateYProperty().bind(heightProperty().divide(2));
  }

  public void moveTurtle(Point2D newCoordinate) {
    Point2D oldCoordinate = turtle.getPosition();
    turtle.setPosition(newCoordinate);
    if (turtle.isPenActive() == 1) {
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

  public void setBackgroundColor(Color color) {
    setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
  }

  public void setPenThickness(Double thickness){
    trail.setCurrentThickness(thickness);
  }

  public void setTurtleImage(String filename) {
    Image image = getImageByName(TURTLE_PATH + filename);
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
