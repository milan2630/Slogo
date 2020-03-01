package view;

import java.util.Collections;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class TerminalButtons extends VBox {

  protected TerminalButtons(ResourceBundle actionResources, Actions actions, TerminalInput input) {
    setPadding(new Insets(5));
    getStyleClass().add("button-box");
    setAlignment(Pos.CENTER);
    setSpacing(10);

    for (String key : Collections.list(actionResources.getKeys())) {
      InputButton button = new InputButton(actionResources.getString(key), key, actions, input);
      getChildren().add(button);
    }
  }
}
