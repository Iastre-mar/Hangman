package ru.cva.hangman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordsHub {
    private static final List<Word> words = new ArrayList<>();
    private static final Random random = new Random();

    static {
        words.add(new Word("Numbat"));
        words.add(new Word("Jellyfish"));
        words.add(new Word("Fossa"));
        words.add(new Word("Beaver"));
    }

    private WordsHub() {

    }

    public static Word getWord() {
        return words.get(random.nextInt(words.size()));
    }

}
