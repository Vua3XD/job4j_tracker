package ru.job4j.tracker.action;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Output;
import ru.job4j.tracker.Tracker;

public class DeleteAction implements UserAction {
    private final Output output;

    public DeleteAction(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "=== Удаление заявки ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        int id = input.askInt("Введите id: ");
        Item item = tracker.findById(id);
        if (item != null) {
            tracker.delete(id);
            output.println("Заявка удалена успешно.");
        } else {
            output.println("Заявка с таким id не найдена.");
        }
        return true;
    }
}
