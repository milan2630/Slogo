package slogo.view.components;

import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import slogo.Model.Parsing.LanguageHandler;
import slogo.Controller.Actions;

/**
 * @author jaidharosenblatt [unused class] transforms a colorpicker box to implement a component.
 * This class was used before the transition to palettes
 */
public class ColorPickerBox extends Component {

  private ColorPicker colorPicker;

  protected ColorPickerBox(LanguageHandler language, String key, Actions actions) {
    super(language, key, actions);
    colorPicker = new ColorPicker();
    colorPicker.setValue(Color.web(getDefaultFromKey(key)));
    colorPicker.setOnAction(
        handler -> handleAction(colorPicker.getValue().toString()));
    getChildren().add(colorPicker);
  }
}
