package view;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class TrailSegment {
  private List<Node> trail = new ArrayList<>();
  private Color currentColor;
  private double currentThickness;

  public Color getCurrentColor() {
    return currentColor;
  }

  public void setCurrentColor(Color currentColor) {
    this.currentColor = currentColor;
  }

  public double getCurrentThickness() {
    return currentThickness;
  }

  public void setCurrentThickness(double currentThickness) {
    this.currentThickness = currentThickness;
  }

  public void addLine(Point start, Point end){
    Line line = new Line();
    line.setStrokeWidth(currentThickness);
    line.setStroke(currentColor);
    line.setStartX(start.getX());
    line.setStartY(start.getY());
    line.setEndX(end.getX());
    line.setEndY(end.getY());
    trail.add(line);
  }

  public Pane getPane(){
    FlowPane flowPane = new FlowPane();
    flowPane.getChildren().addAll(trail);
    return flowPane;
  }
}
