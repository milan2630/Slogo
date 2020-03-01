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
import javafx.scene.control.ComboBox;

public class TurtleImageDropdown extends LabeledDropdown {

  private static final String TURTLE_IMAGES_PATH = "resources/Turtles";

  private ComboBox<String> comboBox;

  protected TurtleImageDropdown(String prompt) {
    super(prompt);
    ObservableList<String> images = getTurtleImages();
    comboBox = new ComboBox<>();
    comboBox.itemsProperty().bind(new SimpleObjectProperty<>(images));
    getChildren().add(comboBox);
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
