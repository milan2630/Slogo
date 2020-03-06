package slogo.view.components;

import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import slogo.view.Actions;

public class TurtleMover extends Component {

  private Slider slider;
  private Actions actions;
  private String key;

  public TurtleMover(String language, String key, Actions actions) {
    super(language, key, actions);

    VBox wrapper = new VBox();
    this.actions = actions;
    this.key = key;

    createSlider();
    HBox box = createMovementController();

    wrapper.getChildren().addAll(slider, box);
    getChildren().add(box);
  }

  private void createSlider() {
    slider = new Slider();
    slider.setShowTickLabels(true);
    slider.setMax(100);
    slider.setValue(Double.parseDouble(getDefaultFromKey(key)));
    slider.setMajorTickUnit(20);
  }

  private HBox createMovementController() {
    HBox box = new HBox();

    Button top = createButton("Forward");
    Button bottom = createButton("Backward");
    Button left = createButton("Left");
    Button right = createButton("Right");

    box.getChildren().addAll(top, bottom, left, right);
    return box;
  }

  private Button createButton(String commandName) {
    String command = getCommandByKey(commandName, 1);
    Button button = new Button();
    button.setText(command + " " + Integer.parseInt(getDefaultFromKey(key)));

    slider.valueProperty().addListener(
        (obs, oldValue, newValue) -> button.setText(command + " " + newValue.intValue()));

    button.setOnAction(value -> handleAction(button.getText()));
    button.getStyleClass().add("turtle-control-button");
    return button;
  }

}
