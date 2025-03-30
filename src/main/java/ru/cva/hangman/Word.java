package ru.cva.hangman;

public record Word(String hiddenWord) {

    public String getHiddenWord() {
        return hiddenWord;
    }

}
