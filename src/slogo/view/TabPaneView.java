package slogo.view;

import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import slogo.view.settingtab.SettingView;


public class TabPaneView {

  private SettingView settingView;
  private HistoryView historyView;
  private MethodView methodView;
  private TabPane tabPane;
  private VariableView variableView;
  private static ResourceBundle uiResources;
  private static final String PREFIX = "resources/UI/";

  public TabPaneView(String language, Actions actions) {
    uiResources = ResourceBundle.getBundle(PREFIX + language);
    tabPane = new TabPane();
    tabPane.getStyleClass().add("tabPane");

    tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
      createSettingTab(language, actions);
  }

  protected TabPane getTabPane() {
    return tabPane;
  }

  private void createSettingTab(String language, Actions actions) {
    settingView = new SettingView(language, actions);
    Tab settings = new Tab(uiResources.getString("SettingTab"));
    settings.setContent(settingView);
    tabPane.getTabs().add(settings);
  }

  public void createMethodTab(String language, ObservableMap methodList) {
    methodView = new MethodView(language, methodList);
    tabPane.getTabs().add(methodView.getTab());
  }

  public void createVariableTab(String language, ObservableList variableList) {
    variableView = new VariableView(language, variableList);
    tabPane.getTabs().add(variableView.getTab());
  }

  public void createHistoryTab(String language, ObservableList historyList) {
    historyView = new HistoryView(language, historyList);
    tabPane.getTabs().add(historyView.getTab());
  }

}
