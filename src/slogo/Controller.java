package slogo;

import javafx.stage.Stage;
import view.HistoryView;
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
        myHistory = new History();
        myVisualizer.bindHistory(this.language, myHistory.getInputs());
        myVisualizer.bindVariable(this.language, myVE.getDisplayVariables());
        myVisualizer.bindMethods(this.language, myME.getMethodNames());
    }

    public void togglePenState() {
        myVisualizer.updatePenState(myTurtle.getPenState() == 0);
        myTurtle.setPenState(1 - myTurtle.getPenState());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("Run")){
            String command = evt.getNewValue().toString();
            try {
                turtleList = myParser.parseStringToCommands(command, myTurtle);
                myHistory.addInput(command);
                for (ImmutableTurtle it : turtleList) {
                    myVisualizer.updatePositions(it.getX(), it.getY());
                    myTurtle.setX(it.getX());
                    myTurtle.setY(it.getY());
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

/*
    public List<ImmutableTurtle> passTurtle() {
        return turtleList;
    }

    public void updatePositions() {
        //myVisualizer.updatePositions();
    }

    // set a language
    // immutable turtle class

 */
}
