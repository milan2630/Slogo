package slogo;

public class DoubleVariable extends Variable<Double> {
    private Double myValue;
    public DoubleVariable(String name, Double num){
        super(name);
        myValue=num;
    }
    /**
     * returns value of DoubleVariable
     * @return double myValue
     */
    //@Override
    public Double getValue() {
        return myValue;
    }

    /**
     * sets myValue of this DoubleVariable to num
     * @param num
     */
    public void setValue(Double num){
        myValue=num;
    }
}
