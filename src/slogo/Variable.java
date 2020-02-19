package slogo;

abstract public class Variable <E> {
    private String myName;
    public Variable(String name){
        myName = name;
    }
    abstract public E getValue();

    abstract public void setValue(E value);

    /**
     * set myName to str
     * @param str
     */
    public void setName(String str){
        myName = str;
    }

    /**
     * returns myName
     * @return myName
     */
    public String getName(){
        return myName;
    }

}
