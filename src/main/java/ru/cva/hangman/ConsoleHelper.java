package ru.cva.hangman;

import java.util.Scanner;

/**
 * Класс для работы с консолью, т.к. сама игра может быть реализована
 *  как в консоли, так и через другие интерфейсы
 */
public class ConsoleHelper {
    private static final Scanner scanner = new Scanner(System.in);

    public static String readString() {
        return scanner.nextLine();
    }

    public static int readInt() {
        return scanner.nextInt();
    }

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    private ConsoleHelper() {}


}
