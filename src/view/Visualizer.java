package view;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Visualizer {

  private static final String DEFAULT_LANGUAGE = "English";
  private ResourceBundle resourceBundle;
  private Pane display;
  private Pane terminal;

  private static final double SCENE_WIDTH = 800;
  private static final double SCENE_HEIGHT = 500;

  public Visualizer(Stage stage, String language) {
    setTitle(stage, language);

    BorderPane borderPane = new BorderPane();

    terminal = new Terminal().getPane();
    display = new Display().getPane();

    borderPane.setRight(display);
    borderPane.setBottom(terminal);

    Scene scene = new Scene(borderPane, SCENE_WIDTH, SCENE_HEIGHT);
    stage.setScene(scene);
    stage.show();
  }

  private void setTitle(Stage stage, String language) {
    try {
      resourceBundle = ResourceBundle
          .getBundle("resources/ui/" + language);
    } catch (MissingResourceException e) {
      resourceBundle = ResourceBundle
          .getBundle("resources/ui/" + DEFAULT_LANGUAGE);
    }
    stage.setTitle(resourceBundle.getString("Title"));
  }


}
