package slogo.view.components;

import java.lang.reflect.Method;
import java.util.ResourceBundle;
import javafx.scene.layout.Pane;
import slogo.Model.Parsing.LanguageHandler;
import slogo.ReflectionException;
import slogo.Controller.Actions;

/**
 * @author jaidharosenblatt An abstract class used by all of the components in gui. This class also
 * holds protected getter methods for accessing property files for commands, method names, and
 * default values
 */
public abstract class Component extends Pane {

  private static final String RESOURCES_UI_PATH = "resources/UI/";
  private static final String RESOURCES_COMMAND_PATH = "resources/Languages/";
  private static final String METHODS_PATH = RESOURCES_UI_PATH + "ReflectionMethods";
  private static final String DEFAULT_RESOURCE_PATH = RESOURCES_UI_PATH + "Default";

  private static ResourceBundle promptBundle;
  private ResourceBundle commandBundle;
  private ResourceBundle methodBundle;
  private ResourceBundle defaultsResources;

  private String key;
  private Actions actions;

  /**
   * Constructs a component and initializes the resource bundles
   *
   * @param language the language of the gui
   * @param key      the unique identifier for this component in property files
   * @param actions  the possible methods to map actions to
   */
  protected Component(LanguageHandler language, String key, Actions actions) {
    this.actions = actions;
    this.key = key;
    defaultsResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PATH);
    methodBundle = ResourceBundle.getBundle(METHODS_PATH);
    promptBundle = ResourceBundle.getBundle(RESOURCES_UI_PATH + language.getLanguage());
    commandBundle = ResourceBundle.getBundle(RESOURCES_COMMAND_PATH + language.getLanguage());
  }

  /**
   * Uses a resource bundle to get a command string by a key name
   *
   * @param key  the type of command
   * @param type 0 for long string "forward", 1 for short string "fd"
   * @return a command as a string
   */
  protected String getCommandByKey(String key, int type) {
    //Splitting command by | ex: forward|fd
    String raw = commandBundle.getString(key);
    return raw.split("\\|")[type];
  }

  /**
   * Uses reflection to get the method associated with this component and invokes it with a given
   * value.
   *
   * @param value a string to pass into the method
   */
  protected void handleAction(String value) {
    try {
      Method m = actions.getClass().getDeclaredMethod(methodBundle.getString(key), String.class);
      m.invoke(actions, value);
    } catch (Exception e) {
      throw new ReflectionException("InvalidMethod", methodBundle.getString(key));
    }
  }

  /**
   * Uses a resource bundle to get a default value by a key name
   *
   * @param key the key name to get the default for
   * @return the key's value from a properties file
   */
  protected String getDefaultFromKey(String key) {
    return defaultsResources.getString(key);
  }

  /**
   * Uses a resource bundle to get a prompt value by a key name
   *
   * @param key the key name to get the button prompt for
   * @return the prompt value from a properties file
   */
  public String getPromptFromKey(String key) {
    return promptBundle.getString(key);
  }

}
