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

/**
 * @author jaidharosenblatt Manages the visualized turtles for a given scene. All of the turtles are
 * put in a pane so that they can be directly added to a scene. Assumes that a immutable turtle map
 * is a error free (besides going out of bounds). It is dependent on Trail, Turtle, and TurtleView.
 */
public class TurtleManager extends Pane {

  private Map<Double, TurtleView> turtleMap;
  private List<Color> palette;

  /**
   * Constructs a new pane with a single turtle.
   */
  public TurtleManager() {
    palette = new ArrayList<>();
    turtleMap = new HashMap<>();
    turtleMap.put(1.0, createTurtle(1));
    updatePane();
  }

  /**
   * Uses turtle to update each turtle based on a list of its states. Also checks if a current state
   * is out of bounds given the current gui size and throws a parsing exception.
   *
   * @param turtles a map from turtle ID to a list of that turtles states
   * @throws ParsingException
   */
  public void updateTurtles(Map<Double, List<ImmutableTurtle>> turtles) throws ParsingException {
    for (double i : turtles.keySet()) {
      List<ImmutableTurtle> turtleList = turtles.get(i);
      if (turtleMap.get(i) == null) {
        turtleMap.put(i, createTurtle(i));
      }
      for (ImmutableTurtle turtleState : turtleList) {
        TurtleView turtle = turtleMap.get(i);
        turtle.setPenState(turtleState.getPenState());
        turtle.setPenColor(palette.get((int) turtleState.getPenColorIndex()));
        turtle.setTurtleHeading(turtleState.getHeading());
        turtle.setPenThickness(turtleState.getPenThickness());
        turtle.setTurtleVisibility(turtleState.getShowing());
        turtle.setShape(turtleState.getTurtleImageIndex());
        if (checkTurtleOutOfBounds(turtleState)) {
          throw new ParsingException("OutOfBoundsException");
        }
        turtle.moveTurtle(new Point2D(turtleState.getX(), -1 * turtleState.getY()));
      }
    }
    updatePane();
  }

  /**
   * Set the palette to a new theme
   *
   * @param palette a list of possible colors where each color's id is its index in the list
   */
  public void setPalette(List<Color> palette) {
    this.palette = palette;
  }

  /**
   * resets a trail for a given turtle
   *
   * @param i the id for the turtle to reset
   */
  public void resetTrail(double i) {
    turtleMap.remove(i);
    turtleMap.put(i, createTurtle(i));
    updatePane();
  }

  /**
   * Sets the background color of our turtle display
   *
   * @param color the new color
   */
  public void setBackgroundColor(Color color) {
    setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
  }

  private void updatePane() {
    getChildren().clear();
    for (double index : turtleMap.keySet()) {
      getChildren().add(turtleMap.get(index));
    }
  }

  private Boolean checkTurtleOutOfBounds(ImmutableTurtle turtle) {
    return turtle.getX() > getWidth() / 2
        || turtle.getX() < -1 * getWidth() / 2 ||
        turtle.getY() > getHeight() / 2 || turtle.getY() < -1 *
        getHeight() / 2;
  }

  private TurtleView createTurtle(double i) {
    TurtleView turtle = new TurtleView(i);
    turtle.translateXProperty().bind(widthProperty().divide(2));
    turtle.translateYProperty().bind(heightProperty().divide(2));

    return turtle;
  }
}
