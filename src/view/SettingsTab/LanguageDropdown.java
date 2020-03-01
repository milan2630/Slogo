package view.SettingsTab;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.ComboBox;

public class LanguageDropdown extends LabeledDropdown {

  private ComboBox<String> comboBox;

  private static final String DEFAULT_LANGUAGE = "English";
  private static final String LANGUAGE_PATH = "src/resources/languages/";

  protected LanguageDropdown(String prompt) {
    super(prompt);
    comboBox = new ComboBox<>();
    comboBox.setValue(DEFAULT_LANGUAGE);
    comboBox.getItems().addAll(getLanguages());

    getChildren().add(comboBox);
  }


  private List<String> getLanguages() {
    File directoryPath = new File(LANGUAGE_PATH);
    List<String> languages = new ArrayList<>();
    for (String s : directoryPath.list()) {
      int index = s.indexOf(".");
      String substring = s.substring(0, index);
      languages.add(substring);
    }
    return languages;
  }

  protected String getValue() {
    return comboBox.getValue();
  }

  private void getValue(String language) {
    comboBox.setValue(language);
  }

}
