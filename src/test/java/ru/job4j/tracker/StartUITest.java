package ru.job4j.tracker;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.action.CreateAction;
import ru.job4j.tracker.action.DeleteAction;
import ru.job4j.tracker.action.ExitAction;
import ru.job4j.tracker.action.FindAllAction;
import ru.job4j.tracker.action.FindByIdAction;
import ru.job4j.tracker.action.FindByNameAction;
import ru.job4j.tracker.action.ReplaceAction;
import ru.job4j.tracker.action.UserAction;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

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
        StartUI startUI = new StartUI(new ConsoleOutput());
        startUI.init(input, tracker, actions);
        actions[0].execute(input, tracker);
        assertThat(tracker.findAll().get(0).getName()).isEqualTo("Item name");
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
                new ReplaceAction(output),
                new ExitAction(output)
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

    @Test
    void whenFindAllActionExecuted() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("Item_1"));
        Item item2 = tracker.add(new Item("Item_2"));
        Output output = new StubOutput();
        FindAllAction findAllAction = new FindAllAction(output);

        String[] input = {"0"};
        MockInput mockInput = new MockInput(input);

        findAllAction.execute(mockInput, tracker);

        String expectedOutput = item1 + System.lineSeparator();
        expectedOutput += item2 + System.lineSeparator();

        assertThat(output.toString()).isEqualTo(expectedOutput);
    }

    @Test
    public void whenExecuteActionThenFindByName() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("Item_1");
        Item item2 = new Item("Item_2");
        tracker.add(item1);
        tracker.add(item2);

        StringBuilder outputBuilder = new StringBuilder();
        Output output = new Output() {
            @Override
            public void println(Object object) {
                outputBuilder.append(object).append(System.lineSeparator());
            }
        };

        FindByNameAction findByNameAction = new FindByNameAction(output);

        findByNameAction.execute(new MockInput(new String[]{"Item_1"}), tracker);

        assertEquals(item1 + System.lineSeparator(), outputBuilder.toString());
    }

    @Test
    public void whenExecuteActionThenNotFound() {
        Tracker tracker = new Tracker();

        StringBuilder outputBuilder = new StringBuilder();
        Output output = new Output() {
            @Override
            public void println(Object object) {
                outputBuilder.append(object).append(System.lineSeparator());
            }
        };

        FindByNameAction findByNameAction = new FindByNameAction(output);

        findByNameAction.execute(new MockInput(new String[]{"Item_3"}), tracker);

        assertEquals("Заявки с именем: Item_3 не найдены." + System.lineSeparator(), outputBuilder.toString());
    }

    @Test
    public void whenExecuteActionThenFindById() {
        Tracker tracker = new Tracker();
        Item item = new Item("Item_1");
        tracker.add(item);

        Output output = new Output() {
            @Override
            public void println(Object object) {
                System.out.println(object);
            }
        };

        FindByIdAction findByIdAction = new FindByIdAction(output);

        findByIdAction.execute(new MockInput(new String[]{"1"}), tracker);

    }

    @Test
    public void whenExecuteActionThenIdNotFound() {
        Tracker tracker = new Tracker();

        Output output = new Output() {
            @Override
            public void println(Object object) {
                System.out.println(object);
            }
        };

        FindByIdAction findByIdAction = new FindByIdAction(output);

        findByIdAction.execute(new MockInput(new String[]{"1"}), tracker);

    }

    @Test
    void whenInvalidExit() {
        Output output = new StubOutput();
        Input input = new MockInput(
                new String[] {"1", "0"}
        );
        Tracker tracker = new Tracker();
        UserAction[] actions = new UserAction[]{
                new ExitAction(output)
        };
        new StartUI(output).init(input, tracker, actions);
        String ln = System.lineSeparator();
        assertThat(output.toString()).isEqualTo(
                "Меню:" + ln
                        + "0. Завершить программу" + ln
                        + "Неверный ввод, вы можете выбрать: 0 .. 0" + ln
                        + "Меню:" + ln
                        + "0. Завершить программу" + ln
                        + "=== Завершение программы ===" + ln
        );
    }
}