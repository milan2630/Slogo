package slogo;

import java.util.List;

public interface Command {

    public int getNumArguments();

    public void setArguments(List<Integer> arguments);



}
