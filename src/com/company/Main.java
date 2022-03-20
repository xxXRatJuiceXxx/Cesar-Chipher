package com.company;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static String text = null;
    public static Scanner sc = new Scanner(System.in);
    //выбор функций
    public static void main(String[] args) {
        System.out.println("Выберете функцию: \n 1.Шифровка \n 2.Расшифровка \n 3.BruteFrose\n Для выбора функции напишите слово или выберете цифру");
        String funcktion = sc.nextLine();
        funk(funcktion);

    }
    //метод для проверки наличия и вызова функций
    public static void funk(String funktion) {
        try {
            if (funktion.equals("1") || funktion.equalsIgnoreCase("шифровка")) {
                System.out.println("Введите путь к файлу");
                textreader();
                System.out.println("Введите ключ:");
                int key = sc.nextInt();
                if (key<0){key = -key;}
                EncoderDecoder(text, key);
            }
            if (funktion.equals("2") || funktion.equalsIgnoreCase("расшифровка")) {
                System.out.println("Введите путь к файлу");
                textreader();
                System.out.println("Введите ключ:");
                int key = sc.nextInt();
                if (key<0){key = -key;}
                key = -key;
                EncoderDecoder(text, key);
            }
            if (funktion.equals("3") || funktion.equalsIgnoreCase("bruteforse")) {
                System.out.println("Введите путь к зашифрованному сообщению:");
                textreader();
                bruteForse(text);
            } else {
                System.out.println("Данного варианта нет, попробуйте ещё раз");
            }
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            System.out.println("Неверно введён ключ, повторите попытку");
        }
    }
    /*метод для шифрования/дешифрования
    работает по принципу шифратор: сравнивает с диапозоном чисел ,если  подходит-суммирует символ с ключём вычитатет диапозон и дабы не выходить
    за рамки, делит на диапозон и суммирует с первым символом диапозона потосле чего записывает ,
    дешифратор:
    работает так же как и шифратор только если ключи шифратора пренадлежит от резку от (0;+бекск) то ключи дешифратора  всегда противоположны ключам шифратора.
     */
    public static void EncoderDecoder(String str, int key) {
        int y = 0;
        int a = 0;
        String new_str = "";
        for (int j = 0; j < str.length(); j++) {
            a = str.charAt(j);
            if (a <= 1071 && a >= 1040) {
                y = ((str.charAt(j) + key) - 1040) % 32 + 1040;
                new_str += (char) y;
            } else if (a > 1071) {
                y = ((str.charAt(j) + key) - 1072) % 32 + 1072;
                new_str += (char) y;
            } else if (a >= 32 && a <= 47) {
                y = ((str.charAt(j) + key) - 32) % 16 + 32;
                new_str += (char) y;
            } else if (a > 47 && a <= 57) {
                y = ((str.charAt(j) + key)- 47) % 10 + 47;
                new_str += (char) y;
            } else if (a > 57 && a <= 64) {
                y = ((str.charAt(j) + key) - 57) % 7 + 57;
                new_str += (char) y;
            } else if (a > 64 && a <= 90) {
                y = ((str.charAt(j) + key) - 64) % 26 + 64;
                new_str += (char) y;
            } else if (a >= 97 && a <= 122) {
                y = ((str.charAt(j) + key) - 97) % 26 + 97;
                new_str += (char) y;
            } else if (a == '\n') {
                new_str += '\n';
            }
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("file.txt"))) {
            bufferedWriter.write(new_str);
            bufferedWriter.flush();
        } catch (Exception ex) {
            System.out.println("Недопустимые символы");
        }
        System.exit(0);

    }
    //метод для считывания дайнных
    public static void textreader() {
        String way = sc.nextLine();
        try {
            text = new String(Files.readAllBytes(Paths.get(way)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Неправильный путь или файл пуст");
            System.exit(0);
        }
    }
    /*
    ключи находятся в диапозоне (0;+беск) а метод bruteforse работает в диапозоне (+беск;0) , ищя ту самую точку
    пересечения двух  диапозонов и выводит все ключи в консоль
     */
    public static void bruteForse(String text) {
        int y =0;
        int a = 0;
        String new_str ="";
        System.out.println("До какого значения будет совершаться взлом?\n Напишите цифру:");
        int len = sc.nextInt();
        if(len <0){len = -len;}
        for (int key = 0; key < len; key++) {
            for (int i = 0; i < text.length(); i++) {
                a = text.charAt(i);
                if (a <= 1071 && a >= 1040) {
                    y = ((text.charAt(i) - key) + 1040) % 32 + 1040;
                    new_str += (char) y;
                } else if (a > 1071) {
                    y = ((text.charAt(i) - key) + 1072) % 32 + 1072;
                    new_str += (char) y;
                } else if (a >= 32 && a <= 47) {
                    y = ((text.charAt(i) - key) + 32) % 16 + 32;
                    new_str += (char) y;
                } else if (a > 47 && a <= 57) {
                    y = ((text.charAt(i) - key) + 47) % 10 + 47;
                    new_str += (char) y;
                } else if (a > 57 && a <= 64) {
                    y = ((text.charAt(i) - key)+ 57) % 7 + 57;
                    new_str += (char) y;
                } else if (a > 64 && a <= 90) {
                    y = ((text.charAt(i) - key) + 64) % 26 + 64;
                    new_str += (char) y;
                } else if (a >= 97 && a <= 122) {
                    y = ((text.charAt(i) - key) + 97) % 26 + 97;
                    new_str += (char) y;
                } else if (a == '\n') {
                    new_str += '\n';
                }
            }
            System.out.println("Ключ = " + key + " Результат взлома : " + String.valueOf(new_str) + "\n ");
            new_str = "";
        }
    }
}










