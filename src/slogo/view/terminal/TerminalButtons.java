package slogo.view.terminal;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import slogo.view.Actions;

public class TerminalButtons extends VBox {

  private static ResourceBundle layout;
  private static ResourceBundle prompts;
  private static ResourceBundle methods;


  private static final String RESOURCES_TERMINAL_PATH = "resources/UI/Layouts";
  private static final String RESOURCES_METHODS_PATH = "resources/UI/ReflectionMethods";
  private static final String RESOURCES_PROMPT_PATH = "resources/UI/";


  protected TerminalButtons(String language, Actions actions, TerminalInput input) {
    layout = ResourceBundle.getBundle(RESOURCES_TERMINAL_PATH);
    methods = ResourceBundle.getBundle(RESOURCES_METHODS_PATH);

    prompts = ResourceBundle.getBundle(RESOURCES_PROMPT_PATH + language);

    List<String> buttonList = Arrays.asList(layout.getString("Terminal").split(","));

    setPadding(new Insets(5));
    getStyleClass().add("button-box");
    setAlignment(Pos.CENTER);
    setSpacing(10);

    for (String key : buttonList) {
      InputButton button = new InputButton(prompts.getString(key), methods.getString(key), actions, input);
      getChildren().add(button);
    }
  }
}
