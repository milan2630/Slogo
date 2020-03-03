package slogo.view.settingtab;

import java.lang.reflect.Constructor;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

import java.util.*;
import slogo.view.Actions;
import slogo.ReflectionException;

public class SettingView extends VBox {

  private static final String PREFIX = "resources/UI/";
  private static final String RESOURCES_TERMINAL = "resources/Layouts/SettingsTab/";
  private static ResourceBundle actionResources;
  private static ResourceBundle uiResources;
  private static final String CLASS_PATH = "slogo.view.settingtab.";

  public SettingView(String language, Actions actions) {
    actionResources = ResourceBundle.getBundle(RESOURCES_TERMINAL + language);
    uiResources = ResourceBundle.getBundle(PREFIX + language);
    setupTab(actions);
  }

  private void setupTab(Actions actions) {
    setSpacing(2);
    List<String> buttonList = Collections.list(actionResources.getKeys());
    Collections.sort(buttonList);

    for (String key : buttonList) {
      try {
        String prompt = uiResources.getString(key);
        Class<?> clazz = Class.forName(CLASS_PATH + actionResources.getString(key));
        Constructor<?> constructor = clazz
            .getDeclaredConstructor(String.class, String.class, Actions.class);
        LabelAndAction dropdown = (LabelAndAction) constructor.newInstance(prompt, key, actions);
        getChildren().add(dropdown);
      } catch (Exception e) {
        throw new ReflectionException("InvalidClass", key);
      }
    }
    HelpButton helpButton = new HelpButton(uiResources);
    getChildren().add(helpButton);
  }
}
