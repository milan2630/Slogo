package slogo;

import java.util.List;

public class BackwardCommand implements TurtleCommand{


    private int pixelsBackward;

    public BackwardCommand(){
        //do nothing
    }

    public BackwardCommand(int pixels){
        pixelsBackward = pixels;
    }

    public int getPixelsBackward(){
        return pixelsBackward;
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
        pixelsBackward = arguments.get(0);
    }

    @Override
    public int getReturn() {
        return pixelsBackward;
    }
}
