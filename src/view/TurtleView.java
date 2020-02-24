package view;

import java.awt.Point;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TurtleView {

  private ImageView graphic;
  private Point2D position;
  private boolean turtleVisible;
  private boolean penActive = true;
  private double heading;

  protected TurtleView(Image image, double x, double y, double heading) {
    this.graphic = new ImageView(image);
    graphic.setFitWidth(50);
    graphic.setFitHeight(50);

    Point2D point = new Point2D(x,y);

    setPosition(point);
    setHeading(heading);
  }

  protected void setPosition(Point2D point) {
    this.position = point;
    graphic.setX(point.getX());
    graphic.setY(point.getY());
  }

  protected Point2D getPosition() {
    return this.position;
  }

  protected double getHeading() {
    return this.heading;
  }

  protected void setHeading(double heading) {
    graphic.setRotate(this.heading = heading);
  }

  protected boolean isTurtleVisible() {
    return turtleVisible;
  }

  protected void setTurtleVisible(boolean isVisible) {
    this.turtleVisible = isVisible;
    graphic.setVisible(isVisible);
  }

  protected boolean isPenActive() {
    return penActive;
  }

  protected void setPenActive(boolean penActive) {
    this.penActive = penActive;
  }

  protected Node getNode() {
    return this.graphic;
  }

}
