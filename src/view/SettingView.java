package view;

import java.beans.PropertyChangeEvent;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Tab;
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
    private String language;
    private Tab myTab;
    private List<PropertyChangeListener> listener;
    private String colorData;
    private static final double PADDING = 5;
    private static final String DATA_FILE_EXTENSION = "*.png";
    //private final static FileChooser FILE_CHOOSER = makeChooser(DATA_FILE_EXTENSION);

    public SettingView(String language){
        this.language = language;
        resourceBundle = ResourceBundle
                .getBundle("resources/ui/" + language);
        myTab = new Tab(resourceBundle.getString("SettingTab"));
        listener = new ArrayList<>();
        setupTab();
    }
    public Tab getTab() { return myTab;}

    private void setupTab() {
        VBox vbox = new VBox();
        Button setTurtleImage = createButton(resourceBundle.getString("LoadImage"));
        setTurtleImage.setOnAction(e-> saveFile());
        HBox penColor = getColorPicker("Pen Color: ");
        HBox backgroundColor= getColorPicker("Background Color: ");
        vbox.getChildren().addAll(setTurtleImage, penColor, backgroundColor);
        vbox.setSpacing(10.0);
        myTab.setContent(vbox);
    }

    private void saveFile() {
//        File dataFile = FILE_CHOOSER.showOpenDialog(primaryStage);
//        while (dataFile != null) {
//            try {
//                System.out.println(dataFile.getName());
//                // do something "interesting" with the resulting data
//                showMessage(AlertType.INFORMATION, "Unable to Load Image");
//            }
//            catch (RuntimeException e) {
//                // handle error of unexpected file format
//                showMessage(AlertType.ERROR, "Unable to Load Image");
//            }
//            dataFile = FILE_CHOOSER.showOpenDialog(primaryStage);
//        }
//
//        // nothing selected, so quit the application
//        Platform.exit();
    }

    private void showMessage (Alert.AlertType type, String message) {
        new Alert(type, message).showAndWait();
    }


    private HBox getColorPicker(String str) {
        HBox hbox = new HBox();
        Text text = new Text(str);
        ColorPicker penColor = new ColorPicker();
        //penColor.setValue(Color.CORAL);
        penColor.setPromptText("Enter Color: ");
        penColor.setOnAction(e->setColor(penColor.getValue()));
        hbox.getChildren().addAll(text, penColor);
        hbox.setSpacing(10.0);
        return hbox;
    }

    private void setColor(Color value) {
      //TODO separate out background and pen colors
      notifyListeners("Color", colorData, colorData = value.toString());
      System.out.println(value);
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

    private static FileChooser makeChooser (String extensionAccepted) {
        FileChooser result = new FileChooser();
        result.setTitle(resourceBundle.getString("LoadImage"));
        result.setInitialDirectory(new File(System.getProperty("user.dir")));
        result.getExtensionFilters().setAll(new ExtensionFilter("Text Files", extensionAccepted));
        return result;
    }
}
