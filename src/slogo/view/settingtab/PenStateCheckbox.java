package slogo.view.settingtab;

import javafx.scene.control.CheckBox;
import slogo.view.Actions;

public class PenStateCheckbox extends LabelAndAction {

  protected PenStateCheckbox(String language, String methodName, Actions target) {
    super(language, methodName);
    CheckBox checkBox = new CheckBox();
    getChildren().add(checkBox);
    checkBox.setSelected(true);
    checkBox.selectedProperty().addListener(
        handler -> {
          String status = checkBox.isSelected() ? "pu" : "pd";
          handleAction(status, methodName, target);
        });
  }
}
