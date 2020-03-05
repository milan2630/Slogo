package slogo.Model.Parsing;

import slogo.Model.ErrorHandling.ParsingException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class EntityListIterator implements Iterator {

    private static final String OPEN_BRACKET = "[";
    private static final String CLOSED_BRACKET = "]";

    private static final String OPEN_PARENTHESES= "(";
    private static final String CLOSED_PARENTHESES = ")";

    private List<String> entityList;
    private int indexInEntityList;
    private Map<String, String> bracketChars;

    public EntityListIterator(List<String> itemList){
        entityList = itemList;
        indexInEntityList = 0;
        bracketChars = new HashMap<>();
        bracketChars.put(OPEN_BRACKET, CLOSED_BRACKET);
        bracketChars.put(OPEN_PARENTHESES, CLOSED_PARENTHESES);
    }

    @Override
    public boolean hasNext() {
        return indexInEntityList < entityList.size();
    }

    @Override
    public String next() throws ArrayIndexOutOfBoundsException{
        String item = entityList.get(indexInEntityList);
        if(bracketChars.containsKey(item)){
            String startChar = item;
            String endChar = bracketChars.get(item);
            indexInEntityList++;
            int bracketsSeen = 1;
            while (bracketsSeen != 0) {
                if (entityList.get(indexInEntityList).contains(endChar)) {
                    bracketsSeen--;
                }
                if (entityList.get(indexInEntityList).contains(startChar)) {
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
