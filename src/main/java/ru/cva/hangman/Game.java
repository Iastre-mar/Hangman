package ru.cva.hangman;

public class Game {
    private static Game game = new Game();
    private boolean isGameEnded = false;

    private boolean isGameEnded() {
        ConsoleHelper.writeMessage("Введите 0 если хотите закончить игру");
        return ConsoleHelper.readInt() == 0;
    }


    public static void main(String[] args) {
        ConsoleHelper.writeMessage("Добро пожаловать в Hangman");

        while (!game.isGameEnded) {
            ConsoleHelper.writeMessage("Очередной увлекательный кон завершен");
            game.isGameEnded = game.isGameEnded();
        }

    }
}
