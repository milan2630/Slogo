package slogo;

import java.security.spec.ECField;
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
        myVisualizer.bindTabs(this.language, myHistory.getInputs(), myVE.getDisplayVariables(),myME.getMethodNames() );
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("Run")){
            language = myVisualizer.getLanguage();
            String command = evt.getNewValue().toString();
            try {
                turtleList = myParser.parseCommands(command, myTurtle);
                myHistory.addInput(command);
                myVisualizer.updateTurtle(turtleList);
            }
            catch(ParsingException e) {
                myVisualizer.displayError(e);
            }
        }
        if (evt.getPropertyName().equals("Reset")){
            myTurtle.setToHome();
        }
    }

    //TODO: set language
    // check for screen bounds
}
