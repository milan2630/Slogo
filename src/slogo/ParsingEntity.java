package slogo;

public class ParsingEntity {

    private String val;
    private boolean isCommand;

    public ParsingEntity(String value){
        if(value.matches("[a-zA-Z_]+(\\?)?")){
            isCommand = true;
        }
        else{
            isCommand = false;
        }
        val = value;
    }

    public ParsingEntity(String value, boolean isCom){
        val = value;
        isCommand = isCom;
    }

    public boolean isCommand() {
        return isCommand;
    }

    public String getVal() {
        return val;
    }
}
