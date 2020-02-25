package view;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class TurtleView {

  private ImageView graphic;
  private Point2D position;
  private boolean turtleVisible;
  private boolean penActive = true;
  private double heading;
  private Pane pane;

  protected TurtleView(Image image, double x, double y, double heading) {
    this.pane = new Pane();
    this.graphic = new ImageView(image);
    graphic.setFitWidth(50);
    graphic.setFitHeight(50);

    Point2D point = new Point2D(x, y);
    pane.getChildren().add(graphic);

    setPosition(point);
    setHeading(heading);
  }

  protected void setPosition(Point2D point) {
    this.position = point;
    graphic.setX(point.getX() - graphic.getBoundsInLocal().getWidth() / 2);
    graphic.setY(point.getY() - graphic.getBoundsInLocal().getHeight() / 2);
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

  protected Node getPane() {
    return this.pane;
  }

}
