package com.words.transformation;

import com.words.transformation.Word;
import com.words.transformation.WordTransformer;

/**
 * This class is a demo of com.words.transformation.Word Transformer
 */
public class WordTransformerDemo {

    private static Word word;
    private static WordTransformer wordTransformer;

    public static void main (String[] args){

        word = new Word();
        wordTransformer = new WordTransformer(word);

        //Declare two words from a dictionary and transform one word to another
        declareWord();

        //Check for word length errors
        boolean result = wordTransformer.checkForWordLengthError(word);
        if (result==true){
            //Do word transformation here
            wordTransformer.transform(word);
        }
        else {
            System.out.println("Both Words must be same length");
        }
    }

    private static void declareWord() {
        word.setFirstWord("DAMP");
        word.setSecondWord("LIKE");
    }
}

