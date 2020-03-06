package slogo.view.settingtab;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import slogo.xml.Chooser;
import slogo.xml.Configuration;
import slogo.xml.XMLException;
import slogo.xml.XMLParser;

import java.io.File;

public class LoadXML extends Button {
    //private Configuration myConfiguration;
    private File dataFile;

    public LoadXML(){
        setText("Load XML");
        setOnAction(e -> handleLoad());
    }

    private void handleLoad() {
        dataFile = Chooser.FILE_CHOOSER.showOpenDialog(new Stage());
        /*
        while (dataFile != null) {
            try {
                XMLParser XMLParser = new XMLParser();
                myConfiguration = XMLParser.getConfiguration(dataFile);
            }
            catch (XMLException e) {
                // handle error of unexpected file format
                Chooser.showMessage(Alert.AlertType.ERROR, e.getMessage());
            }
            break;
        }

         */
    }
/*
    public Configuration getConfiguration() {
        return myConfiguration;
    }

 */

}
