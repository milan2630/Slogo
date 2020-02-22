package view;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class TrailView {

  private List<Node> trail = new ArrayList<>();
  private FlowPane flowPane = new FlowPane();
  private Color currentColor = Color.BLACK;
  private double currentThickness;

  protected TrailView(Double thickness, Color color) {
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

  protected void addLine(Point start, Point end) {
    Line line = new Line();
    line.setStrokeWidth(currentThickness);
    line.setStroke(currentColor);
    line.setStartX(start.getX());
    line.setStartY(start.getY());
    line.setEndX(end.getX());
    line.setEndY(end.getY());
    flowPane.getChildren().add(line);
  }

  protected Node getNode() {
    return flowPane;
  }

  protected void clear() {
    flowPane.getChildren().clear();
  }
}
