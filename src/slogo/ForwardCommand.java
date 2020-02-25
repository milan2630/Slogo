package slogo;

import java.util.ArrayList;
import java.util.List;

public class ForwardCommand implements TurtleCommand{

    private List<Class> arguments;

    private int pixelsForward;

    public ForwardCommand(){
        arguments = new ArrayList<>();
        arguments.add(Double.class);
    }

    public ForwardCommand(int pixels){
        pixelsForward = pixels;
    }

    public int getPixelsForward(){
        return pixelsForward;
    }


    @Override
    public int getTurtleNum() {
        return 0;
    }

    @Override
    public int getNumArguments() {
        return 1;
    }

    //@Override
    public List<Class> getArgumentTypes() {
        return arguments;
    }

}
