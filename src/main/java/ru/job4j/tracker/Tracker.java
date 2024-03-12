package ru.job4j.tracker;

public class Tracker {
    private final Item[] items = new Item[100];
    private int ids = 1;
    private int size = 0;

    public Item add(Item item) {
        item.setId(ids++);
        items[size++] = item;
        return item;
    }

    public Item[] findAll() {
        Item[] result = new Item[size];
        for (int i = 0; i < size; i++) {
            result[i] = items[i];
        }
        return result;
    }

    public Item[] findByName(String key) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (items[i].getName().equals(key)) {
                count++;
            }
        }
        Item[] result = new Item[count];
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (items[i].getName().equals(key)) {
                result[index++] = items[i];
            }
        }
        return result;
    }

    public Item findById(int id) {
        Item result = null;
        for (int i = 0; i < size; i++) {
            if (items[i].getId() == id) {
                result = items[i];
                break;
            }
        }
        return result;
    }
}