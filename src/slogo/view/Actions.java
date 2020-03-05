package slogo.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextArea;

public class Actions {

  private static final String RUN = "Run";
  private static final String RESET = "Reset";
  private static final String PEN_COLOR = "Pen Color";
  private static final String THICKNESS = "Thickness";
  private static final String BACKGROUND_COLOR = "Background Color";
  private static final String TURTLE_IMAGE = "Turtle Image";
  private static final String LANGUAGE = "Language";
  private static final String HISTORY_VARIABLE = "HistoryVariable";
  private static final String PEN_STATUS = "Pen Status";
  private static final String MOVE_TURTLE = "Move Turtle";
  private static final String TURTLE_STATE = "Change Turtle State";
  private static final String METHOD_DISPLAy = "Method Display";

  private String data;
  private List<PropertyChangeListener> listeners = new ArrayList<>();

  public void handleRun(TextArea input) {
    if (input.getText().length() > 0) {
      notifyListeners(RUN, this.data, this.data = input.getText());
    }
  }

  public void handleClear(TextArea input) {
    input.clear();
  }

  public void handleReset(TextArea input) {
    notifyListeners(RESET, this.data, this.data = input.getText());
  }

  public void handleTurtleImage(String value) {
    notifyListeners(TURTLE_IMAGE, this.data, this.data = value);
  }

  public void handleLanguage(String value) {
    notifyListeners(LANGUAGE, this.data, this.data = value);
  }

  public void handleBackgroundColor(String value) {
    notifyListeners(BACKGROUND_COLOR, this.data, this.data = value);
  }

  public void handlePenColor(String value) {
    notifyListeners(PEN_COLOR, this.data, this.data = value);
  }

  public void handleThickness(String value) {
    notifyListeners(THICKNESS, this.data, this.data = value);
  }

  public void handlePenStatus(String value) {
    notifyListeners(RUN, this.data, this.data = value);
  }

  public void handleTurtleMovement(String command){
    notifyListeners(RUN, this.data, this.data = command);
  }

  public void handleHistoryVariable (String value) { notifyListeners(HISTORY_VARIABLE, this.data, this.data=value); }

  public void handleTurtleState(String id, String showing) {
    PropertyChangeEvent e = new PropertyChangeEvent(this, TURTLE_STATE, this.data, this.data = showing);
    e.setPropagationId(id);
    notifyListeners(TURTLE_STATE, e);
  }


  public void handleMethodDisplay(String value){ notifyListeners(METHOD_DISPLAy, this.data, this.data = value);}

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
