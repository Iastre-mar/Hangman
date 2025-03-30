package ru.cva.hangman;

import java.util.Scanner;

/**
 * Класс для работы с консолью, т.к. сама игра может быть реализована
 * как в консоли, так и через другие интерфейсы
 */
public class ConsoleHelper {
    private static final Scanner scanner = new Scanner(System.in);

    private ConsoleHelper() {
    }

    public static String readString() {
        return scanner.nextLine();
    }

    public static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                writeMessage("Ожидался ввод числа, попробуйте еще раз");
            }
        }

    }

    public static char readChar() {
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.length() == 1) {
                return userInput.charAt(0);
            }
            writeMessage("Ожидался только 1 символ, повторите ввод");
        }
    }

    public static void writeMessage(String message) {
        System.out.println(message);
    }


}
