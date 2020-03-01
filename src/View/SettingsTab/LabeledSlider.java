package View.SettingsTab;

import View.Actions;
import javafx.scene.control.Slider;

public class LabeledSlider extends LabeledDropdown {

  protected LabeledSlider(String prompt, String methodName, Actions target) {
    super(prompt);
    Slider slider = new Slider();
    slider.valueProperty().addListener((observable, oldValue, newValue) ->
        handleAction(newValue.toString(), methodName, target)
    );
    getChildren().add(slider);
  }
}
