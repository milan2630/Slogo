package slogo.view.settingtab;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.*;
import javafx.scene.text.Text;
import slogo.view.Actions;
import slogo.view.components.Component;
import slogo.view.components.ComponentFactory;


public class SettingView extends VBox {

  private static final String PREFIX = "resources/UI/";
  private static final String RESOURCES_LAYOUTS = PREFIX + "Layouts";
  public static final int PADDING = 2;
  private static ResourceBundle layouts;

  public SettingView(String language, Actions actions) {
    layouts = ResourceBundle.getBundle(RESOURCES_LAYOUTS);

    setupTab(language, actions);
  }

  protected HBox createLabel(String prompt) {
    HBox hbox = new HBox();
    Text text = new Text(prompt);
    hbox.getChildren().add(text);
    hbox.setPadding((new Insets(PADDING, PADDING, PADDING, PADDING)));
    hbox.setAlignment(Pos.CENTER);
    hbox.setSpacing(PADDING);
    text.getStyleClass().add("settings-text");
    return hbox;
  }

  private void setupTab(String language, Actions actions) {
    List<String> buttonList = Arrays.asList(layouts.getString("SettingView").split(","));
    setSpacing(PADDING);
    ComponentFactory componentFactory = new ComponentFactory();

    for (String key : buttonList) {
      Component component = componentFactory.getComponent(language, key, actions);
      getChildren().add(component);
    }
  }
}
