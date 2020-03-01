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

  public static final String DEFAULT_TURTLE_IMAGE = "turtle.png";

  private List<PropertyChangeListener> listener;
  private String penColorData;
  private String backgroundColorData;
  private String turtleImage;

  private static final String PEN_COLOR = "Pen Color";
  private static final String BACKGROUND_COLOR = "Background Color";
  private static final String TURTLE_IMAGE = "TurtleImage";
  private static final String DEFAULT_PEN_COLOR = Color.NAVY.toString();
  private static final String DEFAULT_BACKGROUND_COLOR = Color.WHITE.toString();

  private static final String PREFIX = "resources/UI/";
  private static final String RESOURCES_TERMINAL = "resources/Layouts/SettingsTab/";
  private static ResourceBundle actionResources;
  private static ResourceBundle uiResources;

  private LanguageDropdown languageDropdown;
  private TurtleImageDropdown turtleImageDropdown;
  private ColorPickerBox backgroundColorPicker;
  private ColorPickerBox penColorPicker;
  private static final String CLASS_PATH = "view.SettingsTab.";

  public SettingView(String language, Actions actions) {
    super("SettingTab");

    actionResources = ResourceBundle.getBundle(RESOURCES_TERMINAL + language);
    uiResources = ResourceBundle.getBundle(PREFIX + language);
    listener = new ArrayList<>();
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
        System.out.println(dropdown);
        vbox.getChildren().add(dropdown);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    HelpButton helpButton = new HelpButton(uiResources);

//    vbox.getChildren()
//        .addAll(helpButton, turtleImageDropdown, penColorPicker, backgroundColorPicker,
//            languageDropdown);
    setContent(vbox);
  }

  public String getLanguage() {
    return languageDropdown.getValue();
  }

}
