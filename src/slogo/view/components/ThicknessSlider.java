package slogo.view.components;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import slogo.Model.Parsing.LanguageHandler;
import slogo.Controller.Actions;
import javafx.scene.control.Slider;

/**
 * @author jaidharosenblatt extends component to use a slider that updates when a button is clicked
 */
public class ThicknessSlider extends Component {

  protected ThicknessSlider(LanguageHandler language, String key, Actions actions) {
    super(language, key, actions);
    HBox hbox = new HBox();
    Slider slider = new Slider();
    slider.setShowTickLabels(true);
    slider.setValue(Double.parseDouble(getDefaultFromKey(key)));
    slider.setMax(30);
    slider.setMajorTickUnit(5);

    Button button = new Button(getPromptFromKey("PenThicknessButton"));
    button.setOnAction(handler -> {
      String command = getCommandByKey("SetPenSize", 0) + " " + slider.getValue();
      handleAction(command);
    });

    hbox.getChildren().addAll(button, slider);
    getChildren().add(hbox);
  }
}
