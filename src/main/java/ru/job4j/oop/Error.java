package ru.job4j.oop;

public class Error {
    private boolean active;
    private int status;
    private String message;

    public Error() {
    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void printLogs() {
        System.out.println("Наличие ошибок: " + active);
        System.out.println("Код ошибки: " + status);
        System.out.println("Сообщение: " + message);
    }

    public static void main(String[] args) {
        Error error = new Error();
        Error errorTwo = new Error(true, 400, "Bad Request");
        error.printLogs();
        errorTwo.printLogs();
    }

}

