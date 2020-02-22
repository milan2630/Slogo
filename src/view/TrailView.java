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

  public TrailView(Double thickness, Color color){
    this.currentThickness = thickness;
    this.currentColor = color;
  }

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
    flowPane.getChildren().add(line);
  }

  public Node getNode(){
    return flowPane;
  }

  public void clear(){
    flowPane.getChildren().clear();
  }
}
