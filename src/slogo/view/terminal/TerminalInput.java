package slogo.view.terminal;

import javafx.geometry.Insets;
import javafx.scene.control.TextArea;

/**
 * @author jaidharosenblatt A simple class that is a styled TextArea
 */
public class TerminalInput extends TextArea {

  /**
   * Create a new input box
   *
   * @param prompt the initial text to display
   */
  protected TerminalInput(String prompt) {
    getStyleClass().add("input-box");
    setPadding((new Insets(0, 5, 5, 5)));
    setPromptText(prompt);
    setFocusTraversable(false);
  }

}
