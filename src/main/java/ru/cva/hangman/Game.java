package ru.cva.hangman;

public class Game {
    private static Game game = new Game();

    private boolean isGameEnded;
    private boolean isUserWin;
    private String currentWord;

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
        ConsoleHelper.writeMessage("Я загадал слово " + currentWord);
        ConsoleHelper.writeMessage("Повтори его");

        String userInput = ConsoleHelper.readString();

        if (currentWord.equals(userInput)) {
            isUserWin = true;
            ConsoleHelper.writeMessage("Поздравляю! Ты ввел слово верно");
        } else {
            ConsoleHelper.writeMessage("Сожалею, ты ошибся в написании слова");
        }


        ConsoleHelper.writeMessage("Очередной увлекательный кон завершен");
    }
}
