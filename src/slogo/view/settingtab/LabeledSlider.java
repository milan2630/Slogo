package slogo.view.settingtab;

import slogo.view.Actions;
import javafx.scene.control.Slider;

public class LabeledSlider extends LabelAndAction {

  protected LabeledSlider(String language, String methodName, Actions target) {
    super(language, methodName);
    Slider slider = new Slider();
    slider.setShowTickLabels(true);
    slider.setMax(30);
    slider.setMajorTickUnit(5);
    slider.valueProperty().addListener((observable, oldValue, newValue) ->
        handleAction(newValue.toString(), methodName, target)
    );
    getChildren().add(slider);
  }
}
