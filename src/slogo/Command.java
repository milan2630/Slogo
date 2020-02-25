package slogo;

import java.util.List;

public interface Command {

    public int getNumArguments();

    //public List<Class> getArgumentTypes();

    public void setArguments(List<Integer> arguments);



}
