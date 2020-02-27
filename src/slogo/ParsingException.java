package slogo;

import java.text.ParseException;
import java.util.ResourceBundle;


public class ParsingException extends ParseException {

    private static final String RESOURCES = "resources";
    private static final String DEFAULT_RESOURCE_PACKAGE = RESOURCES + ".";
    private static final String ERROR_RESOURCES = "ErrorMessages";
    private static final String DEFAULT_ERROR_PACKAGE = ERROR_RESOURCES + ".";
    private static final String DEFAULT_ERROR_RESOURCE_PACKAGE = DEFAULT_RESOURCE_PACKAGE + DEFAULT_ERROR_PACKAGE;
    private static final String DEFAULT_ERROR_MESSAGE_FILENAME = "ParsingErrorMessages";
    private static final ResourceBundle errorResources= ResourceBundle.getBundle(DEFAULT_ERROR_RESOURCE_PACKAGE + DEFAULT_ERROR_MESSAGE_FILENAME);
    private static final int DEFAULT_OFFSET = -1;

    /**
     * Constructs a ParseException with the specified detail message and
     * offset.
     * A detail message is a String that describes this particular exception.
     *
     * @param property           the key for a detail message in the error message properties file
     * @param errorOffset the position where the error is found while parsing.
     */
    public ParsingException(String property, int errorOffset) {
        super(errorResources.getString(property), errorOffset);
    }

    public ParsingException(String property){
        this(property, DEFAULT_OFFSET);
    }

    public ParsingException(String property, String errorEntity, int errorOffset){
        super(errorResources.getString(property) + "\t Error causing entity: " + errorEntity + "\t Entity number: " + errorOffset, errorOffset);
    }

    public ParsingException(String property, String errorEntity){
        super(errorResources.getString(property) + "\t Error causing entity: " + errorEntity, DEFAULT_OFFSET);
    }
}
