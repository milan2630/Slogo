package slogo.view;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Pair;

public class PaletteView extends VBox {

  private List<Pair<Color, String>> colors;
  private static final String PATH = "resources/Palettes/";
  private ResourceBundle colorsResources;


  public PaletteView(String language, Actions actions) {
    colorsResources = ResourceBundle.getBundle(PATH + language);

    createColorList();
    ComboBox dropdown = new ComboBox();
    dropdown.getItems().addAll(createNumberColorList());
    getChildren().add(dropdown);
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
