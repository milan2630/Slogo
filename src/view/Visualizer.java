package view;

import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.MissingResourceException;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
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

    AnchorPane root = new AnchorPane();

    terminal = new Terminal(language);
    terminal.addChangeListener(this);

    display = new Display();

    addPanesToRoot(root);

    Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
    stage.setScene(scene);
    stage.show();

  }

  private void addPanesToRoot(AnchorPane root) {
    Node terminalNode = terminal.getPane();
    AnchorPane.setBottomAnchor(terminalNode, 0.0);
    AnchorPane.setLeftAnchor(terminalNode,0.0);
    AnchorPane.setRightAnchor(terminalNode,0.0);

    Node displayNode = display.getPane();
    AnchorPane.setTopAnchor(displayNode,0.0);
    AnchorPane.setBottomAnchor(displayNode,terminal.getHeight());
    AnchorPane.setRightAnchor(displayNode,0.0);
    //TODO replace 200 with controller width
    AnchorPane.setLeftAnchor(displayNode,200.0);

    root.getChildren().addAll(terminalNode,displayNode);
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
    int r = new Random().nextInt((int) this.display.getPane().getWidth());
    int r2 = new Random().nextInt((int) this.display.getPane().getHeight());

    System.out.println(evt.getNewValue());

    if (evt.getPropertyName().equals("Run")){
      display.moveTurtle(new Point2D(r,r2));
    }
    else {
      display.resetPane();
    }

  }
}
