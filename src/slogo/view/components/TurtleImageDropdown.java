package slogo.view.components;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import slogo.view.Actions;

public class TurtleImageDropdown extends Component {

  private static final String RESOURCE_PATH = "resources/";
  private static final String TURTLE_PATH = "turtles/";

  private ComboBox<String> comboBox;

  protected TurtleImageDropdown(String language, String key, Actions actions) {
    super(language, key, actions);
    ObservableList<String> images = getTurtleImages();
    comboBox = new ComboBox<>();
    comboBox.setValue(getDefaultFromKey(key));
    comboBox.itemsProperty().bind(new SimpleObjectProperty<>(images));
    comboBox.setOnAction(
        handler -> handleAction(comboBox.getValue()));
    getChildren().add(comboBox);
  }

  private ObservableList<String> getTurtleImages() {
    File directoryPath = new File(RESOURCE_PATH + TURTLE_PATH);
    List<String> images = Arrays.asList(directoryPath.list());
    Collections.sort(images);
    return FXCollections.observableList(images);
  }


}
