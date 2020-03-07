package slogo.Controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Actions {
  private final static String ACTIONS_RESOURCE_PATH = "resources/UI/Actions";
  private ResourceBundle actionsBundle = ResourceBundle.getBundle(ACTIONS_RESOURCE_PATH);

  private String data;
  private List<PropertyChangeListener> listeners = new ArrayList<>();

  public void handleCommand(String value) {
    notifyListeners(actionsBundle.getString("Command"), this.data, this.data = value);
  }

  public void handleClear(String result) {
    notifyListeners(actionsBundle.getString("TerminalInput"), this.data, this.data = "");
  }

  public void handleReset(String value) {
    notifyListeners(actionsBundle.getString("Reset"), this.data, value);
  }

  public void handleTurtleImage(String value) {
    notifyListeners(actionsBundle.getString("TurtleImage"), this.data, this.data = value);
  }

  public void handleLanguage(String value) {
    notifyListeners(actionsBundle.getString("Language"), this.data, this.data = value);
  }

  public void handleBackgroundColor(String value) {
    notifyListeners(actionsBundle.getString("BackgroundColor"), this.data, this.data = value);
  }

  public void handleHistoryVariable(String value) {
    notifyListeners(actionsBundle.getString("TerminalInput"), this.data, this.data = value);
  }

  public void handleTurtleState(String id, String showing) {
    PropertyChangeEvent e = new PropertyChangeEvent(this, actionsBundle.getString("TurtleState"), this.data,
        this.data = showing);
    e.setPropagationId(id);
    notifyListeners(actionsBundle.getString("TurtleState"), e);
  }

  public void handleMethodDisplay(String value) {
    notifyListeners(actionsBundle.getString("TerminalInput"), this.data, this.data = value);
  }

  private void notifyListeners(String property, String oldValue, String newValue) {
    for (PropertyChangeListener name : listeners) {
      name.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
    }
  }

  private void notifyListeners(String property, PropertyChangeEvent e) {
    for (PropertyChangeListener name : listeners) {
      name.propertyChange(e);
    }
  }

  public void addChangeListener(PropertyChangeListener newListener) {
    listeners.add(newListener);
  }


}
