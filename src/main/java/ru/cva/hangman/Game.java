package ru.cva.hangman;


// N.B. Должен быть способен содержать любую игру, текстовую ли или графическую
// Необходимо упростить в будущем
public class Game {
    private static Game game = new Game();

    private boolean isGameEnded;
    private boolean isUserWin;
    private Word currentWord;

    public static void main(String[] args) {
        ConsoleHelper.writeMessage("Добро пожаловать в Hangman");

        while (!game.isGameEnded) {
            game.setUpGame();
            game.playRound();
            game.isGameEnded = game.isGameEnded();
        }

    }

    private boolean isGameEnded() {
        ConsoleHelper.writeMessage("Введите 0 если хотите закончить игру");
        return ConsoleHelper.readInt() == 0;
    }

    /**
     * В начале каждого кона игры слово должно выбираться заново
     * Прочие параметры игры должны сбрасываться к исходным
     */
    private void setUpGame() {
        isUserWin = false;
        currentWord = WordsHub.getWord();
    }

    private void playRound() {
        ConsoleHelper.writeMessage("Я загадал слово " + currentWord.getMask());
        ConsoleHelper.writeMessage("Попробуй угадать букву");

        String userInput = ConsoleHelper.readString();

        if (currentWord.checkLetter(userInput)) {
            isUserWin = true;
            ConsoleHelper.writeMessage("Поздравляю! Ты угадал букву верно");
        } else {
            ConsoleHelper.writeMessage("Сожалею, ты ошибся в написании слова");
        }

        ConsoleHelper.writeMessage("Полное слово: " + currentWord.getHiddenWord());


        ConsoleHelper.writeMessage("Очередной увлекательный кон завершен");
    }
}
