package slogo.view.settingtab;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import slogo.view.Actions;

public class TurtleImageDropdown extends LabelAndAction {

  private static final String RESOURCE_PATH = "resources/";
  private static final String TURTLE_PATH = "turtles/";

  private ComboBox<String> comboBox;

  protected TurtleImageDropdown(String language, String methodName, Actions target) {
    super(language, methodName);
    ObservableList<String> images = getTurtleImages();
    comboBox = new ComboBox<>();
    comboBox.setValue(getDefaultFromKey(methodName));
    comboBox.itemsProperty().bind(new SimpleObjectProperty<>(images));
    comboBox.setOnAction(
        handler -> handleAction(comboBox.getValue().substring(0, comboBox.getValue().indexOf("\t")), methodName, target));
    getChildren().add(comboBox);
  }

  private ObservableList<String> getTurtleImages() {
    File directoryPath = new File(RESOURCE_PATH + TURTLE_PATH);
    List<String> images = FXCollections.observableList(new ArrayList<>());
    for (int i =0; i<directoryPath.list().length; i++){
      images.add(i+"\t"+directoryPath.list()[i]);
    }
    Collections.sort(images);
    return FXCollections.observableList(images);
  }


}
