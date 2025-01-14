package org.example;

/*Дано текстовий файл fileNumbers.txt, в якому є список номерів телефонів (один рядок - один телефон).

Необхідно написати метод, який буде читати файл, і виводити в консоль всі валідні номери телефонів.

Телефон вважається валідним, якщо він відповідає одному з двох форматів (x - це одна цифра):
        (xxx) xxx-xxxx
        xxx-xxx-xxxx

Приклад:

Для файлу fileNumbers.txt з наступним змістом:
        987-123-4567
        123 456 7890
        (123) 456-7890

Метод повинен вивести на екран:
        987-123-4567
        (123) 456-7890*/



import java.io.*;
import java.util.regex.Pattern;

public class PhoneNumber {

    public void readFile(String filePath){
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = reader.readLine()) != null){
                if(isValidNumber(line.trim())){
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public boolean isValidNumber(String value){
        String regex = "\\(\\d{3}\\) \\d{3}-\\d{4}|\\d{3}-\\d{3}-\\d{4}";

        return Pattern.matches(regex, value);
    }


}

class TestPhoneNumber{
    public static void main(String[] args) {
        PhoneNumber testPhoneNumber = new PhoneNumber();
        String filePath = "fileNumbers.txt"; // Шлях до файлу
        testPhoneNumber.readFile(filePath);

    }
}
