package view.SettingsTab;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

public class ColorPickerBox extends LabeledDropdown {

  private ColorPicker colorPicker;

  protected ColorPickerBox(String prompt) {
    super(prompt);
    colorPicker = new ColorPicker();
    getChildren().add(colorPicker);
  }

  protected void setOnAction(EventHandler<ActionEvent> value) {
    colorPicker.setOnAction(value);
  }

  protected void setValue(String value) {
    colorPicker.setValue(Color.web(value));
  }

  protected String getValue() {
    return colorPicker.getValue().toString();
  }
}
