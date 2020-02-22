package view;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

public class Display {
  private Image turtleImage = getImageByName("turtle.png");
  private StackPane stackPane;
  private static final double SCENE_WIDTH = 400;
  private static final double SCENE_HEIGHT = 400;

  public Display(){
    this.stackPane = new StackPane();
    this.stackPane.setMinSize(SCENE_WIDTH, SCENE_HEIGHT);
    stackPane.getChildren().add(addTurtle());
  }

  public StackPane getStackPane() {
    return this.stackPane;
  }

  private Node addTurtle() {
    return new TurtleView(turtleImage, SCENE_HEIGHT / 2, SCENE_WIDTH / 2, 50).getNode();
  }

  private Image getImageByName(String name) {
    return new Image(this.getClass().getClassLoader().getResourceAsStream(name));
  }
}
