package slogo.view.turtledisplay;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Display extends Pane {

  private TrailView trail;
  private TurtleView turtle;
  private static final String TURTLE_PATH = "turtles/";
  private static final String DEFAULT_RESOURCE_PATH = "resources/UI/Default";
  private static final String PREFIX = "resources/UI/";
  private static final String RESOURCES_LAYOUTS = PREFIX + "Layouts";
  private List images;
  private ResourceBundle layouts;
  private ResourceBundle resourceBundle = ResourceBundle.getBundle(DEFAULT_RESOURCE_PATH);
  private Image turtleImage;

  public Display() {
    turtleImage = getImageByName(TURTLE_PATH + resourceBundle.getString("TurtleImage"));
    layouts = ResourceBundle.getBundle(RESOURCES_LAYOUTS);
    images = getImages();
    resetPane();
  }

  private List getImages() {
    List<String> imageList = Arrays.asList(layouts.getString("SettingView").split(","));
    return imageList;
  }

  public void resetPane() {

    getChildren().clear();
    this.turtle = new TurtleView(turtleImage, 0, 0, 0);
    this.trail = new TrailView(Double.parseDouble(resourceBundle.getString("PenThickness")),
        Color.web(resourceBundle.getString("PenColor")));
    setBackgroundColor(Color.web(resourceBundle.getString("BackgroundColor")));
    bindOriginToCenter();

    getChildren().addAll(trail, turtle);
  }

  private void bindOriginToCenter() {
    turtle.translateXProperty().bind(widthProperty().divide(2));
    turtle.translateYProperty().bind(heightProperty().divide(2));

    trail.translateXProperty().bind(widthProperty().divide(2));
    trail.translateYProperty().bind(heightProperty().divide(2));
  }

  public void moveTurtle(Point2D newCoordinate) {
    Point2D oldCoordinate = turtle.getPosition();
    turtle.setPosition(newCoordinate);
    if (turtle.isPenActive() == 1) {
      trail.addLine(oldCoordinate, newCoordinate);
    }
  }

  public void setTurtleHeading(double newHeading) {
    turtle.setHeading(newHeading);
  }

  public void setTurtleVisibility(double state) {
    turtle.setTurtleVisible(state);
  }

  public void setPenState(double state) {
    turtle.setPenActive(state);
  }

  public void setBackgroundColor(Color color) {
    setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
  }

  public void setPenThickness(Double thickness) {
    trail.setCurrentThickness(thickness);
  }

  public void setTurtleImage(int index) {
    Image image = getImageByName(TURTLE_PATH + images.get(index));
    turtleImage = image;
    turtle.setGraphicImage(image);
  }

  public void setPenColor(Color currentColor) {
    trail.setCurrentColor(currentColor);
  }

  private Image getImageByName(String name) {

    return new Image(this.getClass().getClassLoader().getResourceAsStream(name));
  }

}
