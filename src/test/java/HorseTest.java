
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {

    @Test
    public void exceptionWhenNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 10.0, 10.0);
        });
    }

    @Test
    public void messageWhenNameIsNull() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(null, 10.0, 10.0);
                }
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "\s", "    "})
    void exceptionWhenNameIsSpace(String name) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(name, 0, 0);
        });
    }


    @ParameterizedTest
    @ValueSource(strings = {"", "\s", "    "})
    void messageWhenNameIsSpace(String name) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(name, 0, 0);
                }
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void exceptionWhenSpeedIsMinus() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("asd", -1, 1);
        });
    }


    @Test
    void messageWhenSpeedIsMinus() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse("asd", -1, 1);
                }
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

   @Test
    void exceptionWhenDistanceIsMinus() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("qwe", 1, -1);
        });
    }

    @Test
    void messageWhenDistanceIsMinus() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse("qwe", 1, -1);
                }
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "Test, 1.0, 1.0"
    })
    void returnParams(String name, double speed, double distance) {
        Horse horse = new Horse(name, speed, distance);
        assertEquals(horse.getName(), name);
        assertEquals(horse.getSpeed(), speed);
        assertEquals(horse.getDistance(), distance);
    }

    @Test
    void returnParamsZero() {
        Horse horse = new Horse("asd", 1);
        assertEquals(0, horse.getDistance());
    }

    @Test
    void checkMoveUsesGetRandom() {
        try(MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)){
            new Horse("asd", 1, 1).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.2, 0.5, 1.0, 999.999, 0.0})
    void move (double random) {
        try (MockedStatic<Horse>mockedStatic = mockStatic(Horse.class)){
            Horse horse = new Horse("qwer", 31, 283);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);

            horse.move();

            assertEquals(283+31*random,horse.getDistance());
        }
    }
}
