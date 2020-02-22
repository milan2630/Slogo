package view;

import javafx.scene.layout.StackPane;

public class Terminal {
  private StackPane stackPane;
  private static final double SCENE_WIDTH = 800;
  private static final double SCENE_HEIGHT = 100;

  public Terminal(){
    this.stackPane = new StackPane();
    this.stackPane.setMinSize(SCENE_WIDTH, SCENE_HEIGHT);
  }

  public StackPane getStackPane() {
    return this.stackPane;
  }
}
