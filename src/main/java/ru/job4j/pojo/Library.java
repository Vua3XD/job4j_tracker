package ru.job4j.pojo;

public class Library {
    public static void main(String[] args) {
        Book cleanCode = new Book("Clean Code", 464);
        Book designPatterns = new Book("Design_Patterns", 240);
        Book effectiveProgramming = new Book("Effective programming", 311);
        Book javaForDummies = new Book("JAVA for dummies", 356);
        Book[] books = new Book[4];
        books[0] = cleanCode;
        books[1] = designPatterns;
        books[2] = effectiveProgramming;
        books[3] = javaForDummies;

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