package ru.job4j.ex;

public class FindEl {
    public static int indexOf(String[] value, String key) throws ElementNotFoundException {
        for (int i = 0; i < value.length; i++) {
            if (value[i].equals(key)) {
                return i;
            }
        }
        throw new ElementNotFoundException("Element not found in the array");
    }

    public static void main(String[] args) {
        String[] array = {"Яблоко", "Банан", "Апельсин"};
        String key = "Персик";
        try {
            int index = FindEl.indexOf(array, key);
            System.out.println("Index of " + key + " is: " + index);
        } catch (ElementNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
