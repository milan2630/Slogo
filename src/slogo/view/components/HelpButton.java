package slogo.view.components;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HelpButton extends Button {

  private final int IMAGE_HEIGHT = 283;
  private final int IMAGE_WIDTH = 500;
  private static final String HELP_IMAGES_PATH = "resources";
  private static ResourceBundle resourceBundle;


  protected HelpButton(ResourceBundle resourceBundle){
    this.resourceBundle = resourceBundle;
    setText(resourceBundle.getString("HelpButton"));

  }

  private void handleHelpScreen() {
    setOnAction(e -> displayDialog());
  }

  private void displayDialog() {
    final Stage dialog = new Stage();
    dialog.initModality(Modality.NONE);
    ScrollPane scrollPane = new ScrollPane();
    VBox helpImages = createHelpScreen();
    scrollPane.setContent(helpImages);
    Scene dialogScene = new Scene(scrollPane);
    dialog.setScene(dialogScene);
    dialog.setTitle(resourceBundle.getString("HelpWindow"));
    dialog.show();
  }

  private VBox createHelpScreen() {
    VBox vbox = new VBox();
    List<String> images = getHelpImages();
    for (String image : images) {
      Image i = new Image(this.getClass().getClassLoader().getResourceAsStream(image));
      ImageView im = new ImageView(i);
      im.setFitHeight(IMAGE_HEIGHT);
      im.setFitWidth(IMAGE_WIDTH);
      vbox.getChildren().add(im);
    }
    return vbox;
  }

  private List<String> getHelpImages() {
    File directoryPath = new File(HELP_IMAGES_PATH);
    List<String> images = new ArrayList<>();
    for (String s : directoryPath.list()) {
      if (s.contains("Help")) {
        images.add(s);
      }
    }
    Collections.sort(images);
    return images;
  }

}
