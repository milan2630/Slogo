package slogo.Model.Parsing;

import slogo.Model.ErrorHandling.ParsingException;
import slogo.Model.TurtleModel.ImmutableTurtle;
import slogo.Model.TurtleModel.Turtle;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;

/**
 * Manages the Map of Turtles and holds the updating list of states to send back to the Front End
 */
public class TurtleModelManager implements Iterable{
    private Map<Double, List<ImmutableTurtle>> internalStates;
    private Map<Double, Turtle> turtles;
    private Map<Double, List<ImmutableTurtle>> previousInternalStates;
    private Map<Double, Turtle> previousTurtles;

    /**
     * Default constructor initializes maps with a single turtle at the origin
     */
    public TurtleModelManager(){
        internalStates = new HashMap<>();
        turtles = new HashMap<>();
        previousInternalStates = new HashMap<>();
        previousTurtles = new HashMap<>();
        addTurtle(1);
    }

    /**
     * Creates a backup of the internal states list
     */
    protected void backupInternalStateList() {
        previousInternalStates = new HashMap<>();
        for(double id: internalStates.keySet()){
            for(ImmutableTurtle turtle: internalStates.get(id)){
                previousInternalStates.putIfAbsent(id, new ArrayList<>());
                previousInternalStates.get(id).add(new Turtle(turtle));
            }
        }

    }

    /**
     * Creates a backup of the Turtle list
     */
    protected void backupTurtleList() {
        previousTurtles = new HashMap<>();
        for(double id: turtles.keySet()){
            previousTurtles.putIfAbsent(id, new Turtle(turtles.get(id)));
        }
    }

    /**
     * Reverts the turtle statuses to what they were prior to the last command execution
     * @return the Map of Immutable Turtles returned by the previous command
     */
    protected Map<Double, List<ImmutableTurtle>> undoAction(){
        turtles = previousTurtles;
        previousTurtles = new HashMap<>();
        return previousInternalStates;
    }

    /**
     * @return the internal States map
     */
    protected Map<Double, List<ImmutableTurtle>> getInternalStates() {
        for(Turtle turtle: turtles.values()){
            addInternalState(turtle);
        }
        return internalStates;
    }

    /**
     * Adds to the internal states map the status of turtle
     * @param turtle the turtle who's state should be added to the map
     */
    public void addInternalState(Turtle turtle){
        internalStates.putIfAbsent(turtle.getID(), new ArrayList<>());
        internalStates.get(turtle.getID()).add(turtle.getImmutableTurtle());
    }

    /**
     * Resets the internal states
     */
    protected void clearInternalStates(){
        internalStates = new HashMap<>();
    }

    /**
     * Adds a turtle with the given id to the turtle list
     * @param id of the turtle to be added
     */
    public void addTurtle(double id) {
        turtles.putIfAbsent(id, new Turtle(id));
    }

    @Override
    public TurtleIterator iterator() {
        return new TurtleIterator(turtles);
    }

    /**
     * Inactivates all turtles
     */
    public void inactivateAll() {
        for(Turtle turtle: turtles.values()){
            turtle.setIsActive(0.0);
        }
    }

    /**
     * Activates all turtles
     */
    public void activateAll() {
        for(Turtle turtle: turtles.values()){
            turtle.setIsActive(1.0);
        }
    }

    /**
     * Activates turtle with given ID
     * @param id of the turtle to activate
     * @return the activated turtle
     */
    public Turtle activateTurtle(double id){
        turtles.putIfAbsent(id, new Turtle(id));
        turtles.get(id).setIsActive(1);
        return turtles.get(id);
    }

    /**
     * @return a list of the IDs of the active turtles
     */
    public List<Double> getActiveTurtleIDs(){
        List<Double> activeIDs = new ArrayList<>();
        TurtleIterator iterator = new TurtleIterator(turtles);
        while(iterator.hasNext()){
            Turtle current = iterator.next();
            if(current.isActive() == 1.0){
                activeIDs.add(current.getID());
            }
        }
        return activeIDs;
    }

    /**
     * Activate several turtles from a list of IDs
     * @param ids of turtles to be activated
     */
    public void activateTurtles(List<Double> ids){
        for(double id: ids){
            activateTurtle(id);
        }
    }

    /**
     * @return the number of turtles created
     */
    public double getNumTurtles(){
        return turtles.size();
    }
}
