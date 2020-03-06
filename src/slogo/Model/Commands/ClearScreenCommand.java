package slogo.Model.Commands;

public class ClearScreenCommand {



    public int getNumArguments() {
        return 0;
    }

}
 /*
    private double clearScreen(ClearScreenCommand clearScreen, List<String> params) throws ParsingException {
        double oldX = myX;
        double oldY = myY;
        setToHome();
        // TODO: tell Controller and clear TrailView in Visualizer, won't work until design change
        return Math.hypot(myX - oldX, myY - oldY);
    }
 */