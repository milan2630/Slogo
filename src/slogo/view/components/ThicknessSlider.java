package slogo.view.components;

import slogo.view.Actions;
import javafx.scene.control.Slider;

public class ThicknessSlider extends Component {

  protected ThicknessSlider(String language, String key, Actions actions) {
    super(language, key, actions);
    Slider slider = new Slider();
    slider.setShowTickLabels(true);
    slider.setValue(Double.parseDouble(getDefaultFromKey(key)));
    slider.setMax(30);
    slider.setMajorTickUnit(5);
    slider.valueProperty().addListener((observable, oldValue, newValue) ->
        handleAction(newValue.toString())
    );
    getChildren().add(slider);
  }
}
