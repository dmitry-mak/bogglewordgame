import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoggleBoardTest {

    @Test
    void boardsShouldBeFilledAfterCreation() {
        BoggleBoard board = new BoggleBoard();
//        assertNotNull(boggle.getBoard(), "Board should be filled after creation");
        char[][] grid = board.getBoard();
        for (char[] row : grid) {
            for (char col : row) {
                assertTrue(col != ' ', "Board should be filled after creation");
            }
        }
    }

    @Test
    void boardHasCorrectSize() {
        BoggleBoard board = new BoggleBoard();
        char[][] grid = board.getBoard();

        assertAll(
                () -> assertEquals(4, grid.length, "Доска должна содержать 4 строки"),

                () -> {
                    for (char[] row : grid) {
                        assertEquals(4, row.length, "Каждая строка должна содержать 4 поля для букв");
                    }
                }
        );
    }

}