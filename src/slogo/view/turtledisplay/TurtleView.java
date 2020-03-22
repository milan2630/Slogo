package slogo.view.turtledisplay;

import java.util.ResourceBundle;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Controls a turtle and its trail for
 */
public class TurtleView extends Pane {

  private Trail trail;
  private Turtle turtle;
  private double index;
  private static final String TURTLE_PATH = "turtles/";
  private static final String DEFAULT_RESOURCE_PATH = "resources/UI/Default";
  private static final String LAYOUT_RESOURCE_PATH = "resources/UI/Layouts";

  private ResourceBundle defaults = ResourceBundle.getBundle(DEFAULT_RESOURCE_PATH);
  private ResourceBundle layouts = ResourceBundle.getBundle(LAYOUT_RESOURCE_PATH);
  private Image turtleImage;
  private String[] filenames;

  protected TurtleView(double index) {
    this.index = index;
    turtleImage = getImageByName(TURTLE_PATH + defaults.getString("TurtleImage"));
    filenames = layouts.getString("TurtleImages").split(",");
    reset();
  }

  protected void reset() {
    getChildren().clear();
    this.turtle = new Turtle(turtleImage, 0, 0, 0);
    this.trail = new Trail(Double.parseDouble(defaults.getString("PenThickness")),
        Color.web(defaults.getString("PenColor")));
    getChildren().addAll(trail, turtle);
  }

  protected void moveTurtle(Point2D newCoordinate) {
    Point2D oldCoordinate = turtle.getPosition();
    turtle.setPosition(newCoordinate);
    if (turtle.isPenActive() == 1) {
      trail.addLine(oldCoordinate, newCoordinate);
    }
  }

  protected void setTurtleHeading(double newHeading) {
    turtle.setHeading(newHeading);
  }

  protected void setTurtleVisibility(double state) {
    turtle.setTurtleVisible(state);
  }

  protected void setPenState(double state) {
    turtle.setPenActive(state);
  }

  protected void setShape(double index) {
    setTurtleImage(filenames[(int) index]);
  }

  protected void setPenThickness(Double thickness) {
    trail.setCurrentThickness(thickness);
  }

  protected void setTurtleImage(String filename) {
    Image image = getImageByName(TURTLE_PATH + filename);
    turtleImage = image;
    turtle.setGraphicImage(image);
  }

  protected void setPenColor(Color currentColor) {
    trail.setColor(currentColor);
  }

  private Image getImageByName(String name) {

    return new Image(this.getClass().getClassLoader().getResourceAsStream(name));
  }

}
