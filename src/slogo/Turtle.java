package slogo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;

public class Turtle {

    private static final String RESOURCES = "resources";
    private static final String METHOD_RESOURCES = "CommandMethodNames";
    public static final String DEFAULT_METHOD_RESOURCE_PACKAGE = RESOURCES + "/" + METHOD_RESOURCES + ".";
    private static final String TURTLE_METHODS_PROPERTIES = "turtleMethods";
    private static final String TURTLE_METHODS_FILEPATH = DEFAULT_METHOD_RESOURCE_PACKAGE + TURTLE_METHODS_PROPERTIES;
    private ResourceBundle myResources;


    private double myX;
    private double myY;
    private double myHeading;
    private int penState;
    private int showing;
    private MethodExplorer methodExplorer;
    private VariableExplorer variableExplorer;


    public Turtle(MethodExplorer me, VariableExplorer ve){
        myX = 0;
        myY = 0;
        myHeading = 0;
        penState = 1;
        showing = 1;
        myResources = ResourceBundle.getBundle(TURTLE_METHODS_FILEPATH);
        methodExplorer = me;
        variableExplorer = ve;

    }

    public double actOnCommand(Command command, List<Object> params) throws ParsingException {
        return callMethod(command, params);
    }

    private double callMethod(Command command, List<Object> params) throws ParsingException {
        String[] classParts = command.getClass().toString().split("\\.");
        String key = classParts[classParts.length - 1].replace("Command", ""); //TODO change command to be generalized
        String methodName = myResources.getString(key);
        Method[] t = this.getClass().getMethods();
        try {
            Method method = this.getClass().getDeclaredMethod(methodName, command.getClass(), List.class);
            return (double) method.invoke(this, command, params);
        } catch (NoSuchMethodException e) {
            throw new ParsingException("ads", 2);
        } catch (IllegalAccessException e) {
            throw new ParsingException("ads", 2);
        } catch (InvocationTargetException e) {
            throw new ParsingException("ads", 2);
        }
    }



    //TODO implement properly
    private double moveForward(ForwardCommand forward, List<Object> params) throws ClassCastException{
        //List<Class> paramTypes = forward.getArgumentTypes();
        double pixForward = (Double) params.get(0);
        myX+= pixForward;
        return pixForward;
    }

    private double setVariable(MakeVariableCommand variableCommand, List<Object> params){
        
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

    public double getX() {
        return myX;
    }

    public double getY() {
        return myY;
    }

    public double getHeading() {
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
