package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        String[] leftParts = left.split("\\.");
        String[] rightParts = right.split("\\.");
        int leftNum = Integer.parseInt(leftParts[0]);
        int rightNum = Integer.parseInt(rightParts[0]);
        return Integer.compare(leftNum, rightNum);

    }
}