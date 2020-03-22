package slogo.view.turtledisplay;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * @author jaidharosenblatt a single turtle that holds a graphic and its properties. Is added to
 * TurtleView in order to visualize a given turtle.
 */
public class Turtle extends Pane {

  private ImageView graphic;
  private Point2D position;
  private double penActive = 1;

  /**
   * Constructs a new turtle
   *
   * @param image   the image to use for the graphic
   * @param x       the x coordinate to place our turtle
   * @param y       the y coordinate to place our turtle
   * @param heading the heading of the turtle
   */
  protected Turtle(Image image, double x, double y, double heading) {
    this.graphic = new ImageView(image);
    graphic.setFitWidth(50);
    graphic.setFitHeight(50);

    Point2D point = new Point2D(x, y);
    getChildren().add(graphic);

    setPosition(point);
    setHeading(heading);
  }

  /**
   * Updates a turtle's position
   *
   * @param point the coordinate to move a turtle to
   */
  protected void setPosition(Point2D point) {
    this.position = point;
    graphic.setX(point.getX() - graphic.getBoundsInLocal().getWidth() / 2);
    graphic.setY(point.getY() - graphic.getBoundsInLocal().getHeight() / 2);
  }

  /**
   * Gets a turtle's position
   *
   * @return the newcoordinate of the turtle
   */
  protected Point2D getPosition() {
    return this.position;
  }

  /**
   * Updates a turtle's heading
   *
   * @param heading the new heading
   */
  protected void setHeading(double heading) {
    graphic.setRotate(heading);
  }

  /**
   * Set a turtle's visibility
   *
   * @param isVisible visibility; 1 is visible and 0 is not
   */
  protected void setTurtleVisible(double isVisible) {
    graphic.setVisible(isVisible == 1);
  }

  /**
   * Determines if a turtle's pen is active
   *
   * @return the pen status (1 active 0 inactive)
   */
  protected double isPenActive() {
    return penActive;
  }
  
  /**
   * Sets a turtle's pen status
   *
   * @param penActive the pen status (1 active 0 inactive)
   */
  protected void setPenActive(double penActive) {
    this.penActive = penActive;
  }

  /**
   * Updates the turtle's graphic image
   *
   * @param image the new image to set to
   */
  protected void setGraphicImage(Image image) {
    graphic.setImage(image);
  }

}
