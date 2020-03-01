package view.SettingsTab;

import java.beans.PropertyChangeEvent;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.beans.PropertyChangeListener;
import java.util.*;

public class SettingView extends Tab {

  public static final String DEFAULT_TURTLE_IMAGE = "turtle.png";
  private static ResourceBundle resourceBundle;

  private List<PropertyChangeListener> listener;
  private String penColorData;
  private String backgroundColorData;
  private String turtleImage;

  private static final String PEN_COLOR = "Pen Color";
  private static final String BACKGROUND_COLOR = "Background Color";
  private static final String TURTLE_IMAGE = "TurtleImage";
  private static final String PREFIX = "resources/UI/";
  private static final String DEFAULT_PEN_COLOR = Color.NAVY.toString();
  private static final String DEFAULT_BACKGROUND_COLOR = Color.WHITE.toString();


  private LanguageDropdown languageDropdown;
  private TurtleImageDropdown turtleImageDropdown;
  private ColorPickerBox backgroundColorPicker;
  private ColorPickerBox penColorPicker;

  public SettingView(String language) {
    super("SettingTab");
    resourceBundle = ResourceBundle.getBundle(PREFIX + language);
    listener = new ArrayList<>();
    setupTab();
  }

  private void setupTab() {
    VBox vbox = new VBox();
    vbox.setAlignment(Pos.TOP_LEFT);
    vbox.setSpacing(10);

    turtleImageDropdown = new TurtleImageDropdown(resourceBundle.getString("LoadImage"));
    turtleImageDropdown.setOnAction(e -> saveFile());
    turtleImageDropdown.setValue(DEFAULT_TURTLE_IMAGE);

    languageDropdown = new LanguageDropdown(resourceBundle.getString("SelectLanguage"));

    penColorPicker = new ColorPickerBox(resourceBundle.getString("PenColor"));
    penColorPicker.setValue(DEFAULT_PEN_COLOR);
    penColorPicker.setOnAction(e -> setPenColor());

    backgroundColorPicker = new ColorPickerBox(resourceBundle.getString("BackgroundColor"));
    backgroundColorPicker.setOnAction(e -> setBackgroundColor());
    backgroundColorPicker.setValue(DEFAULT_BACKGROUND_COLOR);

    HelpButton helpButton = new HelpButton(resourceBundle);

    vbox.getChildren()
        .addAll(helpButton, turtleImageDropdown, penColorPicker, backgroundColorPicker, languageDropdown);
    setContent(vbox);
  }

  private void saveFile() {
    String filename = turtleImageDropdown.setValue();
    notifyListeners(TURTLE_IMAGE, turtleImage, turtleImage = "Turtles/" + filename);
  }

  private void setBackgroundColor() {
    String value = backgroundColorPicker.getValue();
    notifyListeners(BACKGROUND_COLOR, backgroundColorData, backgroundColorData = value);
  }

  private void setPenColor() {
    String value = penColorPicker.getValue();
    notifyListeners(PEN_COLOR, penColorData, penColorData = value);
  }

  private void notifyListeners(String property, String oldValue, String newValue) {
    for (PropertyChangeListener name : listener) {
      name.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
    }
  }

  /**
   * Implements Observer Design pattern
   *
   * @param newListener
   */
  public void addChangeListener(PropertyChangeListener newListener) {
    listener.add(newListener);
  }

  public String getLanguage() {
    return languageDropdown.getValue();
  }

}
