package slogo.Model.Parsing;

import slogo.Model.ErrorHandling.ParsingException;

import java.util.Iterator;
import java.util.List;

public class EntityListIterator<T> implements Iterator {

    private List<String> entityList;
    private int indexInEntityList;

    public EntityListIterator(List<String> itemList){
        entityList = itemList;
        indexInEntityList = 0;
    }

    @Override
    public boolean hasNext() {
        return indexInEntityList < entityList.size();
    }

    @Override
    public String next() throws ArrayIndexOutOfBoundsException{
        String item = entityList.get(indexInEntityList);
        if(item.equals("[")){
            indexInEntityList++;
            int bracketsSeen = 1;
            while (bracketsSeen != 0) {
                if (entityList.get(indexInEntityList).contains("]")) {
                    bracketsSeen--;
                }
                if (entityList.get(indexInEntityList).contains("[")) {
                    bracketsSeen++;
                }
                item = item + " " + entityList.get(indexInEntityList);
                indexInEntityList++;
            }
            indexInEntityList--;
        }
        indexInEntityList++;
        return item;
    }
}
