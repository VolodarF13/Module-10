package org.example;

/*Завдання 2
Є текстовий файл fileNumbers.txt.
Необхідно прочитати файл, перетворити його в список об'єктів типу User,
 і записати їх у новий файл user.json.

Формат файлу:
перший рядок - заголовок
кожний наступний рядок - окремий об'єкт, кожна колонка розділена пробілом

Приклад:
Для файлу fileNumbers.txt із вмістом:
name age
alice 21
ryan 30

необхідно створити наступний файл user.json:
        [
        {
        "name": "alice",
        "age":21
        },
        {
        "name": "ryan",
        "age":30
        }
        ]
*/

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersonProcess {

    class User {
        private final String name;
        private final int age;

        public User(String name, int age){
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
    public List<User> readFile(String filePath) {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");
                if (parts.length == 2) {
                    String name = parts[0];
                    int age = Integer.parseInt(parts[1]);
                    users.add(new User(name, age));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    public void FileToJson() {
        String inputFileName = "fileNames.txt";
        String outputFileName = "user.json";

        try {
            List<User> users = readFile(inputFileName);
            writeUsersToJson(users, outputFileName);
            System.out.println("Дані записані у файл " + outputFileName);
        } catch (IOException e) {
            System.err.println("Помилка: " + e.getMessage());
        }
    }


    public static void writeUsersToJson(List<User> users, String fileName) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Форматований JSON
        try (Writer writer = new FileWriter(fileName)) {
            gson.toJson(users, writer);
        }
    }


}

class TestPersonProcess {
    public static void main(String[] args) {
        PersonProcess testUsersStream = new PersonProcess();
        testUsersStream.FileToJson();

    }
}
