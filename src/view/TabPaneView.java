package view;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import slogo.Commands.UserDefinedInstructionCommand;
import slogo.Variable;

public class TabPaneView {

  private SettingView settingView;
  private HistoryView historyView;
  private MethodView methodView;
  private TabPane tabPane;
  private VariableView variableView;
  private ObservableMap<String, UserDefinedInstructionCommand> methodList;
  private ObservableList<Variable> variableList;
  private ObservableList<String> historyList;

  public TabPaneView(String language) {
    tabPane = new TabPane();
    tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

//    createHistoryTab(language);
//    createVariableTab(language);
//    createMethodTab(language);
      createSettingTab(language);
  }

  protected ObservableMap<String, UserDefinedInstructionCommand> getMethodList() {
    return methodList;
  }

  protected void setMethodList(
      ObservableMap<String, UserDefinedInstructionCommand> methodList) {
    this.methodList = methodList;
  }

  protected ObservableList<Variable> getVariableList() {
    return variableList;
  }

  protected void setVariableList(ObservableList<Variable> variableList) {
    this.variableList = variableList;
  }

  protected ObservableList<String> getHistoryList() {
    return historyList;
  }

  protected void setHistoryList(ObservableList<String> historyList) {
    this.historyList = historyList;
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
    variableView = new VariableView(language, FXCollections.observableList(variableList));
    tabPane.getTabs().add(variableView.getTab());
  }

  public void createHistoryTab(String language, ObservableList historyList) {
    historyView = new HistoryView(language, historyList);
    tabPane.getTabs().add(historyView.getTab());
  }


}
