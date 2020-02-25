package slogo;

public class IntegerVariable extends Variable<Integer> {
    private int myValue;
    public IntegerVariable(String name, int value){
        super(name);
        myValue = value;
    }

    /**
     * Returns int of myValue
     * @return int
     */
    public Integer getValue() {
        return myValue;
    }

    /**
     * sets value of myValue to num
     * @param num
     */
    //@Override
    public void setValue(Integer num) {
        myValue = num;
    }
}
