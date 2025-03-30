package ru.cva.hangman;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class HangmanEngine implements Engine {
    private Word currentWord;
    private int lifeCountLeft;

    // Все буквы хранятся внутри в нижнем регистре
    private Map<Character, Boolean> charactersGuessed;

    private Set<Character> enteredChars;


    /**
     * В начале каждого кона игры слово должно выбираться заново
     * Прочие параметры игры должны сбрасываться к исходным
     */
    @Override
    public void setUpGame() {
        lifeCountLeft = 5; // хардкод пока
        currentWord = WordsHub.getWord();

        charactersGuessed = new HashMap<>();
        currentWord.getHiddenWord()
                   .chars()
                   .mapToObj(i -> Character.toLowerCase((char) i))
                   .forEach(c -> charactersGuessed.put(c, false));

        enteredChars = new HashSet<>();


    }

    @Override
    public void playRound() {
        ConsoleHelper.writeMessage("Я загадал слово", getMask());
        ConsoleHelper.writeMessage("Попробуй угадать букву:");


        while (checkExistenceNotGuessedLetters()) {

            char userInput = ConsoleHelper.readChar();

            if (checkLetter(userInput)) {
                ConsoleHelper.writeMessage(
                        "Поздравляю! Ты угадал букву верно");
            } else {
                lifeCountLeft -= checkCharAlreadyEntered(userInput);
                ConsoleHelper.writeMessage("Сожалею, ты не угадал букву,",
                                           "количество оставшихся попыток:",
                                           String.valueOf(lifeCountLeft));
            }

            if (lifeCountLeft <= 0) {
                break;
            }

            ConsoleHelper.writeMessage("Слово:", getMask());
        }

        ConsoleHelper.writeMessage("Полное слово:",
                                   currentWord.getHiddenWord());

        ConsoleHelper.writeMessage("Очередной увлекательный кон завершен");
    }

    @Override
    public boolean isUserAWinner() {
        return !checkExistenceNotGuessedLetters();
    }


    private String getMask() {
        return currentWord.getHiddenWord()
                          .chars()
                          .mapToObj(i -> Character.toLowerCase((char) i))
                          .map(c -> charactersGuessed.get(
                                  c) ? c.toString() : "*")
                          .collect(Collectors.joining());
    }

    private boolean checkLetter(char letter) {
        // Внутри программы все буквы приведены к нижнему регистру
        letter = Character.toLowerCase(letter);

        boolean res = charactersGuessed.containsKey(letter);

        if (res) {
            openLetter(letter);
        }
        return res;
    }


    private boolean checkExistenceNotGuessedLetters() {
        return !charactersGuessed.values()
                                 .stream()
                                 .filter(b -> !b)
                                 .toList()
                                 .isEmpty();
    }

    private void openLetter(char letter) {
        charactersGuessed.put(letter, true);
    }

    /**
     * @return 1 в случае если буква вводится в первый раз
     * или 0 если уже была введена ранее
     */

    private int checkCharAlreadyEntered(char letter) {
        letter = Character.toLowerCase(letter);
        int indicatorLetterAlreadyEntered = 0;
        if (enteredChars.add(letter)) {
            indicatorLetterAlreadyEntered = 1;
        } else {
            ConsoleHelper.writeMessage("Буква", String.valueOf(letter),
                                       "уже вводилась ранее");
        }
        return indicatorLetterAlreadyEntered;

    }
}
