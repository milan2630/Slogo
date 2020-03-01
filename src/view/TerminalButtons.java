package view;

import java.util.Collections;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

public class TerminalButtons extends VBox {

  protected TerminalButtons(ResourceBundle actionResources, Actions actions, TerminalInput input) {
    setPadding((new Insets(10, 5, 10, 5)));
    getStyleClass().add("button-box");

    for (String key : Collections.list(actionResources.getKeys())) {
      getChildren().add(new InputButton(actionResources.getString(key), key, actions, input));
    }
  }
}
