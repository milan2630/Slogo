package view.SettingsTab;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class TurtleImageDropdown extends ComboBox<String> {
  private static final String TURTLE_IMAGES_PATH = "resources/Turtles";


  protected TurtleImageDropdown(String prompt){
    setPromptText(prompt);
    ObservableList<String> images = getTurtleImages();
    itemsProperty().bind(new SimpleObjectProperty<>(images));
  }

  private ObservableList<String> getTurtleImages() {
    File directoryPath = new File(TURTLE_IMAGES_PATH);
    List<String> images = Arrays.asList(directoryPath.list());
    Collections.sort(images);
    return FXCollections.observableList(images);
  }



}
