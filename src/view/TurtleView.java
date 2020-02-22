package view;

import java.awt.Point;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TurtleView {

  private ImageView graphic;
  private double x;
  private double y;
  private double heading;

  public TurtleView(Image image, double x, double y, double heading) {
    this.graphic = new ImageView(image);
    this.x = x;
    this.y = y;
    this.heading = heading;
    graphic.setFitWidth(50);
    graphic.setFitHeight(50);
    updateTurtle();
  }

  private void updateTurtle() {
    graphic.setX(x - graphic.getBoundsInLocal().getWidth()/2);
    graphic.setY(y - graphic.getBoundsInLocal().getHeight()/2);
    graphic.setRotate(heading);
  }

  public Node getNode() {
    return graphic;
  }

}
