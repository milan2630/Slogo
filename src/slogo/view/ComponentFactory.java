package slogo.view;

import java.lang.reflect.Method;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import slogo.ReflectionException;

public class ComponentFactory {

  private static final String RESOURCES_UI_PATH = "resources/UI/";
  private static final String RESOURCES_COMMAND_PATH = "resources/Languages/";
  public static final int PADDING = 10;
  private static String PROMPTS_PATH;
  private static final String METHODS_PATH = RESOURCES_UI_PATH + "ReflectionMethods";
  private Actions actions;
  private static ResourceBundle promptBundle;
  private ResourceBundle commandBundle;
  private ResourceBundle methodBundle;

  private static final String DEFAULT_RESOURCE_PATH = "resources/states/Default";
  private ResourceBundle defaultsResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PATH);

  protected ComponentFactory(String language, String key, Actions actions) {
    this.actions = actions;
    methodBundle = ResourceBundle.getBundle(METHODS_PATH);
    promptBundle = ResourceBundle.getBundle(RESOURCES_UI_PATH + language);
    commandBundle = ResourceBundle.getBundle(RESOURCES_COMMAND_PATH + language);
  }

  protected HBox createLabel(String key) {
    HBox hbox = new HBox();
    Text text = new Text(promptBundle.getString(key));
    hbox.getChildren().add(text);
    hbox.setPadding((new Insets(PADDING, PADDING, PADDING, PADDING)));
    hbox.setAlignment(Pos.CENTER);
    hbox.setSpacing(PADDING);
    text.getStyleClass().add("settings-text");
    return hbox;
  }

  protected String getCommandByKey(String key, int type) {
    //Splitting command by | ex: forward|fd
    String raw = commandBundle.getString(key);
    return raw.split("\\|")[type];
  }

  protected void handleAction(String value, String key) {
    try {
      Method m = actions.getClass().getDeclaredMethod(methodBundle.getString(key), String.class);
      m.invoke(actions, value);
    } catch (Exception e) {
      throw new ReflectionException("InvalidMethod", methodBundle.getString(key));
    }
  }

  protected String getDefaultFromKey(String key) {
    return defaultsResources.getString(key);
  }

}
