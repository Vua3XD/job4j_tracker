package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

class StartUITest {
    @Test
    void whenAddItem() {
        String[] answers = {"Fix PC"};
        Input input = new MockInput(answers);
        Tracker tracker = new Tracker();
        StartUI.createItem(input, tracker);
        Item created = tracker.findAll()[0];
        Item expected = new Item("Fix PC");
        assertThat(created.getName()).isEqualTo(expected.getName());
    }

    @Test
    public void whenReplaceItem() {
        String[] input = {"1", "test name"};
        MockInput mockInput = new MockInput(input);
        Tracker testTracker = new Tracker();
        testTracker.add(new Item("Initial Name"));

        StartUI.replaceItem(mockInput, testTracker);

        assertEquals("test name", testTracker.findById(1).getName());
    }

    @Test
    public void testDeleteItem() {
        String[] input = {"1"};
        MockInput mockInput = new MockInput(input);
        Tracker testTracker = new Tracker();
        testTracker.add(new Item("Test Item"));

        StartUI.deleteItem(mockInput, testTracker);

        assertNull(testTracker.findById(1));
    }
}