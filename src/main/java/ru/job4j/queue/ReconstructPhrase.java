package ru.job4j.queue;

import java.util.Deque;

public class ReconstructPhrase {

    private final Deque<Character> descendingElements;

    private final Deque<Character> evenElements;

    public ReconstructPhrase(Deque<Character> descendingElements, Deque<Character> evenElements) {
        this.descendingElements = descendingElements;
        this.evenElements = evenElements;
    }

    private String getEvenElements() {
        StringBuilder result = new StringBuilder();
        int index = 0;
        for (char currentChar : evenElements) {
            if (index % 2 == 0) {
                result.append(currentChar);
            }
            index++;
        }
        return result.toString();
    }

    private String getDescendingElements() {
        StringBuilder result = new StringBuilder();
        while (!descendingElements.isEmpty()) {
            result.append(descendingElements.pollLast());
        }
        return result.toString();
    }

    public String getReconstructPhrase() {
        StringBuilder result = new StringBuilder();
        result.append(getEvenElements());
        result.append(getDescendingElements());
        return result.toString();
    }
}