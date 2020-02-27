package slogo;

import slogo.Commands.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Turtle {

    private static final String RESOURCES = "resources";
    private static final String METHOD_RESOURCES = "CommandMethodNames";
    private static final String DEFAULT_METHOD_RESOURCE_PACKAGE = RESOURCES + "/" + METHOD_RESOURCES + ".";
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
    private String language;
    private List<ImmutableTurtle> internalStates;

    public Turtle(MethodExplorer me, VariableExplorer ve, String lang){
        myX = 0;
        myY = 0;
        myHeading = 0;
        penState = 1;
        showing = 1;
        myResources = ResourceBundle.getBundle(TURTLE_METHODS_FILEPATH);
        methodExplorer = me;
        variableExplorer = ve;
        language = lang;
        internalStates = new ArrayList<>();
    }

    public String actOnCommand(Command command, List<String> params) throws ParsingException {
        return callMethod(command, params) + "";
    }

    private double callMethod(Command command, List<String> params) throws ParsingException {
        String[] classParts = command.getClass().toString().split("\\.");
        String key = classParts[classParts.length - 1].replace("Command", ""); //TODO change command to be generalized
        String methodName = myResources.getString(key);
        try {
            Method method = this.getClass().getDeclaredMethod(methodName, command.getClass(), List.class);
            double ret = (double) method.invoke(this, command, params);
            internalStates.add(this.getImmutableTurtle());
            return ret;
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new ParsingException("ExecuteMissing", command.toString());
        } catch (InvocationTargetException e) {
            if(e.getCause() instanceof ParsingException){
                throw (ParsingException) e.getCause();
            }
            throw new ParsingException("CommandExecuteError", command.toString());//TODO implemenet Command.toString()
        }
    }

    private double runUserMethod(UserDefinedInstructionCommand command, List<String> params) throws ParsingException {
        List<Double> inputs = new ArrayList<>();
        for(int i = 0; i < params.size(); i++){
            inputs.add(getDoubleParameter(params.get(i)));
        }
        command.setArguments(inputs);

        String com = command.getExecutableCommands();
        Parser newParser = new Parser(language, methodExplorer);
        parseInternalCommand(newParser, com);
        removeLastInternalState();
        variableExplorer.removeVariablesByNames(command.getArgumentNames());
        return newParser.getFinalReturn();
    }

    private void removeLastInternalState() {
        if(internalStates.size() > 0) {
            internalStates.remove(internalStates.size() - 1);
        }
    }


    private double makeMethod(MakeUserInstructionCommand command, List<String> params) throws ParsingException {
        try{
            Double.parseDouble(params.get(0));
            throw new ParsingException("DuplicateMethod");
        }
        catch (NumberFormatException e) {
            List<String> paramNames = new ArrayList<>();
            String[] names = params.get(1).split(" ");
            paramNames.addAll(Arrays.asList(names));
            for (int i = 0; i < paramNames.size(); i++) {
                if (paramNames.get(i).equals("")) {
                    paramNames.remove(i);
                }
            }
            UserDefinedInstructionCommand newMethod = new UserDefinedInstructionCommand(params.get(0), params.get(2), paramNames);
            methodExplorer.addMethod(newMethod);
            return 0;
        }
    }

    private double getDoubleParameter(String val) throws ParsingException {
        try{
            return Double.parseDouble(val);
        }
        catch (NumberFormatException e){
            if(variableExplorer.getVariable(val) != null){
                return (Double) variableExplorer.getVariable(val).getValue();
            }
            else if(val.indexOf(":") == 0){
                variableExplorer.addDoubleVarByName(val, 0.0);
                return 0.0;
            }
            else{
                throw new ParsingException("UnrecognizedEntity", val);
            }
        }
    }

    private double ifElseCommand(IfElseCommand command, List<String> params) throws ParsingException {
        double expr = getDoubleParameter(params.get(0));
        int whichToExecute = 1;
        if(expr == 0.0){
            whichToExecute = 2;
        }
        Parser newParser = new Parser(language, methodExplorer);
        parseInternalCommand(newParser, params.get(whichToExecute));
        removeLastInternalState();
        return newParser.getFinalReturn();
    }

    private double ifCommand(IfCommand command, List<String> params) throws ParsingException {
        double expr = getDoubleParameter(params.get(0));
        if(expr == 0.0){
            return 0.0;
        }
        Parser newParser = new Parser(language, methodExplorer);
        parseInternalCommand(newParser, params.get(1));
        removeLastInternalState();
        return newParser.getFinalReturn();
    }

    private double forLoop(ForCommand command, List<String> params) throws ParsingException {
        String[] argParts = params.get(0).split(" ");
        String name = argParts[0];
        double start = getDoubleParameter(argParts[1]);
        double end = getDoubleParameter(argParts[2]);
        double iterator = getDoubleParameter(argParts[3]);
        if(start == end || params.get(1).equals("")){
            return 0;
        }
        double ret = repeatAction(params.get(1), name, start, end, iterator);
        variableExplorer.removeVariableByName(name);
        return ret;
    }

    private double repeat(RepeatCommand command, List<String> params) throws ParsingException {
        double numTimes = getDoubleParameter(params.get(0));
        if(numTimes == 0 || params.get(1).equals("")){
            return 0;
        }
        double ret = repeatAction(params.get(1), ":repcount", 1.0, numTimes, 1.0);
        variableExplorer.removeVariableByName(":repcount");
        return ret;
    }

    private double repeatAction(String command, String iteratorName, double startVal, double endVal, double iterationVal) throws ParsingException {
        Variable<Double> var = variableExplorer.addDoubleVarByName(iteratorName, startVal);
        Parser newParser = new Parser(language, methodExplorer);
        while(var.getValue() <= endVal){
            parseInternalCommand(newParser, command);
            var.setValue(var.getValue()+iterationVal);
        }
        removeLastInternalState();
        return newParser.getFinalReturn();
    }

    private double doTimes(DoTimesCommand command, List<String> params) throws ParsingException {
        String[] limitString = params.get(0).split(" ");
        double end = getDoubleParameter(limitString[1]);
        if(end <= 1 || params.get(1).equals("")){
            return 0;
        }
        return repeatAction(params.get(1), limitString[0], 1.0, end, 1.0);
    }

    private void parseInternalCommand(Parser newParser, String s) throws ParsingException {
        List<ImmutableTurtle> stateList = newParser.parseCommands(s, this);
        internalStates.addAll(stateList);
    }

    private double setVariable(MakeVariableCommand variableCommand, List<String> params) throws ParsingException {
        Variable<Double> var = new DoubleVariable(params.get(0), getDoubleParameter(params.get(1)));
        variableExplorer.addVariable(var);
        return var.getValue();
    }

    private double moveForward(ForwardCommand forward, List<String> params) throws ParsingException{
        Double pixForward = getDoubleParameter(params.get(0));
        myY+= pixForward * Math.cos(Math.toRadians(myHeading));
        myX+= pixForward * Math.sin(Math.toRadians(myHeading));
        return pixForward;
    }

    private double moveBack(BackwardCommand backward, List<String> params) throws ParsingException {
        Double pixBackward = getDoubleParameter(params.get(0));
        myY-= pixBackward * Math.cos(Math.toRadians(myHeading));
        myX-= pixBackward * Math.sin(Math.toRadians(myHeading));
        return pixBackward;
    }

    private double turnLeft(LeftCommand left, List<String> params) throws ParsingException {
        Double degreesLeft = getDoubleParameter(params.get(0));
        myHeading -= degreesLeft;
        return myHeading;
    }

    private double turnRight(RightCommand right, List<String> params) throws ParsingException {
        Double degreesRight = getDoubleParameter(params.get(0));
        myHeading += degreesRight;
        return myHeading;
    }

    private double setHeading(SetHeadingCommand setHeading, List<String> params) throws ParsingException {
        Double heading = getDoubleParameter(params.get(0));
        myHeading = heading;
        return myHeading;
    }

    private double setTowards(SetTowardsCommand setTowards, List<String> params) throws ParsingException {
        Double towardX = getDoubleParameter(params.get(0));
        Double towardY = getDoubleParameter(params.get(1));

        if(towardX == myX && towardY == myY) {
            throw new ParsingException("TowardSelfException");
        }

        double oldHeading = myHeading;
        myHeading = Math.toDegrees(Math.atan((towardX-myX)/(towardY-myY)));
        return myHeading - oldHeading;
    }

    private double setPosition(SetPositionCommand setPosition, List<String> params) throws ParsingException {
        double oldX = myX;
        double oldY = myY;
        myX = getDoubleParameter(params.get(0));
        myY = getDoubleParameter(params.get(1));
        return Math.hypot(myX - oldX, myY - oldY);
    }

    private double setPenDown(PenDownCommand penDown, List<String> params) throws ParsingException {
        penState = 1;
        return penState;
    }

    private double setPenUp(PenUpCommand penUp, List<String> params) throws ParsingException {
        penState = 0;
        return penState;
    }

    private double showTurtle(ShowTurtleCommand showTurtle, List<String> params) throws ParsingException {
        showing = 1;
        return showing;
    }

    private double hideTurtle(HideTurtleCommand hideTurtle, List<String> params) throws ParsingException {
        showing = 0;
        return showing;
    }

    private double goHome(GoHomeCommand goHome, List<String> params) throws ParsingException {
        double oldX = myX;
        double oldY = myY;
        setToHome();
        return Math.hypot(myX - oldX, myY - oldY);
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

    private ImmutableTurtle getImmutableTurtle(){
        return new ImmutableTurtle(myX, myY, myHeading, penState, showing);
    }

    public void setX(double x) {
        myX = x;
    }

    public void setY(double y) {
        myY = y;
    }

    public void setToHome() {
        myX = 0;
        myY = 0;
    }

    public void setHeading(double heading) {
        myHeading = heading;
    }

    public void setPenState(int state) {
        penState = state;
    }

    public void setShowing(int showing) {
        this.showing = showing;
    }

    public List<ImmutableTurtle> getInternalStates() {
        List<ImmutableTurtle> copy = new ArrayList<>(internalStates);
        internalStates = new ArrayList<>();
        return copy;
    }
}
