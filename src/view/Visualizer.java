package view;

import java.util.ResourceBundle;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Visualizer {

  private static final String DEFAULT_LANGUAGE = "English";
  private static final double SCENE_WIDTH = 500;
  private static final double SCENE_HEIGHT = 500;

  private Image turtleImage;

  private ResourceBundle resourceBundle = ResourceBundle
      .getBundle("resources/ui/" + DEFAULT_LANGUAGE);

  public Visualizer(Stage stage) {
    BorderPane root = new BorderPane();
    stage.setTitle(resourceBundle.getString("Title"));

    root.getChildren().add(addTurtle());

    Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
    stage.setScene(scene);
    stage.show();
  }

  private Node addTurtle() {
    turtleImage = new Image(this.getClass().getClassLoader().getResourceAsStream("turtle.png"));
    return new TurtleView(turtleImage, 250, 250, 50).getNode();
  }

}
