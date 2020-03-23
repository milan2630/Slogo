package slogo.view.components;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import slogo.Model.Parsing.LanguageHandler;
import slogo.ReflectionException;
import slogo.Controller.Actions;

/**
 * @author jaidharosenblatt Uses the factory pattern and reflection to construct components. Is
 * dependent on Component, LanguageHandler, and Actions
 */
public class ComponentFactory {

  private static final String RESOURCES_CLASS_PATH = "resources/UI/ReflectionClass";
  private static final ResourceBundle promptBundle = ResourceBundle.getBundle(RESOURCES_CLASS_PATH);
  private static final String CLASS_PATH = ComponentFactory.class.getPackageName() + ".";
  private LanguageHandler languageHandler;

  /**
   * Constructs a new ComponentFactory with a given language (legacy and could be deleted)
   *
   * @param languageHandler
   */
  public ComponentFactory(LanguageHandler languageHandler) {
    this.languageHandler = languageHandler;
  }

  /**
   * Uses reflection to create a component based on a key
   *
   * @param language a LanguageHandler object that holds the language
   * @param key      the key uses to create the class
   * @param actions  an instance of the Actions class that contains the key mappings to methods
   * @return a child of component
   */
  public Component getComponent(LanguageHandler language, String key, Actions actions) {
    try {
      Class<?> clazz = Class.forName(CLASS_PATH + promptBundle.getString(key));
      Constructor<?> constructor = clazz
          .getDeclaredConstructor(LanguageHandler.class, String.class, Actions.class);
      Component component = (Component) constructor.newInstance(language, key, actions);
      return component;
    } catch (ClassNotFoundException e) {
      throw new ReflectionException("InvalidClass", key);
    } catch (NoSuchMethodException e) {
      throw new ReflectionException("InvalidMethod", key);
    } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
      throw new ReflectionException("InvalidInstantiation", key);
    }

  }

}
