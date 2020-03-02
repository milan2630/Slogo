package slogo.view.settingtab;

import java.lang.reflect.Method;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import slogo.view.Actions;
import slogo.ReflectionException;

public abstract class LabelAndAction extends HBox {


  protected LabelAndAction(String prompt) {
    Text text = new Text(prompt);
    getChildren().add(text);
    setPadding((new Insets(10, 5, 10, 5)));
    setAlignment(Pos.CENTER);
    setSpacing(10.0);
    text.getStyleClass().add("settings-text");
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
