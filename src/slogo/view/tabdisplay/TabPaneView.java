package slogo.view.tabdisplay;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.paint.Color;
import slogo.Model.Parsing.LanguageHandler;
import slogo.Model.TurtleModel.ImmutableTurtle;
import slogo.view.Actions;
import slogo.view.settingtab.SettingView;


public class TabPaneView {

  private SettingView settingView;
  private HistoryView historyView;
  private MethodView methodView;
  private TurtleTabView turtleTabView;
  private TabPane tabPane;
  private VariableView variableView;
  private PaletteView paletteView;
  private static ResourceBundle uiResources;
  private Actions actions;
  private static final String PREFIX = "resources/UI/";

  public TabPaneView(LanguageHandler language, Actions actions) {
    uiResources = ResourceBundle.getBundle(PREFIX + language.getLanguage());
    tabPane = new TabPane();
    tabPane.getStyleClass().add("tabPane");

    //tabPane.setTabClosingPolicy(TabClosingPolicy.);
    createSettingTab(language, actions);
    createTurtleTab(language, actions);
    this.actions = actions;
  }

  public TabPane getTabPane() {
    return tabPane;
  }


  public void createPaletteTab(LanguageHandler language, ObservableList list) {
    paletteView = new PaletteView(language,list, actions);
    Tab tab = new Tab(uiResources.getString("PaletteTab"));
    tab.setContent(paletteView);
    tabPane.getTabs().add(tab);
  }

  private void createSettingTab(LanguageHandler language, Actions actions) {
    settingView = new SettingView(language, actions);
    Tab tab = new Tab(uiResources.getString("SettingTab"));
    tab.setContent(settingView);
    tabPane.getTabs().add(tab);
  }

  private void createTurtleTab(LanguageHandler language, Actions actions) {
    turtleTabView = new TurtleTabView(language, actions);
    Tab tab = new Tab(uiResources.getString("TurtleTab"));
    tabPane.getTabs().add(turtleTabView.getTab());
  }

  public void createMethodTab(LanguageHandler language, ObservableMap methodList) {
    methodView = new MethodView(language, methodList, actions);
    tabPane.getTabs().add(methodView.getTab());
  }

  public void createVariableTab(LanguageHandler language, ObservableList variableList) {
    variableView = new VariableView(language, variableList);
    tabPane.getTabs().add(variableView.getTab());
  }

  public void createHistoryTab(LanguageHandler language, ObservableList historyList) {
    historyView = new HistoryView(language, historyList, actions);
    tabPane.getTabs().add(historyView.getTab());
    Observable j = FXCollections.observableList(List.of(" ", " "));

  }

  public Color getColor(int index){
    return paletteView.getColor(index);
  }

  public List getImmutablePaletteList(){
    return paletteView.getImmutableList();
  }

  public void updateTurtleTab(Map<Double, List<ImmutableTurtle>> turtleList){
    turtleTabView.setTable(turtleList);
  }

    public void setHistoryLanguage(String newLanguage) {
      historyView.setLanguage(newLanguage);
    }

}
