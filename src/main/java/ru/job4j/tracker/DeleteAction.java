package ru.job4j.tracker;

public class DeleteAction implements UserAction {
    @Override
    public String name() {
        return "=== Удаление заявки ===";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        int id = input.askInt("Введите id: ");
        Item item = tracker.findById(id);
        tracker.delete(id);
        System.out.println(item != null ? "Заявка удалена успешно." : "Ошибка удаления заявки.");
        return false;
    }
}
