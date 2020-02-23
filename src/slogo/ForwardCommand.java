package slogo;

import java.awt.*;

public class ForwardCommand implements TurtleCommand{


    private double pixelsForward;

    public ForwardCommand(double pixels){
        pixelsForward = pixels;
    }

    public double getPixelsForward(){
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
}
