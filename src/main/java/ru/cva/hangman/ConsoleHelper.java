package ru.cva.hangman;

import java.util.Scanner;

/**
 * Класс для работы с консолью, т.к. сама игра может быть реализована
 *  как в консоли, так и через другие интерфейсы
 */
public class ConsoleHelper {
    private final Scanner scanner = new Scanner(System.in);

    public String readString() {
        return scanner.nextLine();
    }

    public int readInt() {
        return scanner.nextInt();
    }

    public void writeMessage(String message) {
        System.out.println(message);
    }


}
