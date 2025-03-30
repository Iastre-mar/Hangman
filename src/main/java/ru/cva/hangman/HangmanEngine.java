package ru.cva.hangman;


public class HangmanEngine implements Engine {
    private boolean isUserWin;
    private Word currentWord;
    private int lifeCountLeft;


    /**
     * В начале каждого кона игры слово должно выбираться заново
     * Прочие параметры игры должны сбрасываться к исходным
     */
    @Override
    public void setUpGame() {
        isUserWin = false;
        lifeCountLeft = 5; // хардкод пока
        currentWord = WordsHub.getWord();
    }

    @Override
    public void playRound() {
        ConsoleHelper.writeMessage("Я загадал слово " + currentWord.getMask());
        ConsoleHelper.writeMessage("Попробуй угадать букву:");


        while (currentWord.checkExistenceNotGuessedLetters()) {

            char userInput = ConsoleHelper.readChar();

            if (currentWord.checkLetter(userInput)) {
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

            ConsoleHelper.writeMessage("Слово:\n" + currentWord.getMask());
        }

        ConsoleHelper.writeMessage(
                "Полное слово: " + currentWord.getHiddenWord());


        ConsoleHelper.writeMessage("Очередной увлекательный кон завершен");
    }

    @Override
    public boolean isUserAWinner() {
        return isUserWin;
    }
}
