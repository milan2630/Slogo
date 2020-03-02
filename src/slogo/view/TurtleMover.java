package slogo.view;

import java.lang.reflect.Method;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import slogo.ReflectionException;

public class TurtleMover extends VBox {

  private Slider slider;
  private Actions actions;

  public TurtleMover(String language, Actions actions) {
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

    Button top = createButton("top", "fd");
    box.add(top, 1, 0);

    Button bottom = createButton("bottom", "bk");
    box.add(bottom, 1, 2);

    Button left = createButton("turn left", "lt");
    box.add(left, 0, 1);

    Button right = createButton("turn right", "rt");
    box.add(right, 2, 1);
    return box;
  }

  private Button createButton(String prompt, String commandName) {
    Button button = new Button();

    slider.valueProperty().addListener((obs, oldValue, newValue) ->
    {
      button.setText(commandName + " " + newValue.intValue());
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
