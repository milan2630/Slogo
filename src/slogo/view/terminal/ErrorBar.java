package slogo.view.terminal;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * @author jaidharosenblatt A modified HBox that only displays error messages. Since it is a node,
 * it can be constructed and directly added to a scene.
 */
public class ErrorBar extends HBox {

  private Text errors;

  /**
   * Constructs an error bar. This method is protected so that it will only be used in a terminal
   */
  protected ErrorBar() {
    setPadding(new Insets(5));
    errors = new Text();
    getChildren().add(errors);

    getStyleClass().add("error-bar");
    errors.getStyleClass().add("error-text");
  }

  /**
   * Set the error bar to a given message
   *
   * @param error the message to display
   */
  protected void setText(String error) {
    errors.setText(error);
  }

}
