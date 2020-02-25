package slogo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ResourceBundle;
import java.util.Stack;

public class Turtle {

    private static final String RESOURCES = "resources";
    private static final String METHOD_RESOURCES = "CommandMethodNames";
    public static final String DEFAULT_METHOD_RESOURCE_PACKAGE = RESOURCES + "/" + METHOD_RESOURCES + ".";
    private static final String TURTLE_METHODS_PROPERTIES = "turtleMethods";
    private static final String TURTLE_METHODS_FILEPATH = DEFAULT_METHOD_RESOURCE_PACKAGE + TURTLE_METHODS_PROPERTIES;
    private ResourceBundle myResources;


    private int myX;
    private int myY;
    private int myHeading;
    private int penState;
    private int showing;



    public Turtle(){
        myX = 0;
        myY = 0;
        myHeading = 0;
        penState = 1;
        showing = 1;
        myResources = ResourceBundle.getBundle(TURTLE_METHODS_FILEPATH);

    }

    public int actOnCommand(Command command) throws ParsingException {
        return callMethod(command);
    }

    private int callMethod(Command command) throws ParsingException {
        String[] classParts = command.getClass().toString().split("\\.");
        String key = classParts[classParts.length - 1].replace("Command", ""); //TODO change command to be generalized
        String methodName = myResources.getString(key);
        Method[] t = this.getClass().getMethods();
        Method method = null;
        try {
            method = this.getClass().getDeclaredMethod(methodName, command.getClass());
            return (int) method.invoke(this, command);
        } catch (NoSuchMethodException e) {
            throw new ParsingException("ads", 2);
        } catch (IllegalAccessException e) {
            throw new ParsingException("ads", 2);
        } catch (InvocationTargetException e) {
            throw new ParsingException("ads", 2);
        }
    }



    //TODO implement properly
    private int moveForward(ForwardCommand forward){
        myX+= forward.getPixelsForward();
        return forward.getPixelsForward();
    }

    private void moveBack(BackwardCommand backward) {
        myX-= backward.getPixelsBackward();
    }
/*
    private void turnLeft(LeftCommand left) {
        myHeading-= left.getDegreesLeft();
    }

    private void turnRight(RightCommand right) {
        myHeading+= right.getDegreesRight();
    }

    private void setHeading(SetHeadingCommand setHeading) {
        myHeading = setHeading.getHeading();
    }

    private void setTowards(SetTowardsCommand setTowards) {
        int x = setTowards.getX();
        int y = setTowards.getY();
        //TODO: calculate the angle and set heading to that (still figuring it out)
        //TODO: myHeading should probably be a double for more precision
    }

    private void setPosition(SetPositionCommand setPosition) {
        myX = setPosition.getX();
        myY = setPosition.getY();
    }

    private void penDown(PenDownCommand penDown) {
        penState = 1;
    }

    private void penUp(PenUpCommand penUp) {
        penState = 0;
    }

    private void showTurtle(ShowTurtleCommand showTurtle) {
        showing = 1;
    }

    private void hideTurtle(HideTurtleCommand hideTurtle) {
        showing = 0;
    }

    private void goHome(GoHomeCommand goHome) {
        myX = 0;
        myY = 0;
    }

    private void clearScreen(ClearScreenCommand clearScreen) {
        goHome(new GoHomeCommand());
        // TODO: tell Controller and clear TrailView in Visualizer
    }

 */

    public int getX() {
        return myX;
    }

    public int getY() {
        return myY;
    }

    public int getHeading() {
        return myHeading;
    }

    public int getPenState(){
        return penState;
    }

    public int getShowing(){
        return showing;
    }

    public ImmutableTurtle getImmutableTurtle(){
        return new ImmutableTurtle(myX, myY, myHeading, penState, showing);
    }
}
