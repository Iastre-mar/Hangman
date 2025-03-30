package ru.cva.hangman;


import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class HangmanEngine implements Engine {
    private boolean isUserWin;
    private Word currentWord;
    private int lifeCountLeft;

    // Все буквы хранятся внутри в нижнем регистре
    private  Map<Character, Boolean> charactersGuessed;



    /**
     * В начале каждого кона игры слово должно выбираться заново
     * Прочие параметры игры должны сбрасываться к исходным
     */
    @Override
    public void setUpGame() {
        isUserWin = false;
        lifeCountLeft = 5; // хардкод пока
        currentWord = WordsHub.getWord();

        charactersGuessed = new HashMap<>();
        currentWord.getHiddenWord().chars()
                  .mapToObj(i -> Character.toLowerCase((char) i))
                  .forEach(c -> charactersGuessed.put(c, false));


    }

    @Override
    public void playRound() {
        ConsoleHelper.writeMessage("Я загадал слово " + getMask());
        ConsoleHelper.writeMessage("Попробуй угадать букву:");


        while (checkExistenceNotGuessedLetters()) {

            char userInput = ConsoleHelper.readChar();

            if (checkLetter(userInput)) {
                //isUserWin = true;
                ConsoleHelper.writeMessage(
                        "Поздравляю! Ты угадал букву верно");
            } else {
                lifeCountLeft--;
                ConsoleHelper.writeMessage(
                        "Сожалею, ты не угадал букву," +
                        " количество оставшихся попыток: " +
                        lifeCountLeft);
            }

            if (lifeCountLeft <= 0){
                break;
            }

            ConsoleHelper.writeMessage("Слово:\n" + getMask());
        }

        ConsoleHelper.writeMessage(
                "Полное слово: " + currentWord.getHiddenWord());


        ConsoleHelper.writeMessage("Очередной увлекательный кон завершен");
    }

    @Override
    public boolean isUserAWinner() {
        return isUserWin;
    }


    public String getMask() {
        return currentWord.getHiddenWord().chars()
                         .mapToObj(i -> Character.toLowerCase((char) i))
                         .map(c -> charactersGuessed.get(
                                 c) ? c.toString() : "*")
                         .collect(Collectors.joining());
    }

    public boolean checkLetter(char letter) {
        // Внутри программы все буквы приведены к нижнему регистру
        letter = Character.toLowerCase(letter);
        boolean res = charactersGuessed.containsKey(letter);

        if (res) {
            openLetter(letter);
        }
        return res;
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
