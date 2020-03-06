package slogo.view.settingtab;

import java.lang.reflect.Constructor;

import javafx.scene.layout.VBox;

import java.util.*;

import slogo.Model.Parsing.LanguageConverter;
import slogo.view.Actions;
import slogo.ReflectionException;

public class SettingView extends VBox {

  private static final String PREFIX = "resources/UI/";
  private static final String RESOURCES_CLASSES = PREFIX + "ReflectionClass";
  private static final String RESOURCES_LAYOUTS = PREFIX + "Layouts";

  private static ResourceBundle classMap;
  private static ResourceBundle prompts;
  private static ResourceBundle layouts;


  private static final String CLASS_PATH = "slogo.view.settingtab.";

  public SettingView(LanguageConverter language, Actions actions) {
    classMap = ResourceBundle.getBundle(RESOURCES_CLASSES);
    layouts = ResourceBundle.getBundle(RESOURCES_LAYOUTS);
    prompts = ResourceBundle.getBundle(PREFIX + language.getLanguage());

    setupTab(language, actions);
  }

  private void setupTab(LanguageConverter language, Actions actions) {
    List<String> buttonList = Arrays.asList(layouts.getString("SettingView").split(","));
    setSpacing(2);

    for (String key : buttonList) {
      try {
        Class<?> clazz = Class.forName(CLASS_PATH + classMap.getString(key));
        Constructor<?> constructor = clazz
            .getDeclaredConstructor(String.class, String.class, Actions.class);
        LabelAndAction o = (LabelAndAction) constructor.newInstance(language.getLanguage(), key, actions);

        getChildren().add(o);
      } catch (Exception e) {
        e.printStackTrace();
        throw new ReflectionException("InvalidClass", key);
      }
    }
    HelpButton helpButton = new HelpButton(prompts);
    getChildren().add(helpButton);
  }
  public String getImage(String index){
    return null;
  }
}
