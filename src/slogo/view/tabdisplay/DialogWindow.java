package slogo.view.tabdisplay;

import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import slogo.Model.Parsing.LanguageHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DialogWindow extends Dialog {
    private ResourceBundle resourceBundle;

    public DialogWindow(String display, LanguageHandler language, int parameters) {
        super();
        resourceBundle = ResourceBundle.getBundle("resources/UI/" + language.getLanguage());
        setupWindow(display, parameters);
    }

    private void setupWindow(String display, int parameters) {
        VBox vbox = createInputFields(parameters);
        this.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        this.setTitle("planning");
        this.setHeaderText(display);
        this.getDialogPane().setContent(vbox);
        setupInput(vbox);
    }

    private void setupInput(VBox vbox) {
        this.setResultConverter(new Callback<ButtonType, List<String>>() {
            @Override
            public List<String> call(ButtonType b) {
                if (b == ButtonType.OK) {
                    List<String> parameters = new ArrayList<>();
                    for (Node n : vbox.getChildren()) {
                        TextField f = (TextField) n;
                        parameters.add(f.getText());
                    }
                    return parameters;
                }
                return null;
            }
        });
    }

    private VBox createInputFields(int parameters) {
        VBox vbox = new VBox();
        for (int i = 0; i < parameters; i++) {
            TextField input = new TextField();
            int j = i + 1;
            input.setPromptText(resourceBundle.getString("Parameter") + " " + j);
            vbox.getChildren().add(input);
        }
        vbox.setSpacing(10);
        return vbox;
    }
}

