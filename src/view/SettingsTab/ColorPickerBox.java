package view.SettingsTab;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import view.Actions;

public class ColorPickerBox extends LabeledDropdown {

  private ColorPicker colorPicker;

  protected ColorPickerBox(String prompt, String methodName, Actions target) {
    super(prompt, methodName, target);
    colorPicker = new ColorPicker();
    colorPicker.setOnAction(
        handler -> handleAction(colorPicker.getValue().toString(), methodName, target));
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
