package slogo;

public class IfCommand implements ControlCommand {


    private int compare;

    @Override
    public int getNumArguments() {
        return 1;
    }

    @Override
    public int numBracketArguments() {
        return 0;
    }

    public int getCompare(){
        return compare;
    }

}
