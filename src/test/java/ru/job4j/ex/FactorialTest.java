package ru.job4j.ex;

import org.junit.jupiter.api.Test;
import ru.job4j.ex.Factorial;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FactorialTest {

    @Test
    public void whenNumberIsNegativeThenThrowException() {
        Factorial factorial = new Factorial();

        assertThrows(IllegalArgumentException.class, () -> {
            factorial.calc(-1);
        });
    }

    @Test
    public void whenNumberIsZeroThenReturnOne() {
        Factorial factorial = new Factorial();
        int result = factorial.calc(0);

        assertEquals(1, result);
    }

    @Test
    public void whenNumberIsPositiveThenCalculateFactorial() {
        Factorial factorial = new Factorial();
        int result = factorial.calc(5);

        assertEquals(120, result);
    }
}