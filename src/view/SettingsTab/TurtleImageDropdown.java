package view.SettingsTab;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class TurtleImageDropdown extends HBox {

  private static final String TURTLE_IMAGES_PATH = "resources/Turtles";

  private ComboBox<String> comboBox;

  protected TurtleImageDropdown(String prompt) {
    ObservableList<String> images = getTurtleImages();
    comboBox = new ComboBox<>();
    comboBox.itemsProperty().bind(new SimpleObjectProperty<>(images));
    Text text = new Text(prompt);
    getChildren().addAll(text, comboBox);

    setPadding((new Insets(10, 5, 10, 5)));
    setAlignment(Pos.CENTER);
    setSpacing(10.0);
    text.getStyleClass().add("settings-text");
  }

  protected void setOnAction(EventHandler<ActionEvent> value) {
    comboBox.setOnAction(value);
  }

  protected String getImageFilename() {
    return comboBox.getValue();
  }

  protected void setImageFilename(String filename){
    comboBox.setValue(filename);
  }

  private ObservableList<String> getTurtleImages() {
    File directoryPath = new File(TURTLE_IMAGES_PATH);
    List<String> images = Arrays.asList(directoryPath.list());
    Collections.sort(images);
    return FXCollections.observableList(images);
  }


}
