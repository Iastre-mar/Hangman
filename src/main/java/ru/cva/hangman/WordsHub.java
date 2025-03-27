package ru.cva.hangman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordsHub {
    private static final List<String> words = new ArrayList<>();

    static {
        words.add("Numbat");
        words.add("Jellyfish");
        words.add("Fossa");
        words.add("Beaver");
    }

    private WordsHub(){

    }

    public static String getWord(){
        return words.get(new Random().nextInt(words.size()));
    }

}
