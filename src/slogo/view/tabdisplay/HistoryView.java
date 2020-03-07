package slogo.view.tabdisplay;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import slogo.Model.Parsing.LanguageHandler;
import slogo.ReflectionException;
import slogo.Controller.Actions;

import java.lang.reflect.Method;
import java.util.*;

public class HistoryView {

  private ResourceBundle resourceBundle;
  private ResourceBundle layoutBundle;
  private ResourceBundle methodBundle;
  private static final String PREFIX = "resources/UI/";
  private static final String RESOURCES_LAYOUT = PREFIX + "Layouts";
  private static final String RESOURCES_METHODS = PREFIX + "ReflectionMethods";
  private LanguageHandler languageHandler;
  private Tab myTab;
  private ObservableList<String> history;
  private ListView<String> list;
  private Actions actions;

  public HistoryView(LanguageHandler language, ObservableList<String> historyList,
      Actions actions) {
    languageHandler = language;
    resourceBundle = ResourceBundle.getBundle(PREFIX + language.getLanguage());
    layoutBundle = ResourceBundle.getBundle((RESOURCES_LAYOUT));
    methodBundle = ResourceBundle.getBundle(RESOURCES_METHODS);
    myTab = new Tab(resourceBundle.getString("HistoryTab"));
    history = historyList;
    list = new ListView<String>();
    this.actions = actions;
    setupTab();
  }

  public Tab getTab() {
    return myTab;
  }

  private void setupTab() {
    VBox vbox = new VBox();
    vbox.setAlignment(Pos.CENTER);
    vbox.setSpacing(20);
    list.itemsProperty().bind(new SimpleObjectProperty<>(history));
    list.getSelectionModel().selectedItemProperty().addListener(e -> print());
    HBox buttons = createButtons();
    vbox.getChildren().addAll(buttons, list);
    myTab.setContent(vbox);
  }

  private HBox createButtons() {
    HBox hbox = new HBox();
    hbox.setSpacing(10);
    hbox.setAlignment(Pos.CENTER);
    String[] buttons = layoutBundle.getString("HistoryView").split(",");
    for (String b : buttons) {
      hbox.getChildren().add(createButton(b));
    }
    return hbox;
  }

  private Node createButton(String b) {
    Button button = new Button();
    button.setText(resourceBundle.getString(b));
    try {
      Method m = this.getClass().getDeclaredMethod(methodBundle.getString(b));
      m.invoke(this);
    } catch (Exception e) {
      throw new ReflectionException("InvalidMethod", methodBundle.getString(b));
    }
    return button;
  }

  private void print() {
    actions.handleHistoryVariable(list.getSelectionModel().getSelectedItem());
  }

  private void empty() {
    if (!history.isEmpty()) {
      history.clear();
    }
  }

  public void setLanguage(String newLanguage) {
    for (int i = 0; i < history.size(); i++) {
      String oldString = history.get(i);
      String newString = languageHandler.translateString(oldString, newLanguage);
      history.set(i, newString);
    }
  }
}