package slogo;

import java.awt.*;
import java.util.List;

public class ForwardCommand implements TurtleCommand{


    private int pixelsForward;

    public ForwardCommand(){
        //do nothing
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

    @Override
    public void setArguments(List<Integer> arguments) {
        pixelsForward = arguments.get(0);
    }
    
}