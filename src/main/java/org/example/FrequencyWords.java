package org.example;

/*
Завдання 3
Напишіть метод, який буде рахувати частоту кожного слова у файлі words.txt.

Вважаємо що:

words.txt містить лише слова в нижньому регістрі, розділені пробілом
Кожне слово містить лише літери в нижньому регістрі
Слова розділені одним або декількома пробілами, або переносом рядка
Приклад:

Для файлу words.txt із вмістом:

the day is sunny the the
the sunny is is

Метод повинен повернути результат на кшталт:

the 4
is 3
sunny 2
day 1

Увага
Результат виводу в консоль повинен бути відсортований за частотою
(спочатку йдуть слова, що зустрічаються найчастіше)
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FrequencyWords {

    public HashMap<String, Integer> readFile(String filePath) {

        HashMap<String, Integer> words = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");
                for (String word : parts) {
                words.put(word,words.getOrDefault(word,0) + 1);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return words;
    }

    private List<Map.Entry<String, Integer>> sortWords(HashMap<String, Integer> words){
        List<Map.Entry<String, Integer>> sortedWords = new ArrayList<>(words.entrySet());
        sortedWords.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        return sortedWords;
    }

    public void printFiles(HashMap<String, Integer> words){
        for (Map.Entry<String, Integer> entry : sortWords(words)) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

}


class TestFrequencyWords{
    public static void main(String[] args) {
        final String filepath = "words.txt";
        FrequencyWords frequencyWords = new FrequencyWords();

        frequencyWords.printFiles(frequencyWords.readFile(filepath));


    }
}
