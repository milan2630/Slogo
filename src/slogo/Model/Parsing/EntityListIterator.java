package slogo.Model.Parsing;

import slogo.Model.ErrorHandling.ParsingException;

import java.util.*;

/**
 * Converts a String into a list of strings by splitting on the spaces and combining brackets
 */
public class EntityListIterator implements Iterator {

    private static final String OPEN_BRACKET = "[";
    private static final String CLOSED_BRACKET = "]";

    private List<String> entityList;
    private int indexInEntityList;
    private Map<String, String> bracketChars;

    /**
     * EntityListIterator Constructor that takes in a String and initializes the internal entitryList using it
     * @param input the string to split into entities
     * @throws ParsingException
     */
    public EntityListIterator(String input) throws ParsingException{
        input = input.toLowerCase();
        input = stripBrackets(input);
        entityList = getEntitiesFromString(input);
        indexInEntityList = 0;
        bracketChars = new HashMap<>();
        bracketChars.put(OPEN_BRACKET, CLOSED_BRACKET);

    }

    /**
     * Removes brackets at the start and end of a string if they are present
     * @param input string to be stripped
     * @return the input string without a bracket in the beginning and end
     */
    private String stripBrackets(String input) {
        if(input.charAt(0) == '[' && input.charAt(input.length()-1) == ']'){
            return input.substring(1, input.length()-1);
        }
        return input;
    }


    private List<String> getEntitiesFromString(String input) throws ParsingException {
        String noCommentString = removeComments(input);
        if(noCommentString.equals("")){
            return new ArrayList<>();
        }
        noCommentString = noCommentString.strip();
        noCommentString = noCommentString.replaceAll("\\s+", " ");
        String[] entities = noCommentString.split(" ");
        return Arrays.asList(entities);
    }

    private String removeComments(String input) {
        String[] lineList = input.split("\n");
        List<String> noComments = new ArrayList<>();
        for (String s : lineList) {
            if (s.indexOf("#") != 0 && !s.equals("")) {
                noComments.add(s);
            }
        }
        String[] noCommentArray = noComments.toArray(new String[0]);
        return String.join(" ", noCommentArray);
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
