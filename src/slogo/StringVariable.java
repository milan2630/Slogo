package slogo;

public class StringVariable extends Variable<String> {
    private String myValue;
    public StringVariable(String name, String value){
        super(name);
        myValue=value;
    }

    /**
     * returns myValue of this StringVariable
     * @return myValue
     */
    @Override
    public String getValue() {
        return null;
    }

    /**
     * sets myValue to value
     * @param value
     */
    public void setValue(String value){
        myValue=value;
    }
}
