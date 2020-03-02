package view.SettingsTab;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import view.Actions;

public class TurtleImageDropdown extends LabeledDropdown {

  private static final String RESOURCE_PATH = "resources/";
  private static final String TURTLE_PATH = "Turtles/";
  private static final String DEFAULT_TURTLE = "turtle.png";

  private ComboBox<String> comboBox;

  protected TurtleImageDropdown(String prompt, String methodName, Actions target) {
    super(prompt);
    ObservableList<String> images = getTurtleImages();
    comboBox = new ComboBox<>();
    comboBox.setValue(DEFAULT_TURTLE);
    comboBox.itemsProperty().bind(new SimpleObjectProperty<>(images));
    comboBox.setOnAction(
        handler -> handleAction(comboBox.getValue(), methodName, target));
    getChildren().add(comboBox);
  }

  private ObservableList<String> getTurtleImages() {
    File directoryPath = new File(RESOURCE_PATH + TURTLE_PATH);
    List<String> images = Arrays.asList(directoryPath.list());
    Collections.sort(images);
    return FXCollections.observableList(images);
  }


}
