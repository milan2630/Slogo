package slogo;

import view.Visualizer;

import java.util.List;

public class ModelExternal implements BackEndExternal{

    CommandManager commandManager;
    String language;

    public ModelExternal(String lang, MethodExplorer me, VariableExplorer ve, Visualizer v){
        commandManager = new CommandManager(v, me, ve, lang);
        language = lang;
    }

    @Override
    public List<ImmutableTurtle> parseCommands(String input) throws ParsingException {
        Parser parser = new Parser(commandManager);
        parser.parseCommands(input);
        List<ImmutableTurtle> ret = commandManager.getInternalStates();
        commandManager.clearInternalStates();
        return ret;
    }
}
