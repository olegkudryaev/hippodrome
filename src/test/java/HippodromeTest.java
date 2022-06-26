import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@RunWith(MockitoJUnitRunner.class)
public class HippodromeTest {


    @Test
    public void exceptionWhenNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        });
    }

    @Test
    public void messageWhenNameIsNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        });
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void exceptionWhenEmptyList() {
        List<Horse> horses = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(horses);
        });
    }

    @Test
    public void messageWhenEmptyList() {
        List<Horse> horses = new ArrayList<>();
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(horses);
        });
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    public void checkGetHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 49; i++) {
            horses.add(new Horse(""+i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void checkMove() {
        ArrayList<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 49; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (int i = 0; i < 49; i++) {
            Mockito.verify(horses.get(i)).move();
        }
    }

    @Test
    public void checkGetWinner() {
        List <Horse> horses = new ArrayList<>();
        horses.add(new Horse("q",1.5,2.5));
        horses.add(new Horse("w",1.5,2.5));
        horses.add(new Horse("e",4.0,5.9));
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(5.9, hippodrome.getWinner().getDistance());
    }
}
