package com.pattern.matching;

import java.util.*;

/**
 * This implementation is based on a question on cracking the coding interview moderate questions
 * on pattern matching
 * Used a linked hash map to preserve the insertion order
 */
public class Pmatching {

    private static String pattern;
    private static String value;

    public static void main(String[] args) {

        System.out.println("This program matches the string with a pattern provided as a's and b's");
        pattern = "aabab";
        value = "catcatgocatgo";
        System.out.println("Pattern: " + pattern +  " Value: " + value);
        Boolean result = match(pattern, value);
        if(result) {
            System.out.println("Match!!");
        } else {
            System.out.println("No pattern match!!");
        }

    }

    /**
     * This method match the pattern with the value
     * @param pattern - pattern string
     * @param value - actual string to be matched
     * @return - true|false
     */
    public static boolean match(String pattern, String value) {
        // Create an array to identify the unique characters in the value string
        //Create a HashMap of small letters from a-z
        HashMap<Character,Boolean> map = new HashMap<>();
        for(int i=97; i<123;i++) {
            map.put((char)i,false);
        }
        //Check the unique characters in the value string
        for(int j=0;j<value.length();j++) {
            map.put(value.charAt(j),true);
        }

        ArrayList<Character> uchars = getUniqueCharacters(value, map);

        LinkedHashMap<Character, ArrayList<Integer>> indexMap = getCharacterOccurance(value, uchars);


        LinkedHashMap<Integer, ArrayList<Character>> lens = getUniqueWordsWithRepeats(indexMap);

        if (checkError(lens)) return false;

        HashMap<Character, String> patternMapper = getPatternMapper(lens);

        printPatternMapper(patternMapper);

        String strPattern = formStringFromPattern(pattern, patternMapper);

        //Compare the constructed string from the pattern to actual string provided
        if(value.equals(strPattern)) {
            return true;
        }

        return false;
    }

    /**
     * This method forms a string from a patten to be compared to the actual value
     * @param pattern - pattern
     * @param patternMapper - a HashMap mapping word to pattern letters (a|b)
     * @return - constructed string
     */
    private static String formStringFromPattern(String pattern, HashMap<Character, String> patternMapper) {
        String strPattern="";
        for(int t=0;t<pattern.length();t++) {
            if(pattern.charAt(t)=='a') {
                strPattern+=patternMapper.get('a');
            } else if(pattern.charAt(t)=='b') {
                strPattern+=patternMapper.get('b');
            }
        }
        System.out.println("String build from pattern: " + strPattern);
        return strPattern;
    }

    /**
     * This method prints pattern mapping
     * @param patternMapper - a HashMap of pattern mapper
     */
    private static void printPatternMapper(HashMap<Character, String> patternMapper) {
        System.out.println("Printing the mapped word with the pattern letter (a|b)");
        for(Map.Entry<Character,String> entry : patternMapper.entrySet()) {
            System.out.println("Pattern letter: " + entry.getKey() + " Word: " + entry.getValue());
        }
    }

    /**
     * This method creates a pattern mapping
     * @param lens - a linked hash map of letters and its indexes
     * @return - a hash map of patten map
     */
    private static HashMap<Character, String> getPatternMapper(LinkedHashMap<Integer, ArrayList<Character>> lens) {
        System.out.println("Unique words with its occurence: ");
        String str="";
        int ltr=97;
        HashMap<Character,String> patternMapper = new HashMap<>();
        for(Map.Entry<Integer,ArrayList<Character>> entry : lens.entrySet()) {
            ArrayList<Character> concatStrs = entry.getValue();
            for(char c: concatStrs) {
                str+=c;
            }
            patternMapper.put((char)ltr,str);
            ++ltr;
            str="";
            System.out.println("Repeats: " + entry.getKey() + " Word in an array: " + entry.getValue());
        }
        return patternMapper;
    }

    /**
     * This method checks if more than two or only one unique words found in the index map
     * @param lens - a linked hash map of letter indexes
     * @return - true|false
     */
    private static boolean checkError(LinkedHashMap<Integer, ArrayList<Character>> lens) {
        if(lens.size()!=2) {
            System.out.println("There exists more than two unique words in side the string and the pattern can not be matched!!");
            return true;
        }
        return false;
    }

    /**
     * This method creates a unique words with its repeats
     * @param indexMap - a linked hash map of letters and its indexes
     * @return - linked hash map of words with repeats
     */
    private static LinkedHashMap<Integer, ArrayList<Character>> getUniqueWordsWithRepeats(LinkedHashMap<Character, ArrayList<Integer>> indexMap) {
        System.out.println("Letter and its indexes stored in a list: ");
        LinkedHashMap<Integer,ArrayList<Character>> lens = new LinkedHashMap<>();
        ArrayList<Character> chars = new ArrayList<>();
        for(Map.Entry<Character,ArrayList<Integer>> entry : indexMap.entrySet()) {
            if(lens.containsKey(entry.getValue().size())) {
                chars.add(entry.getKey());
                lens.put(entry.getValue().size(),chars);
            } else {
                chars = new ArrayList<>();
                chars.add(entry.getKey());
                lens.put(entry.getValue().size(),chars);
            }
            System.out.println("Letter: " +entry.getKey().toString() + " Indexes: " + entry.getValue());
        }
        return lens;
    }

    /**
     * This method creates a letter with its indexes
     * @param value - actual string
     * @param uchars - unique characters list
     * @return - linked hash map of unique letter->indexes
     */
    private static LinkedHashMap<Character, ArrayList<Integer>> getCharacterOccurance(String value, ArrayList<Character> uchars) {
        //Creating indexMap
        LinkedHashMap<Character,ArrayList<Integer>> indexMap = new LinkedHashMap<>();
        for(char ch: uchars) {
            ArrayList<Integer> ints = new ArrayList<>();
            for (int v=0;v<value.length();v++){
                if(ch==value.charAt(v)){
                    ints.add(v);
                }
            }
            indexMap.put(ch,ints);
        }
        return indexMap;
    }

    /**
     * This method creates a unique characters list form value
     * @param value - actual string
     * @param map - a hash map of letters->boolean to identify unique characters
     * @return - a list of unique characters
     */
    private static ArrayList<Character> getUniqueCharacters(String value, HashMap<Character, Boolean> map) {
        //Put unique characters in an array list
        ArrayList<Character> uchars = new ArrayList<>();
        for(int h=0;h<value.length();h++) {
            if(map.containsKey(value.charAt(h))) {
                if(map.get(value.charAt(h))) {
                    if(!uchars.contains(value.charAt(h))) {
                        uchars.add(value.charAt(h));
                    }
                }
            }
        }
        System.out.println("Unique Characters from value: " + uchars);
        return uchars;
    }

}
