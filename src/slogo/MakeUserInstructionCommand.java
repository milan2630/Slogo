package slogo;

import java.util.List;

public class MakeUserInstructionCommand implements Command {
    @Override
    public int getNumArguments() {
        return 3;
    }

    @Override
    public void setArguments(List<Integer> arguments) {

    }
}
