package slogo.view.settingtab;

import javafx.scene.control.ColorPicker;
import slogo.view.Actions;

public class ColorPickerBox extends LabelAndAction {

  private ColorPicker colorPicker;

  protected ColorPickerBox(String prompt, String methodName, Actions target) {
    super(prompt);
    colorPicker = new ColorPicker();
    colorPicker.setOnAction(
        handler -> handleAction(colorPicker.getValue().toString(), methodName, target));
    getChildren().add(colorPicker);
  }
}
