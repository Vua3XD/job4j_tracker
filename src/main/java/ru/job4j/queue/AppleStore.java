package ru.job4j.queue;

import java.util.Queue;

public class AppleStore {
    private final Queue<Customer> queue;

    private final int count;

    public AppleStore(Queue<Customer> queue, int count) {
        this.queue = queue;
        this.count = count;
    }

    public String getLastHappyCustomer() {
        Customer lastCustomer = null;
        for (Customer customer : queue) {
            lastCustomer = customer;
        }
        return lastCustomer.name();
    }

    public String getFirstUpsetCustomer() {
        for (Customer customer : queue) {
            if (customer.amount() > count) {
                return customer.name();
            }
        }
        return "";
    }
}