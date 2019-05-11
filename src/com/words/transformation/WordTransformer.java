package com.words.transformation;

import com.words.transformation.Word;

import java.io.*;

/**
 * This class implement word transformer
 */
public class WordTransformer {

    private Word word;

    public WordTransformer(Word word){
        this.word = word;
    }

    /**
     * This method checks for word length errors
     * @param word - word class instance
     * @return - True or False
     */
    protected boolean checkForWordLengthError(Word word) {
        if (word.getFirstWord().length() == word.getSecondWord().length()) {
            return true;
        }
        return false;
    }

    /**
     * This method transform the first word to find another word found in the dictionary
     * file found in the dict.txt file until it reaches the second word.
     * @param word - word class instance
     */
    protected void transform(Word word) {
        String str2=word.getSecondWord();
        String b1=word.getFirstWord();
        System.out.println("Input: "+ b1 + " , " + str2);
        System.out.print(b1 + " -> ");
        StringBuilder builder=new StringBuilder();
        // Do word permutations and check if that word is in the dictionary or not
        for(int i=0; i<str2.length();i++) {
            char c1 = str2.charAt(i);
            for(int j=0; j<str2.length();j++){
                builder.setLength(0);
                builder.append(b1);
                builder.replace(j,j+1,String.valueOf(c1));
                boolean b = dictCheck(builder.toString());
                if(b==true){
                    System.out.print(builder + " -> ");
                    //System.out.println("Found in the Dictionary");
                    b1=builder.toString();
                    break;
                }
            }
        }
        System.out.print(str2);
    }

    /**
     * This method reads the english words in the dictionary checks if the word exists
     * in the dictionary
     * @param s - the word to be checked
     * @return - true/false
     */
    protected boolean dictCheck(String s){
        File file = new File("dict.txt");
        BufferedReader br;

        {
            try {
                br = new BufferedReader(new FileReader(file));
                String st="";
                while((st=br.readLine())!= null){
                    if(st.equalsIgnoreCase(s)){
                        return true;
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

}
