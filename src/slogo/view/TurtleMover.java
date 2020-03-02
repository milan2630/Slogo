package slogo.view;

import java.lang.reflect.Method;
import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import slogo.ReflectionException;

public class TurtleMover extends VBox {

  public static final int DEFAULT_VALUE = 10;
  private Slider slider;
  private Actions actions;
  private ResourceBundle resourceBundle;
  private final static String LANGUAGE_PATH = "resources/Languages/";

  public TurtleMover(String language, Actions actions) {
    resourceBundle = ResourceBundle.getBundle(LANGUAGE_PATH + language);
    this.actions = actions;
    setAlignment(Pos.CENTER);

    createSlider();
    HBox box = createMovementController();

    getChildren().addAll(slider, box);
  }

  private void createSlider() {
    slider = new Slider();
    slider.setShowTickLabels(true);
    slider.setMax(100);
    slider.setValue(DEFAULT_VALUE);
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
    //Splitting command by | ex: forward|fd
    String[] raw = resourceBundle.getString(commandName).split("\\|");
    Button button = new Button();
    button.setText(raw[0] + " " + DEFAULT_VALUE);

    slider.valueProperty().addListener(
        (obs, oldValue, newValue) -> button.setText(raw[0] + " " + newValue.intValue()));

    button.setOnAction(value -> handleAction(button.getText(), "handleTurtleMovement", actions));
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
