package view;

import java.beans.PropertyChangeListener;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;


public class TabPaneView {

  private SettingView settingView;
  private HistoryView historyView;
  private MethodView methodView;
  private TabPane tabPane;
  private VariableView variableView;

  public TabPaneView(String language) {
    tabPane = new TabPane();
    tabPane.getStyleClass().add("tabPane");

    tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
      createSettingTab(language);
  }

  protected TabPane getTabPane() {
    return tabPane;
  }

  protected void addChangeHistoryListener(PropertyChangeListener newListener) {
    historyView.addChangeListener(newListener);
  }

  protected void addChangeSettingsListener(PropertyChangeListener newListener) {
    settingView.addChangeListener(newListener);
  }

  private void createSettingTab(String language) {
    settingView = new SettingView(language);
    tabPane.getTabs().add(settingView.getTab());
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
  public String getLanguage(){
    return settingView.getLanguage();
  }

}
