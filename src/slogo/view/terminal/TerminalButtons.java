package slogo.view.terminal;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import slogo.Model.Parsing.LanguageHandler;
import slogo.Controller.Actions;
import slogo.view.components.InputButton;

public class TerminalButtons extends VBox {

  private static ResourceBundle layout;
  private static final String RESOURCES_TERMINAL_PATH = "resources/UI/Layouts";

  protected TerminalButtons(LanguageHandler language, Actions actions, TerminalInput input) {
    layout = ResourceBundle.getBundle(RESOURCES_TERMINAL_PATH);

    List<String> buttonList = Arrays.asList(layout.getString("Terminal").split(","));

    setPadding(new Insets(5));
    getStyleClass().add("button-box");
    setAlignment(Pos.CENTER);
    setSpacing(10);

    for (String key : buttonList) {
      InputButton button = new InputButton(language, key, actions, input);
      getChildren().add(button);
    }
  }
}
