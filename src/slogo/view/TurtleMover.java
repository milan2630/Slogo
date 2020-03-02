package slogo.view;

import java.lang.reflect.Method;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import slogo.ReflectionException;

public class TurtleMover extends VBox {

  private double increment = 10;
  private Actions actions;

  public TurtleMover(String language, Actions actions) {
    this.actions = actions;
    setAlignment(Pos.CENTER);
    GridPane box = new GridPane();
    box.setPrefSize(10, 10);

    Button top = createButton("top", "fd");
    box.add(top, 1, 0);

    Button bottom = createButton("bottom", "bk");
    box.add(bottom, 1, 2);

    Button left = createButton("left", "lt");
    box.add(left, 0, 1);

    Button right = createButton("right", "rt");
    box.add(right, 2, 1);

    getChildren().add(box);
  }

  private Button createButton(String prompt, String commandName) {
    Button button = new Button();
    button.setText(prompt + " " + increment);
    String command = commandName + " " + increment;
    button.setOnAction(value -> handleAction(command, "handleTurtleMovement", actions));
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
