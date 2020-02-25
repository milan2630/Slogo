package slogo;

import java.util.List;

public class IfCommand implements ControlCommand {


    private int compare;

    @Override
    public int getNumArguments() {
        return 1;
    }

    @Override
    public void setArguments(List<Integer> arguments) {
        compare = arguments.get(0);
    }

    @Override
    public int numBracketArguments() {
        return 0;
    }

    public int getCompare(){
        return compare;
    }

}
