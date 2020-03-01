package view.SettingsTab;

import java.beans.PropertyChangeEvent;
import java.lang.reflect.Constructor;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.beans.PropertyChangeListener;
import java.util.*;
import view.Actions;
import view.Terminal.InputButton;

public class SettingView extends Tab {

  private static final String PREFIX = "resources/UI/";
  private static final String RESOURCES_TERMINAL = "resources/Layouts/SettingsTab/";
  private static ResourceBundle actionResources;
  private static ResourceBundle uiResources;
  private static final String CLASS_PATH = "view.SettingsTab.";

  public SettingView(String language, Actions actions) {
    super("SettingTab");
    actionResources = ResourceBundle.getBundle(RESOURCES_TERMINAL + language);
    uiResources = ResourceBundle.getBundle(PREFIX + language);
    setupTab(actions);
  }

  private void setupTab(Actions actions) {
    VBox vbox = new VBox();
    vbox.setAlignment(Pos.TOP_LEFT);
    vbox.setSpacing(10);

    for (String key : Collections.list(actionResources.getKeys())) {
      try {
        String prompt = uiResources.getString(key);
        Class<?> clazz = Class.forName(CLASS_PATH + actionResources.getString(key));
        Constructor<?> constructor = clazz
            .getDeclaredConstructor(String.class, String.class, Actions.class);
        LabeledDropdown dropdown = (LabeledDropdown) constructor.newInstance(prompt, key, actions);
        vbox.getChildren().add(dropdown);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    HelpButton helpButton = new HelpButton(uiResources);
    vbox.getChildren().add(helpButton);

    setContent(vbox);
  }

}
