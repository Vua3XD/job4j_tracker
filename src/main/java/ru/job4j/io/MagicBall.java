package ru.job4j.io;
import java.util.Scanner;
import java.util.Random;

public class MagicBall {
    public static void main(String[] args) {
        System.out.println("Я великий Оракул. Что ты хочешь узнать?");
        System.out.println("Будет ли завтра дождь?");

        Scanner scanner = new Scanner(System.in);

        int answer = new Random().nextInt(3);

        switch (answer) {
            case 0:
                System.out.println("Да");
                break;
            case 1:
                System.out.println("Нет");
                break;
            default:
                System.out.println("Может быть");
                break;
        }
    }
}
