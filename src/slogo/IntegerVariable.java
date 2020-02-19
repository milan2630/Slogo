package slogo;

public class IntegerVariable extends Variable<Integer> {
    private int myValue;
    public IntegerVariable(int value, String name){
        super(name);
        myValue = value;
    }

    public Integer getValue() {
        return myValue;
    }
}
