package view;

import javafx.geometry.Insets;
import javafx.scene.control.TextArea;

public class TerminalInput extends TextArea {

  protected TerminalInput(String prompt) {
    getStyleClass().add("input-box");
    setPadding((new Insets(0, 5, 5, 5)));
    setPromptText(prompt);
    setFocusTraversable(false);
  }

}
