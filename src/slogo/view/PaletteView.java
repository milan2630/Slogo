package slogo.view;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Pair;

public class PaletteView extends VBox {

  private List<Pair<Color, String>> colors;
  private ObservableList<Pair<Color, String>> palette;
  private static final String PATH = "resources/Palettes/";
  private ResourceBundle colorsResources;

  public PaletteView(String language, ObservableList list) {
    palette = list;
    colorsResources = ResourceBundle.getBundle(PATH + language);
    createColorList();
    ComboBox background = bindList();
    ComboBox pen = bindList();
    background.getSelectionModel().selectedItemProperty().
            addListener(e-> changeBackgroundColor(background.getSelectionModel().getSelectedItem()));
    pen.getSelectionModel().selectedItemProperty()
            .addListener(e -> changePenColor(background.getSelectionModel().getSelectedItem()));
    getChildren().addAll(background, pen);
  }

  private void changePenColor(Object selectedItem) {
    //TODO Call method
  }

  private ComboBox bindList(){
    ComboBox comboBox = new ComboBox();
    comboBox.itemsProperty().bind(new SimpleObjectProperty<>(palette));
    return comboBox;
  }

  private void changeBackgroundColor(Object selectedItem) {
    //TODO call command
    System.out.println(selectedItem);
  }


  private void createColorList() {
    colors = new ArrayList<>();
    for (String key : colorsResources.keySet()) {
      colors.add(new Pair<>(Color.web(key), colorsResources.getString(key)));
    }
  }

  private List<String> createNumberColorList() {
    List<String> numberColors = new ArrayList<>();
    for (int i = 0; i < colors.size(); i++) {
      numberColors.add(i + ". " + colors.get(i).getValue());
    }
    return numberColors;
  }

}