package slogo.Model.Explorers.Variables;

public class CharacterVariable extends Variable<Character> {
    private char myValue;
    public CharacterVariable(String name, char character) {
        super(name);
        myValue=character;
    }

    /**
     * returns char myValue
     * @return myValue
     */
    //@Override
    public Character getValue() {
        return myValue;
    }

    /**
     * sets myValue to val
     * @param val
     */
    //@Override
    public void setValue(Character val) {
        myValue=val;
    }
}
