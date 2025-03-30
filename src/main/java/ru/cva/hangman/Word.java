package ru.cva.hangman;


import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


// Уродливо и нелогично
public class Word {
    private final String hiddenWord;
    private final Map<Character, Boolean> charactersGuessed;

    public Word(String hiddenWord) {
        this.hiddenWord = hiddenWord;
        charactersGuessed = new HashMap<>();
        hiddenWord.chars()
                  .mapToObj(i -> (char) i)
                  .forEach(c -> charactersGuessed.put(c, false));
    }

    public String getMask() {
        return hiddenWord.chars()
                         .mapToObj(i -> (char) i)
                         .map(c -> charactersGuessed.get(
                                 c) ? c.toString() : "*")
                         .collect(Collectors.joining());
    }

    public boolean checkLetter(char letter) {
        boolean res = charactersGuessed.containsKey(letter);

        if (res) {
            openLetter(letter);
        }
        return res;
    }

    public String getHiddenWord() {
        return hiddenWord;
    }

    public boolean checkExistenceNotGuessedLetters() {
        return !charactersGuessed.values()
                                .stream()
                                .filter(b -> b == false)
                                .toList()
                                .isEmpty();
    }

    private void openLetter(char letter) {
        charactersGuessed.put(letter, true);
    }

}
