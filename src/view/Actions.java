package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextArea;

public class Actions {

  private static final String RUN = "Run";
  private static final String RESET = "Reset";
  private String data;
  private List<PropertyChangeListener> listeners = new ArrayList<>();

  protected void handleRun(TextArea input) {
    if (input.getText().length() > 0) {
      notifyListeners(RUN, this.data, this.data = input.getText());
    }
    System.out.println(listeners);
  }

  protected void handleClear(TextArea input) {
    input.clear();
  }

  protected void handleReset(TextArea input) {
    notifyListeners(RESET, this.data, this.data = input.getText());
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
