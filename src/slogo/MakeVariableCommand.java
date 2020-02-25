package slogo;

import java.util.List;

public class MakeVariableCommand implements Command {
    @Override
    public int getNumArguments() {
        return 2;
    }

    @Override
    public void setArguments(List<Integer> arguments) {

    }
}
