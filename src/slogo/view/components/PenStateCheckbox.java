package slogo.view.components;

import javafx.scene.control.CheckBox;
import slogo.Model.Parsing.LanguageHandler;
import slogo.Controller.Actions;

/**
 * @author jaidharosenblatt extends component to display the current state of a pen as a check box
 */
public class PenStateCheckbox extends Component {

  protected PenStateCheckbox(LanguageHandler language, String key, Actions actions) {
    super(language, key, actions);
    CheckBox checkBox = new CheckBox();
    checkBox.setSelected(true);
    checkBox.selectedProperty().addListener(
        handler -> {
          String status =
              checkBox.isSelected() ? getCommandByKey("PenDown", 0) : getCommandByKey("PenUp", 0);
          handleAction(status);
        });
    getChildren().add(checkBox);
  }
}
