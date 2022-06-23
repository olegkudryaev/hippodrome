
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
        } );
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
    @CsvSource({
            "\s, 1.0, 1.0",
            ", 1.0, 1.0"
            // ,"фыв, 1.0, 1.0"
    })
    void exceptionWhenNameIsSpace (String name, double speed, double distance) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(name, speed, distance);
        } );
    }

    @ParameterizedTest
    @CsvSource({
            "\s, 1.0, 1.0",
            ", 1.0, 1.0"
//            ,"фыв, 1.0, 1.0"
    })
    void messageWhenNameIsSpace (String name, double speed, double distance) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(name, speed, distance);
                }
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "Test, -1.0, 1.0"
//             ,"фыв, 1.0, 1.0"
    })
    void exceptionWhenSpeedIsMinus (String name, double speed, double distance) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(name, speed, distance);
        } );
    }

    @ParameterizedTest
    @CsvSource({
            "Test, -1.0, 1.0"
//             ,"фыв, 1.0, 1.0"
    })
    void messageWhenSpeedIsMinus (String name, double speed, double distance) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(name, speed, distance);
                }
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "Test, 1.0, -1.0"
//             ,"фыв, 1.0, 1.0"
    })
    void exceptionWhenDistanceIsMinus (String name, double speed, double distance) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(name, speed, distance);
        } );
    }

    @ParameterizedTest
    @CsvSource({
            "Test, 1.0, -1.0"
//             ,"фыв, 1.0, 1.0"
    })
    void messageWhenDistanceIsMinus (String name, double speed, double distance) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(name, speed, distance);
                }
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "Test, 1.0, 1.0"
    })
    void returnParams (String name, double speed, double distance) {
        Horse horse = new Horse(name, speed, distance);
        assertEquals(horse.getName(), name);
        assertEquals(horse.getSpeed(), speed);
        assertEquals(horse.getDistance(), distance);
    }

    @ParameterizedTest
    @CsvSource({
            "Test, 1.0, 0.0"
    })
    void returnParamsZero (String name, double speed, double distance) {
        Horse horse = new Horse(name, speed);
//        assertEquals(horse.getName(), name);
//        assertEquals(horse.getSpeed(), speed);
        assertEquals(0, distance);
    }

}
