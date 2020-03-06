package slogo.Model.Explorers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import slogo.view.Actions;

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

    public PaletteExplorer(String language, Actions actions){
        list = FXCollections.observableArrayList(new ArrayList<>());
        colorsResources = ResourceBundle.getBundle(PATH + language);
        resourceBundle = ResourceBundle.getBundle(RESOURCE_PATH+language);
        createColorList();
        for( int i=0; i<list.size(); i++) {
            System.out.println(list.get(i));
        }
        this.actions = actions;
    }

    private void createColorList() {
        for (String key : colorsResources.keySet()) {
            Color c = Color.web(key);
            String color="";
            color+=c.getRed()+" ";
            color+=c.getGreen()+" ";
            color+=c.getBlue();
            list.add(color);
        }
    }

    public void addColor(double r, double g, double b, double index){
        int i = (int) index;
        Color newColor = new Color(r, g, b, 0);
        list.add(i, r+" "+g+" "+b);
    }

    public void replaceColor(double r, double g, double b, double index){
        int i = (int) index;
        Color newColor = new Color(r, g, b, 0);
        list.set(i,r+" "+g+" "+b+" ");
    }

    public double getBackgroundColor(){
        return backgroundIndex;
    }

    public ObservableList getList(){ return list; }

}