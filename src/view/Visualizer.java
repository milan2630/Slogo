package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Visualizer implements PropertyChangeListener {

  private static final String DEFAULT_LANGUAGE = "English";
  private static ResourceBundle resourceBundle;
  private String language;
  private Display display;
  private Terminal terminal;

  private static final double SCENE_WIDTH = 800;
  private static final double SCENE_HEIGHT = 500;

  public Visualizer(Stage stage, String language) {
    this.language = language;
    setTitle(stage);

    BorderPane borderPane = new BorderPane();

    terminal = new Terminal(language);
    terminal.addChangeListener(this);

    display = new Display();

    borderPane.setRight(display.getPane());
    borderPane.setBottom(terminal.getPane());

    Scene scene = new Scene(borderPane, SCENE_WIDTH, SCENE_HEIGHT);
    stage.setScene(scene);
    stage.show();
  }

  private void setTitle(Stage stage) {
    try {
      resourceBundle = ResourceBundle
          .getBundle("resources/ui/" + language);
    } catch (MissingResourceException e) {
      this.language = DEFAULT_LANGUAGE;
      resourceBundle = ResourceBundle
          .getBundle("resources/ui/" + language);
    }
    stage.setTitle(resourceBundle.getString("Title"));
  }

  /**
   * Implements Observer Design pattern
   * @param evt
   */
  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    System.out.println(evt.getPropertyName() + " " + evt.getNewValue());
  }
}
