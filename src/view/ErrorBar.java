package view;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ErrorBar extends HBox {

  private Text errors;

  protected ErrorBar() {
    setPadding(new Insets(5));
    errors = new Text();
    getChildren().add(errors);

    getStyleClass().add("error-bar");
    errors.getStyleClass().add("error-text");
  }

  protected void setText(String error) {
    errors.setText(error);
  }

}
