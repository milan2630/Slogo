package slogo.view.settingtab;

import java.lang.reflect.Method;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import slogo.ReflectionException;
import slogo.view.Actions;

public class TurtleMover extends LabelAndAction {

  public static final int DEFAULT_VALUE = 10;
  private Slider slider;
  private Actions actions;
  private String methodName;

  public TurtleMover(String language, String methodName, Actions actions) {
    super(language, methodName);

    VBox wrapper = new VBox();
    this.actions = actions;
    this.methodName = methodName;
    setAlignment(Pos.CENTER);

    createSlider();
    HBox box = createMovementController();

    wrapper.getChildren().addAll(slider, box);

    getChildren().add(wrapper);
  }

  private void createSlider() {
    slider = new Slider();
    slider.setShowTickLabels(true);
    slider.setMax(100);
    slider.setValue(Double.parseDouble(getDefaultFromKey(methodName)));
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
    button.setText(command + " " + Integer.parseInt(getDefaultFromKey(methodName)));

    slider.valueProperty().addListener(
        (obs, oldValue, newValue) -> button.setText(command + " " + newValue.intValue()));

    button.setOnAction(value -> handleAction(button.getText(), methodName, actions));
    button.getStyleClass().add("turtle-control-button");
    return button;
  }

  protected void handleAction(String value, String methodName, Actions target) {
    try {
      Method m = target.getClass().getDeclaredMethod(methodName, String.class);
      m.invoke(target, value);
    } catch (Exception e) {
      throw new ReflectionException("InvalidMethod", methodName);
    }
  }

}
