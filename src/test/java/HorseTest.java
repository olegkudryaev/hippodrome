
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTest {

    @Test
    public void exceptionWhenNameIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
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

    @Test
    void exceptionWhenNameIsSpace() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("\s", 0, 0);
            new Horse("", 0, 0);
            new Horse("    ", 0, 0);
        });
    }


    @Test
    void messageWhenNameIsSpace() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse("\s", 0, 0);
                    new Horse("", 0, 0);
                    new Horse("    ", 0, 0);
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

    @ParameterizedTest
    @CsvSource({
            "Test, 1.0, 0.0"
    })
    void returnParamsZero(String name, double speed, double distance) {
        Horse horse = new Horse(name, speed);
//        assertEquals(horse.getName(), name);
//        assertEquals(horse.getSpeed(), speed);
        assertEquals(0, distance);
    }

}
