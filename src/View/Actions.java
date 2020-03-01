package View;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextArea;

public class Actions {

  private static final String RUN = "Run";
  private static final String RESET = "Reset";
  private static final String PEN_COLOR = "Pen Color";
  private static final String BACKGROUND_COLOR = "Background Color";
  private static final String HISTORY_VARIABLE = "HistoryVariable";
  private static final String TURTLE_IMAGE = "TurtleImage";
  private static final String LANGUAGE = "Language";

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

  public void handlePenColor(String value){
    notifyListeners(PEN_COLOR, this.data, this.data = value);
  }


  private void notifyListeners(String property, String oldValue, String newValue) {
    for (PropertyChangeListener name : listeners) {
      name.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
    }
  }

  public void addChangeListener(PropertyChangeListener newListener) {
    listeners.add(newListener);
  }
}
