package ru.job4j.tracker;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class StartUITest {
    @Test
    void whenCreateItem() {
        Input input = new MockInput(
                new String[] {"0", "Item name", "1"}
        );
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new CreateAction(new ConsoleOutput()),
                new ExitAction(new ConsoleOutput())
        };
        new StartUI(new ConsoleOutput()).init(input, tracker, actions);
        assertThat(tracker.findAll()[0].getName()).isEqualTo("Item name");
    }

    @Test
    void whenReplaceItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        Input input = new MockInput(
                new String[] {"0", String.valueOf(item.getId()), "New item name", "1"}
        );
        UserAction[] actions = {
                new ReplaceAction(new ConsoleOutput()),
                new ExitAction(new ConsoleOutput())
        };
        new StartUI(new ConsoleOutput()).init(input, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName()).isEqualTo(replacedName);
    }

    @Test
    void whenDeleteItem() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Deleted item"));
        Input input = new MockInput(
                new String[] {"0", String.valueOf(item.getId()), "1"}
        );
        UserAction[] actions = {
                new DeleteAction(new ConsoleOutput()),
                new ExitAction(new ConsoleOutput())
        };
        new StartUI(new ConsoleOutput()).init(input, tracker, actions);
        assertThat(tracker.findById(item.getId())).isNull();
    }

    @Test
    void whenFindAllItems() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("Item_1"));
        Item item2 = tracker.add(new Item("Item_2"));
        Input input = new MockInput(new String[] {"0", "1"});
        UserAction[] actions = {
                new FindAllAction(new ConsoleOutput()),
                new ExitAction(new ConsoleOutput())
        };
        new StartUI(new ConsoleOutput()).init(input, tracker, actions);
        assertThat(tracker.findAll()).containsExactly(item1, item2);
    }

    @Test
    void whenFindItemsByName() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("Item_1"));
        Item item2 = tracker.add(new Item("Item_2"));
        Input input = new MockInput(new String[] {"0", "Item_1", "1"});
        UserAction[] actions = {
                new FindByNameAction(new ConsoleOutput()),
                new ExitAction(new ConsoleOutput())
        };
        new StartUI(new ConsoleOutput()).init(input, tracker, actions);
        assertThat(tracker.findByName("Item_1")).containsExactly(item1);
    }

    @Test
    void whenFindItemById() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Item"));
        Input input = new MockInput(new String[] {"0", String.valueOf(item.getId()), "1"});
        UserAction[] actions = {
                new FindByIdAction(new ConsoleOutput()),
                new ExitAction(new ConsoleOutput())
        };
        new StartUI(new ConsoleOutput()).init(input, tracker, actions);
        assertThat(tracker.findById(item.getId())).isEqualTo(item);
    }
}