package slogo.view;

import java.lang.reflect.Method;
import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import slogo.ReflectionException;

public class TurtleMover extends VBox {

  private Slider slider;
  private Actions actions;
  private ResourceBundle resourceBundle;
  private final static String LANGUAGE_PATH = "resources/Languages/";

  public TurtleMover(String language, Actions actions) {
    resourceBundle = ResourceBundle.getBundle(LANGUAGE_PATH + language);
    this.actions = actions;
    setAlignment(Pos.CENTER);

    slider = new Slider();
    slider.setShowTickLabels(true);
    slider.setMax(100);
    slider.setValue(10);
    slider.setMajorTickUnit(20);

    GridPane box = createMovementController();

    getChildren().addAll(slider, box);
  }

  private GridPane createMovementController() {
    GridPane box = new GridPane();
    box.setPrefSize(10, 10);

    Button top = createButton("Forward");
    box.add(top, 1, 0);

    Button bottom = createButton("Backward");
    box.add(bottom, 1, 2);

    Button left = createButton("Left");
    box.add(left, 0, 1);

    Button right = createButton("Right");
    box.add(right, 2, 1);
    return box;
  }

  private Button createButton(String commandName) {
    //Splitting command by | ex: forward|fd
    String[] raw = resourceBundle.getString(commandName).split("\\|");
    Button button = new Button();

    slider.valueProperty().addListener((obs, oldValue, newValue) ->
    {
      button.setText(raw[0] + " " + newValue.intValue());
    });

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
