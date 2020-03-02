package slogo.view.settingtab;

import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import slogo.view.Actions;

public class ColorPickerBox extends LabelAndAction {

  private ColorPicker colorPicker;

  protected ColorPickerBox(String language, String methodName, Actions target) {
    super(language, methodName);
    colorPicker = new ColorPicker();
    colorPicker.setValue(Color.web(getDefaultFromKey(methodName)));
    colorPicker.setOnAction(
        handler -> handleAction(colorPicker.getValue().toString(), methodName, target));
    getChildren().add(colorPicker);
  }
}
