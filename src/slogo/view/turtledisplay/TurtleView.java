package slogo.view.turtledisplay;

import java.util.ResourceBundle;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * @author jaidharosenblatt Controls a turtle and its trail. Holds both a turtle graphic and its
 * trail in a pane so it can be directly added to a scene. Is dependent on Trail and Turtle.
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

  /**
   * Constructs a turtle at a given index
   *
   * @param index the index of the turtle
   */
  protected TurtleView(double index) {
    this.index = index;
    turtleImage = getImageByName(TURTLE_PATH + defaults.getString("TurtleImage"));
    filenames = layouts.getString("TurtleImages").split(",");
    reset();
  }

  /**
   * Resets a turtle to 0,0 position, the default pen color, and a blank trail
   */
  protected void reset() {
    getChildren().clear();
    this.turtle = new Turtle(turtleImage, 0, 0, 0);
    this.trail = new Trail(Double.parseDouble(defaults.getString("PenThickness")),
        Color.web(defaults.getString("PenColor")));
    getChildren().addAll(trail, turtle);
  }

  /**
   * Moves a turtle and draws a trail if its pen is active
   *
   * @param newCoordinate the coordinate to move the turtle into
   */
  protected void moveTurtle(Point2D newCoordinate) {
    Point2D oldCoordinate = turtle.getPosition();
    turtle.setPosition(newCoordinate);
    if (turtle.isPenActive() == 1) {
      trail.addLine(oldCoordinate, newCoordinate);
    }
  }

  /**
   * Updates the turtle's heading
   *
   * @param newHeading the new heading
   */
  protected void setTurtleHeading(double newHeading) {
    turtle.setHeading(newHeading);
  }

  /**
   * Updates the visibility of a turtle
   *
   * @param state the visibility of a turtle (1 visible, 0 invisible)
   */
  protected void setTurtleVisibility(double state) {
    turtle.setTurtleVisible(state);
  }

  /**
   * Updates a turtle's pen state
   *
   * @param state the pen state of a turtle (1 active, 0 inactive)
   */
  protected void setPenState(double state) {
    turtle.setPenActive(state);
  }

  /**
   * Updates the graphic by using the index from the possible turtle images
   *
   * @param index the index of the new image
   */
  protected void setShape(double index) {
    setTurtleImage(filenames[(int) index]);
  }

  /**
   * Updates pen thickness
   *
   * @param thickness the new thickness
   */
  protected void setPenThickness(Double thickness) {
    trail.setCurrentThickness(thickness);
  }

  /**
   * Sets the turtle graphic to a given image
   *
   * @param filename the name of the image in resources
   */
  protected void setTurtleImage(String filename) {
    Image image = getImageByName(TURTLE_PATH + filename);
    turtleImage = image;
    turtle.setGraphicImage(image);
  }

  /**
   * Updates the pen color
   *
   * @param currentColor the new color
   */
  protected void setPenColor(Color currentColor) {
    trail.setColor(currentColor);
  }

  private Image getImageByName(String name) {
    return new Image(this.getClass().getClassLoader().getResourceAsStream(name));
  }

}
