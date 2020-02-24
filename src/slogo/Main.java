package slogo;

import javafx.application.Application;
import javafx.stage.Stage;
import view.Display;
import view.Visualizer;

/**
 * Feel free to completely change this code or delete it entirely.
 */
public class Main extends Application {
  private static final String LANGUAGE = "English";

  /**
   * Start of the program.
   */
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    new Visualizer(primaryStage, LANGUAGE);
  }
}
