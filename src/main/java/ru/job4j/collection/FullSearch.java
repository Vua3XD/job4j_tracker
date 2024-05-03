package ru.job4j.collection;

import ru.job4j.collection.Task;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FullSearch {
    public Set<String> extractNumber(List<Task> tasks) {
        Set<String> uniqueNumbers = new HashSet<>();
        for (Task task : tasks) {
            uniqueNumbers.add(task.getNumber());
        }
        return uniqueNumbers;
    }
}