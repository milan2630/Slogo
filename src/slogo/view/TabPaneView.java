package slogo.view;

import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import slogo.Model.TurtleModel.ImmutableTurtle;
import slogo.view.settingtab.SettingView;
import slogo.view.settingtab.TurtleMover;


public class TabPaneView {

  private SettingView settingView;
  private HistoryView historyView;
  private MethodView methodView;
  private TurtleTabView turtleTabView;
  private TabPane tabPane;
  private VariableView variableView;
  private static ResourceBundle uiResources;
  private Actions actions;
  private static final String PREFIX = "resources/UI/";

  public TabPaneView(String language, Actions actions) {
    uiResources = ResourceBundle.getBundle(PREFIX + language);
    tabPane = new TabPane();
    tabPane.getStyleClass().add("tabPane");

    tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
    createSettingTab(language, actions);
    createTurtleTab(language, actions);
    this.actions = actions;
  }

  protected TabPane getTabPane() {
    return tabPane;
  }


  public void createPaletteTab(String language, ObservableList list) {
    PaletteView paletteView = new PaletteView(language,list);
    Tab tab = new Tab(uiResources.getString("PaletteTab"));
    tab.setContent(paletteView);
    tabPane.getTabs().add(tab);
  }

  private void createSettingTab(String language, Actions actions) {
    settingView = new SettingView(language, actions);
    Tab tab = new Tab(uiResources.getString("SettingTab"));
    tab.setContent(settingView);
    tabPane.getTabs().add(tab);
  }

  private void createTurtleTab(String language, Actions actions) {
    turtleTabView = new TurtleTabView(language, actions);
    Tab tab = new Tab(uiResources.getString("TurtleTab"));
    tabPane.getTabs().add(turtleTabView.getTab());
  }

  public void createMethodTab(String language, ObservableMap methodList) {
    methodView = new MethodView(language, methodList, actions);
    tabPane.getTabs().add(methodView.getTab());
  }

  public void createVariableTab(String language, ObservableList variableList) {
    variableView = new VariableView(language, variableList);
    tabPane.getTabs().add(variableView.getTab());
  }

  public void createHistoryTab(String language, ObservableList historyList) {
    historyView = new HistoryView(language, historyList, actions);
    tabPane.getTabs().add(historyView.getTab());
  }

  public void updateTurtleTab(ImmutableTurtle turtle){
    turtleTabView.setTable(turtle);
  }

    public void setHistoryLanguage(String newLanguage) {
      historyView.setLanguage(newLanguage);
    }
}
