package ru.job4j.ex;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class FactorialTest {

    @Test
    public void whenNumberIsNegativeThenThrowIllegalArgumentException() {
        Factorial factorial = new Factorial();
        assertThrows(IllegalArgumentException.class, () -> factorial.calc(-1));
    }
}