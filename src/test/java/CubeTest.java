import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CubeTest {

    @Test
    void cubeShouldStoreLetters() {
        char[] letters = {'А', 'Б', 'В', 'Г', 'Д', 'Е'};
        Cube cube = new Cube(letters);
        char result = cube.rollCube();
        assertTrue(new String(letters).indexOf(result) >= 0, "Cube should store letters from constructor");
    }

    @Test
    void shouldThrowExceptionForInvalidLettersQuantity() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> new Cube(new char[]{'А', 'Б', 'В'})),
                () -> assertThrows(IllegalArgumentException.class, () -> new Cube(new char[]{'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж'}))
        );
    }
}