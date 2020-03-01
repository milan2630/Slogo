package view.SettingsTab;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ColorPickerBox extends HBox {

  private ColorPicker colorPicker;

  protected ColorPickerBox(String prompt) {
    setPadding((new Insets(10, 5, 10, 5)));
    setAlignment(Pos.CENTER);
    Text text = new Text(prompt);
    colorPicker = new ColorPicker();
    getChildren().addAll(text, colorPicker);
    setSpacing(10.0);
    text.getStyleClass().add("settings-text");
  }


  protected void setOnAction(EventHandler<ActionEvent> value) {
    colorPicker.setOnAction(value);
  }

  protected void setColor(Color value) {
    colorPicker.setValue(value);
  }

  protected Color getColor() {
    return colorPicker.getValue();
  }
}
