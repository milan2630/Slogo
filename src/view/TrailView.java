package view;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class TrailView {

  private List<Node> trail = new ArrayList<>();
  private Pane pane;
  private Color currentColor = Color.BLACK;
  private double currentThickness;

  protected TrailView(Double thickness, Color color) {
    this.pane = new Pane();
    this.currentThickness = thickness;
    this.currentColor = color;
  }

  protected Color getCurrentColor() {
    return currentColor;
  }

  protected void setCurrentColor(Color currentColor) {
    this.currentColor = currentColor;
  }

  protected double getCurrentThickness() {
    return currentThickness;
  }

  protected void setCurrentThickness(double currentThickness) {
    this.currentThickness = currentThickness;
  }

  protected void addLine(Point2D start, Point2D end) {

    Line line = new Line();
    line.setStrokeWidth(currentThickness);
    line.setStroke(currentColor);
    line.setStartX(start.getX());
    line.setStartY(start.getY());
    line.setEndX(end.getX());
    line.setEndY(end.getY());
    pane.getChildren().add(line);
    trail.add(line);
  }

  protected Pane getPane() {
    return pane;
  }

  protected void clear() {
    pane.getChildren().clear();
  }
}
