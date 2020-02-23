package view;

import java.awt.Point;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TurtleView {

  private ImageView graphic;
  private Point position;
  private boolean turtleVisible;
  private boolean penActive = true;
  private double heading;

  protected TurtleView(Image image, double x, double y, double heading) {
    this.graphic = new ImageView(image);
    graphic.setFitWidth(50);
    graphic.setFitHeight(50);

    Point point = new Point();
    point.setLocation(x, y);

    setPosition(point);
    setHeading(heading);
  }

  protected void setPosition(Point point) {
    this.position = point;
    graphic.setX(point.getX());
    graphic.setY(point.getY());
  }

  protected Point getPosition() {
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
