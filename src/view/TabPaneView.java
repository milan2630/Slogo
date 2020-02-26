package view;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import slogo.UserDefinedInstructionCommand;
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

    createHistoryTab(language);
    createVariableTab(language);
    createSettingTab(language);
    createMethodTab(language);

    tabPane.getTabs().addAll(historyView.getTab(), methodView.getTab(), settingView.getTab(),
        variableView.getTab());
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
  }

  private void createMethodTab(String language) {
    methodList = FXCollections.observableMap(new HashMap<>());
    methodView = new MethodView(language, methodList);
  }

  private void createVariableTab(String language) {
    variableList = FXCollections.observableList(new ArrayList<>());
    variableView = new VariableView(language, FXCollections.observableList(variableList));
  }

  private void createHistoryTab(String language) {
    historyList = FXCollections.observableList(new ArrayList<>());
    historyView = new HistoryView(language, historyList);
  }


}
