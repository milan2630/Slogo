package slogo.view.turtledisplay;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * @author jaidharosenblatt holds a trail for a single turtle. Stores a list of lines that represent
 * the turtle's path. Trails are held in a pane so it can be added to a scene.
 */
public class Trail extends Pane {

  private List<Node> trail = new ArrayList<>();
  private Color currentColor;
  private double currentThickness;

  /**
   * Constructs a new trail. Is protected so that only the turtledisplay classes can access
   *
   * @param thickness the initial pen thickness
   * @param color     the initial pen color
   */
  protected Trail(Double thickness, Color color) {
    this.currentThickness = thickness;
    this.currentColor = color;
  }

  /**
   * Updates the color
   *
   * @param color the new color
   */
  protected void setColor(Color color) {
    this.currentColor = color;
  }

  /**
   * Updates the pen thickness
   *
   * @param currentThickness the pen thickness for future lines
   */
  protected void setCurrentThickness(double currentThickness) {
    this.currentThickness = currentThickness;
  }

  /**
   * Construct a Line object and add it the trail. Uses the current pen thickness and color
   *
   * @param start the coordinate to begin the line at
   * @param end   the coordinate to end the line at
   */
  protected void addLine(Point2D start, Point2D end) {
    Line line = new Line();
    line.setStrokeWidth(currentThickness);
    line.setStroke(currentColor);
    line.setStartX(start.getX());
    line.setStartY(start.getY());
    line.setEndX(end.getX());
    line.setEndY(end.getY());
    getChildren().add(line);
    trail.add(line);
  }
}
