package ru.job4j.oop;

public class DummyDic {
    public String engToRus(String eng) {
        return "Неизвестное слово. " + eng;
    }

    public static void main(String[] args) {
        DummyDic text = new DummyDic();
        String translate = text.engToRus("hello");
        System.out.println(translate);
    }
}