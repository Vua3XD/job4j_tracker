package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book clean_code = new Book("Clean Code", 464);
        Book design_patterns = new Book("Design_Patterns", 240);
        Book effective_programming = new Book("Effective programming", 311);
        Book java_for_dummies = new Book("JAVA for dummies", 356);
        Book[] books = new Book[4];
        books[0] = clean_code;
        books[1] = design_patterns;
        books[2] = effective_programming;
        books[3] = java_for_dummies;

        Book temp = books[0];
        books[0] = books[3];
        books[3] = temp;

        for (int index = 0; index < books.length; index++) {
            Book book = books[index];
            System.out.println(book.getName() + " - " + book.getPage());
        }

        System.out.println("Books with name 'Clean Code':");
        for (Book book : books) {
            if (book.getName().equals("Clean Code")) {
                System.out.println(book.getName() + " - " + book.getPage());
            }
        }
    }
}