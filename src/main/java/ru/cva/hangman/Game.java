package ru.cva.hangman;


// N.B. Должен быть способен содержать любую игру, текстовую ли или графическую
// Необходимо упростить в будущем
public class Game {
    private final Engine engine;
    private boolean isGameEnded;


    public Game(Engine engine) {
        this.engine = engine;
    }


    public static void main(String[] args) {
        Game game = new Game(new HangmanEngine());
        ConsoleHelper.writeMessage("Добро пожаловать в Hangman");

        while (!game.isGameEnded) {
            game.engine.setUpGame();
            game.engine.playRound();
            game.isUserWinner();
            game.isGameEnded = game.isGameEnded();
        }

    }

    private boolean isGameEnded() {
        ConsoleHelper.writeMessage("Введите 0 если хотите закончить игру",
                                   "или любую другую цифру чтобы продолжить");
        return ConsoleHelper.readInt() == 0;
    }

    private void isUserWinner() {
        boolean res = this.engine.isUserAWinner();
        if (res) {
            ConsoleHelper.writeMessage("Пользователь победил в игре!");
        } else {
            ConsoleHelper.writeMessage("Пользователь проиграл");
        }
    }


}
