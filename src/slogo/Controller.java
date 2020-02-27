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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("Run")){
            String command = evt.getNewValue().toString();
            try {
                turtleList = myParser.parseCommands(command, myTurtle);
                myHistory.addInput(command);
                for (ImmutableTurtle it : turtleList) {
                    myVisualizer.updatePositions(it.getX(), it.getY());
                    myVisualizer.updateHeading(it.getHeading());
                    myVisualizer.updatePenState(it.getPenState() == 1);
                    myVisualizer.updateTurtleState(it.getShowing() == 1);
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

    //TODO: set language
    // check for screen bounds
}
