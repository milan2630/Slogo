package slogo.Model.Explorers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import slogo.Model.Parsing.LanguageHandler;
import slogo.Controller.Actions;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class PaletteExplorer {
    private ObservableList<String> list;
    private static final String PATH = "resources/Palettes/";
    private int backgroundIndex;
    private int penIndex;
    private Actions actions;
    private static final String RESOURCE_PATH = "resources/UI/";
    private static ResourceBundle resourceBundle;
    private ResourceBundle colorsResources;

    public PaletteExplorer(LanguageHandler language, Actions actions){
        list = FXCollections.observableArrayList(new ArrayList<>());
        colorsResources = ResourceBundle.getBundle(PATH + language.getLanguage());
        resourceBundle = ResourceBundle.getBundle(RESOURCE_PATH+language.getLanguage());
        createColorList();
        this.actions = actions;
    }

    private void createColorList() {
        for (String key : colorsResources.keySet()) {
            Color c = Color.web(key);
            String color="";
            int val =(int)(c.getRed()*255);
            color+=val+" ";
            val=(int)(c.getGreen()*255);
            color+=val+" ";
            val=(int)(c.getBlue()*255);
            color+=val;
            list.add(color);
        }
    }

    public void addColor(double r, double g, double b, double index){
        int i = (int) index;
        list.add(i, (int)r+" "+(int)g+" "+(int)b);
    }

    public void replaceColor(double r, double g, double b, double index){
        int i = (int) index;
        list.set(i,(int)r+" "+(int)g+" "+(int)b);
    }

    public double getBackgroundColor(){
        return backgroundIndex;
    }

    public double getPenColor() { return penIndex; }

    public ObservableList getList(){ return list; }

}