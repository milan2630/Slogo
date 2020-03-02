package slogo.Model;

import slogo.Model.Explorers.MethodExplorer;
import slogo.Model.Parsing.CommandManager;
import slogo.Model.Parsing.Parser;
import slogo.Model.TurtleModel.ImmutableTurtle;
import slogo.Model.ErrorHandling.ParsingException;
import slogo.Model.Explorers.Variables.VariableExplorer;
import slogo.view.Visualizer;

import java.util.List;

public class ModelExternal implements BackEndExternal {

    private CommandManager commandManager;

    public ModelExternal(String lang, MethodExplorer me, VariableExplorer ve, Visualizer v){
        commandManager = new CommandManager(v, me, ve, lang);
    }

    @Override
    public List<ImmutableTurtle> parseCommands(String input) throws ParsingException {
        Parser parser = new Parser(commandManager);
        parser.parseCommands(input);
        List<ImmutableTurtle> ret = commandManager.getInternalStates();
        commandManager.clearInternalStates();
        System.out.println("States:");
        for(ImmutableTurtle t: ret){
            System.out.println(t.getY());
        }
        return ret;
    }

    @Override
    public void setLanguage(String lang) {
        commandManager.setLanguage(lang);
    }
}
