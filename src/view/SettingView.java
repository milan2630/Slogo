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
  private static ResourceBundle imageBundle;

  private List<PropertyChangeListener> listener;
  private String penColorData;
  private String backgroundColorData;
  private String turtleImage;
  private static final String PEN_COLOR = "Pen Color";
  private static final String BACKGROUND_COLOR = "Background Color";
  private static final String TURTLE_IMAGE = "TurtleImage";
  private static final String PREFIX = "resources/ui/";
  private static final String IMAGE_PATH = "TurtleImages";
  private static final String HELP_IMAGES_PATH = "resources";
  private final int IMAGE_HEIGHT = 283;
  private final int IMAGE_WIDTH = 500;
  private LanguageDropdown languageDropdown;

  public SettingView(String language) {
    super("SettingTab");
    resourceBundle = ResourceBundle
        .getBundle(PREFIX + language);
    imageBundle = ResourceBundle.getBundle(PREFIX + IMAGE_PATH);
    listener = new ArrayList<>();
    setupTab();
  }

  private void setupTab() {
    VBox vbox = new VBox();
    vbox.setAlignment(Pos.CENTER);
    vbox.setSpacing(10);
    //Combo Boc for Selecting Image
    ComboBox<String> setTurtleImage = new ComboBox<>();
    setTurtleImage.setPromptText(resourceBundle.getString("LoadImage"));
    ObservableList<String> images = getTurtleImages();
    setTurtleImage.itemsProperty().bind(new SimpleObjectProperty<>(images));
    setTurtleImage.setOnAction(e -> saveFile(setTurtleImage.valueProperty().get()));

    //Color Picker for Background and Pen Color
    ColorPicker penColor = new ColorPicker();
    ColorPicker backgroundColor = new ColorPicker();
    penColor.setOnAction(e -> setPenColor(penColor.getValue()));
    backgroundColor.setOnAction(e -> setBackgroundColor(backgroundColor.getValue()));
    HBox penColorBox = getColorPickerBox(resourceBundle.getString("PenColor"), penColor);
    HBox backgroundColorBox = getColorPickerBox(resourceBundle.getString("BackgroundColor"),
        backgroundColor);

    languageDropdown = new LanguageDropdown(resourceBundle.getString("SelectLanguage"));

    Button help = createButton(resourceBundle.getString("HelpButton"));
    help.setOnAction(e -> handleHelpScreen());
    vbox.getChildren()
        .addAll(setTurtleImage, penColorBox, backgroundColorBox, languageDropdown, help);
    vbox.setSpacing(10.0);
    setContent(vbox);
  }

  private ObservableList<String> getTurtleImages() {
    File directoryPath = new File(HELP_IMAGES_PATH);
    List<String> images = new ArrayList<>();
    for (String s : directoryPath.list()) {
      if (s.contains("turtle")) {
        images.add(s);
      }
    }
    Collections.sort(images);
    return FXCollections.observableList(images);
  }

  private void handleHelpScreen() {
    final Stage dialog = new Stage();
    dialog.initModality(Modality.NONE);
    ScrollPane scrollPane = new ScrollPane();
    VBox helpImages = createHelpScreen();
    scrollPane.setContent(helpImages);
    Scene dialogScene = new Scene(scrollPane);
    dialog.setScene(dialogScene);
    dialog.setTitle(resourceBundle.getString("HelpWindow"));
    dialog.show();
  }

  private VBox createHelpScreen() {
    VBox vbox = new VBox();
    List<String> images = getHelpImages();
    for (String image : images) {
      Image i = new Image(this.getClass().getClassLoader().getResourceAsStream(image));
      ImageView im = new ImageView(i);
      im.setFitHeight(IMAGE_HEIGHT);
      im.setFitWidth(IMAGE_WIDTH);
      vbox.getChildren().add(im);
    }
    return vbox;
  }

  private List<String> getHelpImages() {
    File directoryPath = new File(HELP_IMAGES_PATH);
    List<String> images = new ArrayList<>();
    for (String s : directoryPath.list()) {
      if (s.contains("Help")) {
        images.add(s);
      }
    }
    Collections.sort(images);
    return images;
  }


  private void setBackgroundColor(Color value) {
    notifyListeners(BACKGROUND_COLOR, backgroundColorData, backgroundColorData = value.toString());
  }

  private void setPenColor(Color value) {
    notifyListeners(PEN_COLOR, penColorData, penColorData = value.toString());
  }

  private void saveFile(String str) {
    notifyListeners(TURTLE_IMAGE, turtleImage, turtleImage = str);
  }

  private void showMessage(Alert.AlertType type, String message) {
    new Alert(type, message).showAndWait();
  }

  private HBox getColorPickerBox(String str, ColorPicker colorPicker) {
    HBox hbox = new HBox();
    hbox.setPadding((new Insets(10, 5, 10, 5)));
    Text text = new Text(str);
    hbox.getChildren().addAll(text, colorPicker);
    hbox.setSpacing(10.0);
    text.getStyleClass().add("settings-text");
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

  private Button createButton(String text) {
    Button button = new Button();
    button.setText(text);
    return button;
  }

  protected String getLanguage() {
    return languageDropdown.getLanguage();
  }


}
