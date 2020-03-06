package slogo.view.components;

import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import slogo.view.Actions;

public class ColorPickerBox extends Component {

  private ColorPicker colorPicker;

  protected ColorPickerBox(String language, String key, Actions actions) {
    super(language, key, actions);
    colorPicker = new ColorPicker();
    colorPicker.setValue(Color.web(getDefaultFromKey(key)));
    colorPicker.setOnAction(
        handler -> handleAction(colorPicker.getValue().toString()));
    getChildren().add(colorPicker);
  }
}
