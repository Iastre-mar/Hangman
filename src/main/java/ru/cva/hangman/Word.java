package ru.cva.hangman;

public record Word(String hiddenWord, String lang) {

    public Word(String hiddenWord) {
        this(hiddenWord, "English");
    }

    public String getHiddenWord() {
        return hiddenWord;
    }

    @Override
    public String lang() {
        return lang;
    }


}
