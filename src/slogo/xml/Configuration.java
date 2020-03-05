package slogo.xml;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class Configuration {
    public static final List<String> SIZE_FIELDS = List.of(
            "background",
            "palette",
            "language",
            "turtles"
    );

    private String myBackground;
    private String myPalette;
    private String myLanguage;
    private int myTurtles;

    public Configuration (String background, String palette, String language, String turtles) {
        myBackground = background;
        myPalette = palette;
        myLanguage = language;
        myTurtles = Integer.parseInt(turtles);
    }

    public Configuration (Map<String, String> dataValues) {
        this(dataValues.get(SIZE_FIELDS.get(0)),
                dataValues.get(SIZE_FIELDS.get(1)),
                dataValues.get(SIZE_FIELDS.get(2)),
                dataValues.get(SIZE_FIELDS.get(3)));
    }

    public String getBackground() {
        return myBackground;
    }

    public String getPalette() {
        return myPalette;
    }

    public String getLanguage() {
        return myLanguage;
    }

    public int getTurtles() {
        return myTurtles;
    }
}
