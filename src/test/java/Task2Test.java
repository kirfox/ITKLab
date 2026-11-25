import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.example.spring.Task2;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {

    private Task2 task2;

    @BeforeEach
    void setUp() {
        task2 = new Task2();
    }

    @Test
    void shouldCountRepeatedElements() {
        Integer[] arr = {1,2,3,4,1,42,3,1,1};
        Map<Integer, Integer> correctMap = Map.of(
                1, 4,
                2, 1,
                3, 2,
                4, 1,
                42, 1);

        Map<Integer, Integer> result = task2.count(arr);

        assertEquals(correctMap, result);
    }

    @Test
    void shouldCountUniqueElements() {
        Integer[] arr = {1, 2, 3, 4, 5};
        Map<Integer, Integer> correctMap = Map.of(
                1, 1,
                2, 1,
                3, 1,
                4, 1,
                5, 1);

        Map<Integer, Integer> result = task2.count(arr);

        assertEquals(correctMap, result);
    }

    @Test
    void shouldReturnEmptyMapForEmptyArray() {
        Integer[] arr = {};
        Map<Integer, Integer> correctMap = Map.of();

        Map<Integer, Integer> result = task2.count(arr);

        assertEquals(correctMap, result);
    }

    @Test
    void shouldCountSingleElement() {
        Integer[] arr = {42};
        Map<Integer, Integer> correctMap =Map.of(42,1);

        Map<Integer, Integer> result = task2.count(arr);

        assertEquals(correctMap, result);
    }

    @Test
    void shouldWorkWithStringArray() {
        String[] arr = {"Java", "SQL", "Java"};
        Map<String, Integer> correctMap = Map.of(
                "Java", 2,
                "SQL", 1);

        Map<String, Integer> result = task2.count(arr);

        assertEquals(correctMap, result);
    }
}
