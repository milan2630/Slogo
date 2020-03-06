package slogo.view.turtledisplay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import slogo.Model.ErrorHandling.ParsingException;
import slogo.Model.TurtleModel.ImmutableTurtle;

public class TurtleManager extends Pane {

  private Map<Integer, TurtleView> turtleMap;

  public TurtleManager() {
    turtleMap = new HashMap<>();
    turtleMap.put(0, createTurtle(0));
    updatePane();
  }

  public void updateTurtles(List<ImmutableTurtle> turtles) throws ParsingException {
    for (int i = 0; i < turtles.size(); i++) {
      ImmutableTurtle turtleProperties = turtles.get(i);
      if (turtleMap.get(i) == null) {
        turtleMap.put(i, createTurtle(i));
      }
      TurtleView turtle = turtleMap.get(i);
      turtle.setPenState(turtleProperties.getPenState());
      turtle.setTurtleHeading(turtleProperties.getHeading());
      turtle.setPenThickness(turtleProperties.getPenThickness());
      if (checkTurtleOutOfBounds(turtleProperties)) {
        throw new ParsingException("OutOfBoundsException", i);
      }
      turtle.moveTurtle(new Point2D(turtleProperties.getX(), turtleProperties.getY()));
//      turtle.setPenColor(turtleProperties.getPenColorIndex());
    }
    updatePane();
  }

  public void resetTrail(int i) {
    TurtleView turtle = turtleMap.get(i);
    turtle.reset();
  }

  private TurtleView createTurtle(int i) {
    TurtleView turtle = new TurtleView(i);
    turtle.translateXProperty().bind(widthProperty().divide(2));
    turtle.translateYProperty().bind(heightProperty().divide(2));

    return turtle;
  }


  public void setBackgroundColor(Color color) {
    setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
  }

  private void updatePane() {
    getChildren().clear();
    for (int index : turtleMap.keySet()) {
      getChildren().add(turtleMap.get(index));
    }
  }

  private Boolean checkTurtleOutOfBounds(ImmutableTurtle turtle) {
    return turtle.getX() > getWidth() / 2
        || turtle.getX() < -1 * getWidth() / 2 ||
        turtle.getY() > getHeight() / 2 || turtle.getY() < -1 *
        getHeight() / 2;
  }
}