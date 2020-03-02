package view.Terminal;

import java.lang.reflect.Method;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import view.Actions;

public class InputButton extends Button {

  public InputButton(String promptText, String methodName, Actions target, TerminalInput input) {
    setText(promptText);
    getStyleClass().add("terminal-button");
    setOnAction(handler -> {
      try {
        Method m = target.getClass().getDeclaredMethod(methodName, TextArea.class);
        m.invoke(target, input);
      } catch (Exception e) {
        // FIXME: typically make your own custom exception to throw
        throw new RuntimeException("Improper configuration", e);
      }
    });
  }

}
