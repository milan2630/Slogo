package view;

import java.awt.Point;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TurtleView {

  private ImageView graphic;
  private Point position;
  private double heading;
  private boolean visible = true;

  public TurtleView(Image image, double x, double y, double heading) {
    this.graphic = new ImageView(image);
    graphic.setFitWidth(50);
    graphic.setFitHeight(50);

    Point point = new Point();
    point.setLocation(x, y);

    setPosition(point);
    setHeading(heading);
  }

  public void setPosition(Point point) {
    this.position = point;
    graphic.setX(this.position.getX() - graphic.getBoundsInLocal().getWidth() / 2);
    graphic.setY(this.position.getY() - graphic.getBoundsInLocal().getHeight() / 2);
  }

  public void setHeading(double heading) {
    this.heading = heading;
    graphic.setRotate(heading);
  }

  public Node getNode() {
    return this.graphic;
  }

}
