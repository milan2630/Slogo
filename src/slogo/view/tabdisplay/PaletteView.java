package slogo.view.tabdisplay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import slogo.Model.Parsing.LanguageHandler;
import slogo.Controller.Actions;

public class PaletteView extends GridPane {

  private ObservableList<String> palette;
  private static final String PATH = "resources/Palettes/";
  private static final String RESOURCES_COMMAND_PATH = "resources/Languages/";
  private static final String PREFIX = "resources/UI/";
  private static final String RESOURCES_LAYOUTS = PREFIX + "ReflectionMethods";
  private ResourceBundle methodsResources;
  private ResourceBundle resourceBundle;
  private ResourceBundle commandBundle;

  private Actions actions;
  private LanguageHandler languageHandler;
  private VBox background;
  private VBox pen;
  private final double COLOR_SCALE = 255.0;

  public PaletteView(LanguageHandler language, ObservableList list, Actions actions) {
    palette = list;
    languageHandler = language;
    methodsResources = ResourceBundle.getBundle(RESOURCES_LAYOUTS);
    resourceBundle = ResourceBundle.getBundle((PREFIX + language.getLanguage()));
    commandBundle = ResourceBundle.getBundle(RESOURCES_COMMAND_PATH + language.getLanguage());
    this.actions = actions;
    background = bindList("BackgroundPalette");
    pen = bindList("PenPalette");
    palette.addListener((ListChangeListener.Change<? extends String> e) -> handleColorAdded(e));
    add(background, 0, 0, 1, 1);
    add(pen, 2, 0, 1, 1);
  }

  private void handleColorAdded(ListChangeListener.Change<? extends String> e) {
    if (e.wasAdded()) {
      addAdditionalColor();
    }
  }

  private void addAdditionalColor() {
    background.getChildren().add(
        createColorOption(palette.size() - 1, methodsResources.getString("BackgroundPalette")));
    pen.getChildren()
        .add(createColorOption(palette.size() - 1, methodsResources.getString("PenPalette")));
  }

  private VBox bindList(String text) {
    VBox vbox = new VBox();
    Label title = new Label();
    title.setText(resourceBundle.getString((text)));
    vbox.setAlignment(Pos.CENTER);
    vbox.getChildren().add(title);
    for (int i = 0; i < palette.size(); i++) {
      String methodName = methodsResources.getString(text);
      vbox.getChildren().add(createColorOption(i, methodName));
    }
    vbox.setSpacing(20);
    return vbox;
  }

  private Node createColorOption(int i, String methodName) {
    HBox color = new HBox();
    color.getStyleClass().add(".settings-text");
    Label label = new Label("" + i);
    label.setTextFill(Color.RED);
    Rectangle rect = new Rectangle();
    rect.setWidth(100);
    rect.setHeight(20);
    rect.setFill(getColor(i));
    if (methodName.equals(methodsResources.getString("BackgroundPalette"))) {
      rect.setOnMouseClicked(e -> actions.handleBackgroundColor(i + ""));
    } else {
      rect.setOnMouseClicked(e -> actions
          .handleCommand(commandBundle.getString("SetPenColor").split("\\|")[1] + " " + i));
    }
    color.getChildren().addAll(label, rect);
    color.setSpacing(20);
    return color;
  }

  public Color getColor(int index) {
    String s = palette.get(index);
    String[] components = s.split(" ");
    int[] colorRGB = new int[3];
    for (int j = 0; j < colorRGB.length; j++) {
      colorRGB[j] = Integer.parseInt(components[j]);
    }
    java.awt.Color c = new java.awt.Color(colorRGB[0], colorRGB[1], colorRGB[2]);
    int a = c.getAlpha();
    double opacity = a / COLOR_SCALE;
    Color color = new Color(c.getRed() / COLOR_SCALE, c.getGreen() / COLOR_SCALE,
        c.getBlue() / COLOR_SCALE, opacity);
    return color;
  }

  public void addColor(Color c) {
    int red = (int) (c.getRed() * COLOR_SCALE);
    int green = (int) (c.getGreen() * COLOR_SCALE);
    int blue = (int) (c.getBlue() * COLOR_SCALE);
    palette.add(red + " " + green + " " + blue);
  }

  public List getImmutableList() {
    List<Color> colors = new ArrayList<>();
    for (int i = 0; i < palette.size(); i++) {
      colors.add(getColor(i));
    }
    return Collections.unmodifiableList(colors);
  }

}