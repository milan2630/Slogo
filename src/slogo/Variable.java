package slogo;

abstract public class Variable <E> {
    private String myName;
    public Variable(String name){
        myName = name;
    }
    abstract public E getValue();

    public String getName(){
        return myName;
    }

}
