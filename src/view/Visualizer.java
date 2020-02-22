package view;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
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

//    GridPane gridPane = new GridPane();
    BorderPane borderPane = new BorderPane();

    terminal = new Terminal().getStackPane();
    display = new Display().getStackPane();

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
