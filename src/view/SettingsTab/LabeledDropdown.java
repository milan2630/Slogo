package view.SettingsTab;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public abstract class LabeledDropdown extends HBox {

  protected LabeledDropdown(String prompt) {
    Text text = new Text(prompt);
    getChildren().add(text);
    setPadding((new Insets(10, 5, 10, 5)));
    setAlignment(Pos.CENTER);
    setSpacing(10.0);
    text.getStyleClass().add("settings-text");
  }
}
