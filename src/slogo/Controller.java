package slogo;

import javafx.stage.Stage;
import view.Visualizer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class Controller implements PropertyChangeListener {
    private Visualizer myVisualizer;
    private Parser myParser;
    private MethodExplorer myME;
    private VariableExplorer myVE;
    private Turtle myTurtle;

    private History myHistory;
    // bind the lists in history and historyView

    private String language;
    private List<ImmutableTurtle> turtleList;

    public Controller(Stage stage, String language) {
        myVisualizer = new Visualizer(stage, language);
        myVisualizer.addTerminalChangeListener(this);

        myME = new MethodExplorer();
        myVE = new VariableExplorer();
        this.language = language;
        myParser = new Parser(language, myME);
        myTurtle = new Turtle(myME, myVE, language);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("Run")){
            String command = evt.getNewValue().toString();
            try {
                turtleList = myParser.parseCommands(command, myTurtle);
                for (ImmutableTurtle it : turtleList) {
                    updatePositions(it);
                    updateHeading(it);
                    updatePenState(it);
                    updateShowing(it);
                }
            }
            catch(ParsingException e) {
                //TODO: handle exception properly
                System.out.println(new ParsingException("oops", 0));
            }
        }
        if (evt.getPropertyName().equals("Reset")){
            myTurtle.setToHome();
        }
    }

    public void updatePositions(ImmutableTurtle it) {
        myVisualizer.updatePositions(it.getX(), it.getY());
        myTurtle.setX(it.getX());
        myTurtle.setY(it.getY());
    }

    public void updateHeading(ImmutableTurtle it) {
        myVisualizer.updateHeading(it.getHeading());
        myTurtle.setHeading(it.getHeading());
    }

    public void updatePenState(ImmutableTurtle it) {
        myVisualizer.updatePenState(it.getPenState() == 1);
        myTurtle.setPenState(it.getPenState());
    }

    public void updateShowing(ImmutableTurtle it) {
        myVisualizer.updateTurtleState(it.getShowing() == 1);
        myTurtle.setShowing(it.getShowing());
    }

    //TODO: set language
}
