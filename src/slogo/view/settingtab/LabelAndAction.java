package slogo.view.settingtab;

import java.lang.reflect.Method;
import java.util.ResourceBundle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import slogo.view.Actions;
import slogo.ReflectionException;

public abstract class LabelAndAction extends HBox {
  private static final String UI_PATH = "resources/UI/";
  private final static String LANGUAGE_PATH = "resources/Languages/";

  private static ResourceBundle uiResources;
  private ResourceBundle commandBundle;
  private static final String DEFAULT_RESOURCE_PATH = "resources/states/Default";
  private ResourceBundle defaultsResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PATH);

  protected LabelAndAction(String language, String methodName) {
    uiResources = ResourceBundle.getBundle(UI_PATH + language);
    commandBundle = ResourceBundle.getBundle(LANGUAGE_PATH + language);
    Text text = new Text(uiResources.getString(methodName));
    getChildren().add(text);
    setPadding((new Insets(10, 5, 10, 5)));
    setAlignment(Pos.CENTER);
    setSpacing(10.0);
    text.getStyleClass().add("settings-text");
  }

  protected String getCommandByKey(String key, int type){
    //Splitting command by | ex: forward|fd
    String raw = commandBundle.getString(key);
    return raw.split("\\|")[type];
  }

  protected void handleAction(String value, String methodName, Actions target) {
    try {
      Method m = target.getClass().getDeclaredMethod(methodName, String.class);
      m.invoke(target, value);
    } catch (Exception e) {
      e.printStackTrace();
      throw new ReflectionException("InvalidMethod", methodName);
    }
  }

  protected String getDefaultFromKey(String key){
    return defaultsResources.getString(key);
  }
}
