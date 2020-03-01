package view;

import java.beans.PropertyChangeEvent;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class SettingView extends Tab {

  private static ResourceBundle resourceBundle;

  private List<PropertyChangeListener> listener;
  private String penColorData;
  private String backgroundColorData;
  private String turtleImage;

  private static final String PEN_COLOR = "Pen Color";
  private static final String BACKGROUND_COLOR = "Background Color";
  private static final String TURTLE_IMAGE = "TurtleImage";
  private static final String PREFIX = "resources/ui/";

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
    vbox.setAlignment(Pos.CENTER);
    vbox.setSpacing(10);

    turtleImageDropdown = new TurtleImageDropdown(resourceBundle.getString("LoadImage"));
    turtleImageDropdown.setOnAction(e -> saveFile());

    languageDropdown = new LanguageDropdown(resourceBundle.getString("SelectLanguage"));

    penColorPicker = new ColorPickerBox(resourceBundle.getString("PenColor"));
    penColorPicker.setOnAction(e -> setPenColor());

    backgroundColorPicker = new ColorPickerBox(resourceBundle.getString("BackgroundColor"));
    backgroundColorPicker.setOnAction(e -> setBackgroundColor());

    HelpButton helpButton = new HelpButton(resourceBundle);

    vbox.getChildren()
        .addAll(turtleImageDropdown, penColorPicker, backgroundColorPicker, languageDropdown, helpButton);
    setContent(vbox);
  }

  private void saveFile() {
    String filename = turtleImageDropdown.valueProperty().get();
    notifyListeners(TURTLE_IMAGE, turtleImage, turtleImage = "turtles/" + filename);
  }

  private void setBackgroundColor() {
    Color value = backgroundColorPicker.getColor();
    notifyListeners(BACKGROUND_COLOR, backgroundColorData, backgroundColorData = value.toString());
  }

  private void setPenColor() {
    Color value = penColorPicker.getColor();
    notifyListeners(PEN_COLOR, penColorData, penColorData = value.toString());
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

  protected String getLanguage() {
    return languageDropdown.getLanguage();
  }

}
