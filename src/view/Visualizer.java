package view;

import java.util.ResourceBundle;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Visualizer {
  private Stage stage;
  private static final String DEFAULT_LANGUAGE = "English";

  private ResourceBundle resourceBundle = ResourceBundle.getBundle("resources/ui/"+ DEFAULT_LANGUAGE);

  public Visualizer(Stage stage) {
    this.stage = stage;
    stage.setTitle(resourceBundle.getString("Title"));
    Scene scene = new Scene(new Group(),500,500);
    stage.setScene(scene);
    stage.show();
  }

}
