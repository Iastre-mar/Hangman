package ru.cva.hangman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordsHub {
    private final List<Word> words = new ArrayList<>();
    private final Random random = new Random();

    private final String wordsPathInResources;


    public WordsHub(String wordsPathInResources) {
        this.wordsPathInResources = wordsPathInResources;
        loadWords();

    }

    public WordsHub() {
        this.wordsPathInResources = "words.txt";
        loadWords();
    }

    public Word getWord() {
        return words.get(random.nextInt(words.size()));
    }

    private void loadWords() {
        URL wordsLoc = WordsHub.class.getClassLoader()
                                     .getResource(this.wordsPathInResources);

        try (InputStream fis = Files.newInputStream(
                Path.of(wordsLoc.getPath()));
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr);
        ) {
            while (br.ready()) {
                words.add(new Word(br.readLine()));
            }
        } catch (IOException ioe) {
            ConsoleHelper.writeMessage(ioe.getMessage());
        } catch (NullPointerException npe) {
            ConsoleHelper.writeMessage("Источника слов для загрузки",
                                       "Не существует");
        }
    }

}
