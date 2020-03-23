package slogo.view.components;

import javafx.scene.control.Button;
import slogo.Model.Parsing.LanguageHandler;
import slogo.Controller.Actions;
import slogo.view.terminal.TerminalInput;

/**
 * @author jaidharosenblatt created a styled button that extends a component. This class is public
 * since it is constructed in the terminal package
 */
public class InputButton extends Component {

  /**
   * Constructs a new input button
   *
   * @param language a LanguageHandler object that contains its language
   * @param key      the key used to set methods and prompt
   * @param actions  an Action object used to find the method associated with key
   * @param input    an input box in terminal to control
   */
  public InputButton(LanguageHandler language, String key, Actions actions, TerminalInput input) {
    super(language, key, actions);
    Button button = new Button(getPromptFromKey(key));
    button.getStyleClass().add("terminal-button");
    button.setOnAction(handler -> handleAction(input.getText()));
    getChildren().add(button);
  }
}
