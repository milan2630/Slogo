package view;

import java.beans.PropertyChangeEvent;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SettingView {
    private static ResourceBundle resourceBundle;
    private static ResourceBundle imageBundle;
    private static ResourceBundle languageBundle;
    private String language;
    private Tab myTab;
    private List<PropertyChangeListener> listener;
    private String penColorData;
    private String backgroundColorData;
    private static final String PEN_COLOR = "Pen Color";
    private static final String BACKGROUND_COLOR = "Background Color";
    private static final String PREFIX = "resources/ui/";
    private static final String IMAGE_PATH = "TurtleImages";
    private static final String LANGUAGE_PATH = "/resources/languages";
  private static final double PADDING = 5;

    public SettingView(String language){
        //File folder = new File(LANGUAGE_PATH);
        //File[] listOfFiles = folder.listFiles();
        //System.out.println(listOfFiles);
        this.language = language;
        resourceBundle = ResourceBundle
                .getBundle(PREFIX + language);
        imageBundle = ResourceBundle.getBundle(PREFIX+IMAGE_PATH);
        //languageBundle = ResourceBundle.getBundle(PREFIX+IMAGE_PATH);
        myTab = new Tab(resourceBundle.getString("SettingTab"));
        listener = new ArrayList<>();
        setupTab();

    }
    public Tab getTab() { return myTab;}

    private void setupTab() {
        VBox vbox = new VBox();
        //Combo Boc for Selecting Image
        ComboBox<String> setTurtleImage = new ComboBox<>();
        setTurtleImage.setPromptText(resourceBundle.getString("LoadImage"));
        ObservableList<String> images = FXCollections.observableList(new ArrayList<>(imageBundle.keySet()));
        setTurtleImage.itemsProperty().bind(new SimpleObjectProperty<>(images));
        setTurtleImage.setOnAction(e-> saveFile(setTurtleImage.valueProperty().get()));

        //Color Picker for Background and Pen Color
        ColorPicker penColor = new ColorPicker();
        ColorPicker backgroundColor = new ColorPicker();
        penColor.setOnAction(e->setPenColor(penColor.getValue()));
        backgroundColor.setOnAction(e->setBackgroundColor(backgroundColor.getValue()));
        HBox penColorBox = getColorPickerBox("Pen Color: ", penColor);
        HBox backgroundColorBox= getColorPickerBox("Background Color: ", backgroundColor);

        //Select Language
        //ComboBox<String> languageBox = new ComboBox<>();
        //languageBox.setPromptText(resourceBundle.getString("SelectLanguage"));
        //ObservableList<String> languages = FXCollections.observableList(new ArrayList<>(languageBundle.keySet()));
        //languageBox.itemsProperty().bind(new SimpleObjectProperty<>(languages));
        //languageBox.setOnAction(e-> changeLanguage(languageBox.valueProperty().get()));

        vbox.getChildren().addAll(setTurtleImage, penColorBox, backgroundColorBox);
        vbox.setSpacing(10.0);
        myTab.setContent(vbox);
    }

    private void changeLanguage(String s) {
        System.out.println(s);
    }

    private void setBackgroundColor(Color value) {
      notifyListeners(BACKGROUND_COLOR, backgroundColorData, backgroundColorData = value.toString());
      System.out.println("Background Color: "+value);
    }

    private void setPenColor(Color value) {
      notifyListeners(PEN_COLOR, penColorData, penColorData = value.toString());
      System.out.println("Pen Color: "+value);
    }

    private void saveFile(String str) {
        System.out.println(imageBundle.getString(str));
    }

    private void showMessage (Alert.AlertType type, String message) {
        new Alert(type, message).showAndWait();
    }

    private HBox getColorPickerBox(String str, ColorPicker colorPicker) {
        HBox hbox = new HBox();
        Text text = new Text(str);
        colorPicker.setPromptText("Enter Color: ");
        hbox.getChildren().addAll(text, colorPicker);
        hbox.setSpacing(10.0);
        return hbox;
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

    private Button createButton( String text) {
        Button button = new Button();
        button.setText(text);
        return button;
    }


}
