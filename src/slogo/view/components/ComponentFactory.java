package slogo.view.components;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import slogo.Model.Parsing.LanguageHandler;
import slogo.ReflectionException;
import slogo.Controller.Actions;

public class ComponentFactory {

  private static final String RESOURCES_CLASS_PATH = "resources/UI/ReflectionClass";
  private static final ResourceBundle promptBundle = ResourceBundle.getBundle(RESOURCES_CLASS_PATH);
  private static final String CLASS_PATH = ComponentFactory.class.getPackageName() + ".";
  private LanguageHandler languageHandler;
  public ComponentFactory(LanguageHandler languageHandler){
    this.languageHandler = languageHandler;
  }
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
