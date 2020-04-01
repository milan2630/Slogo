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

/**
 * @author jaidharosenblatt A box for controlling the buttons needed for terminal. This class is a
 * node so it can be directly added to a scene.
 */
public class TerminalButtons extends VBox {

  private static ResourceBundle layout;
  private static final String RESOURCES_TERMINAL_PATH = "resources/UI/Layouts";

  /**
   * Constructs a new terminal button box. Uses reflection by reading a the "Layout" property file
   * in resources and assigning each button a desired method using the passed in actions object.
   *
   * @param language the current language in the gui
   * @param actions  an instance of a Actions class that holds the methods that will be executed *
   *                 with buttons
   * @param input    the text box input to control
   */
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
