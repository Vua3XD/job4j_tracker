package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.Input;
import ru.job4j.tracker.MockInput;
import ru.job4j.tracker.StubOutput;
import ru.job4j.tracker.ValidateInput;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidateInputTest {

    @Test
    public void testValidInput() {
        Input input = new MockInput(new String[]{"5"});
        ValidateInput validateInput = new ValidateInput(new StubOutput(), input);
        int result = validateInput.askInt("Введите число:");
        assertThat(result).isEqualTo(5);
    }

    @Test
    public void testMultipleValidInputs() {
        Input input = new MockInput(new String[]{"1", "2", "3"});
        ValidateInput validateInput = new ValidateInput(new StubOutput(), input);
        assertThat(validateInput.askInt("Введите число:")).isEqualTo(1);
        assertThat(validateInput.askInt("Введите число:")).isEqualTo(2);
        assertThat(validateInput.askInt("Введите число:")).isEqualTo(3);
    }

    @Test
    public void testNegativeInput() {
        Input input = new MockInput(new String[]{"-10"});
        ValidateInput validateInput = new ValidateInput(new StubOutput(), input);
        assertThat(validateInput.askInt("Введите число:")).isEqualTo(-10);
    }

}