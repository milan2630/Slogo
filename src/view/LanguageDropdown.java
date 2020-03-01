package view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.ComboBox;

public class LanguageDropdown extends ComboBox<String> {
  private String language;
  private static final String DEFAULT_LANGUAGE = "English";

  private static final String LANGUAGE_PATH = "src/resources/languages/";

  protected LanguageDropdown(String prompt){
    setPromptText(prompt);
    setValue(DEFAULT_LANGUAGE);
    this.language = DEFAULT_LANGUAGE;

    getItems().addAll(getLanguages());
    setOnAction(e -> changeLanguage(this.valueProperty().get()));
  }

  protected String getLanguage() {
    return language;
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

  private void changeLanguage(String s) {
    this.language = s;
  }


}
