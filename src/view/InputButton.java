package view;

import java.lang.reflect.Method;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import slogo.Controller;

public class InputButton extends Button {

  public InputButton(String promptText, String methodName, Controller target, TextArea input) {
    setText(promptText);
    getStyleClass().add("terminal-button");
    setOnAction(handler -> {
      try {
        // find method with given name that takes String as its only parameter
        Method m = target.getClass().getDeclaredMethod(methodName, TextArea.class);
        m.invoke(target, input);
      } catch (Exception e) {
        // FIXME: typically make your own custom exception to throw
        throw new RuntimeException("Improper configuration", e);
      }
    });
  }

}
