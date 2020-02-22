package view;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Visualizer {

  private static final String DEFAULT_LANGUAGE = "English";
  private ResourceBundle resourceBundle;
  private StackPane display;
  private StackPane terminal;

  private static final double SCENE_WIDTH = 800;
  private static final double SCENE_HEIGHT = 500;

  public Visualizer(Stage stage, String language) {
    setTitle(stage, language);

    GridPane gridPane = new GridPane();
    terminal = new Terminal().getStackPane();
    terminal.setBackground(
        new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

    display = new Display().getStackPane();
    display.setBackground(
        new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));

    gridPane.add(display, 0, 1);
    gridPane.add(terminal, 1, 1);


    Scene scene = new Scene(gridPane, SCENE_WIDTH, SCENE_HEIGHT);
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
