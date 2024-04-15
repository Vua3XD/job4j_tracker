package ru.job4j.tracker;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.action.Create;
import ru.job4j.tracker.action.Delete;
import ru.job4j.tracker.action.Exit;
import ru.job4j.tracker.action.FindAll;
import ru.job4j.tracker.action.FindById;
import ru.job4j.tracker.action.FindByName;
import ru.job4j.tracker.action.Replace;
import ru.job4j.tracker.action.UserAction;

import static org.assertj.core.api.Assertions.assertThat;

class StartUITest {
    @Test
    void whenCreateItem() {
        Input input = new MockInput(
                new String[] {"0", "Item name", "1"}
        );
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new Create(new ConsoleOutput()),
                new Exit(new ConsoleOutput())
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
                new Replace(new ConsoleOutput()),
                new Exit(new ConsoleOutput())
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
                new Delete(new ConsoleOutput()),
                new Exit(new ConsoleOutput())
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
                new FindAll(new ConsoleOutput()),
                new Exit(new ConsoleOutput())
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
                new FindByName(new ConsoleOutput()),
                new Exit(new ConsoleOutput())
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
                new FindById(new ConsoleOutput()),
                new Exit(new ConsoleOutput())
        };
        new StartUI(new ConsoleOutput()).init(input, tracker, actions);
        assertThat(tracker.findById(item.getId())).isEqualTo(item);
    }

    @Test
    void whenReplaceItemTestOutputIsSuccessfully() {
        Output output = new StubOutput();
        Tracker tracker = new Tracker();
        Item one = tracker.add(new Item("test1"));
        String replaceName = "New Test Name";
        Input input = new MockInput(
                new String[] {"0", String.valueOf(one.getId()), replaceName, "1"}
        );
        UserAction[] actions = new UserAction[]{
                new Replace(output),
                new Exit(output)
        };
        new StartUI(output).init(input, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "Меню:" + ln
                        + "0. Изменить заявку" + ln
                        + "1. Завершить программу" + ln
                        + "=== Редактирование заявки ===" + ln
                        + "Заявка изменена успешно." + ln
                        + "Меню:" + ln
                        + "0. Изменить заявку" + ln
                        + "1. Завершить программу" + ln
                        + "=== Завершение программы ===" + ln
        );
    }
}