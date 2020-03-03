package slogo.view.settingtab;

import javafx.scene.control.CheckBox;
import slogo.view.Actions;

public class LabeledCheckBox extends LabelAndAction {

  protected LabeledCheckBox(String language, String methodName, Actions target) {
    super(language, methodName);
    CheckBox checkBox = new CheckBox();
    getChildren().add(checkBox);
    checkBox.setSelected(true);
    checkBox.selectedProperty().addListener(
        handler -> {
          int status = checkBox.isSelected() ? 1 : 0;
          handleAction(Integer.toString(status), methodName, target);
        });
  }
}
