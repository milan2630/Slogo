package View.SettingsTab;

import javafx.scene.control.ColorPicker;
import View.Actions;

public class ColorPickerBox extends LabeledDropdown {

  private ColorPicker colorPicker;

  protected ColorPickerBox(String prompt, String methodName, Actions target) {
    super(prompt);
    colorPicker = new ColorPicker();
    colorPicker.setOnAction(
        handler -> handleAction(colorPicker.getValue().toString(), methodName, target));
    getChildren().add(colorPicker);
  }
}
