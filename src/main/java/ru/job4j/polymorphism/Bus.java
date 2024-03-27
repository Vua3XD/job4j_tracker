package ru.job4j.polymorphism;

public class Bus implements Transport {
    @Override
    public void drive() {
        System.out.println("Автобус в пути!");
    }

    @Override
    public void passengers(int count0) {
        System.out.println("В автобусе " + count0 + " пасажиров");
    }

    @Override
    public double refuel(int fuelAmount) {
        double pricePerLiter = 48.9;
        return  fuelAmount * pricePerLiter;
    }
}
