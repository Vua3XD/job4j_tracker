package ru.job4j.pojo;

import java.util.Date;

public class Colledge {
    public static void main(String[] args) {
        Student student = new Student();
        student.setFio("Вязовик Сергей");
        student.setGroup("Стажер");
        student.setDate("19.09.2023 16:39");
        System.out.println(student.getFio() + " " + student.getGroup() + " " + student.getDate());
    }
}
