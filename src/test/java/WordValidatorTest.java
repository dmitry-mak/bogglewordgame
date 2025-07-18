import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordValidatorTest {

    private char[][] board;

    @BeforeEach
    void setUp() {
        board = new char[][]{{'А', 'Б', 'В', 'Г'},
                {'Д', 'Е', 'Ж', 'З'},
                {'И', 'К', 'Л', 'М'},
                {'Н', 'О', 'П', 'Р'}};
    }

    @Test
    void shouldSendTrueForValidWord() {

        String wordFromUser = "Роман";
        assertTrue(WordValidator.isValidWord(wordFromUser, board));
    }

    @Test
    void shouldSendFalseForLetterUsedMoreThanOnce() {
//        char[][] board = {{'А', 'Б', 'В', 'Г'},
//                {'Д', 'Е', 'Ж', 'З'},
//                {'И', 'К', 'Л', 'М'},
//                {'Н', 'О', 'П', 'Р'}};

        String wordFromUser = "Рроман";
        assertFalse(WordValidator.isValidWord(wordFromUser, board));
    }

    @Test
    void shouldSendFalseForLetterNotInBoard() {
//        char[][] board = {{'А', 'Б', 'В', 'Г'},
//                {'Д', 'Е', 'Ж', 'З'},
//                {'И', 'К', 'Л', 'М'},
//                {'Н', 'О', 'П', 'Р'}};

        String wordFromUser = "Томан";
        assertFalse(WordValidator.isValidWord(wordFromUser, board));
    }

    @Test
    void shouldSendFalseForIncorrectLanguageWord() {
//        char[][] board = {{'А', 'Б', 'В', 'Г'},
//                {'Д', 'Е', 'Ж', 'З'},
//                {'И', 'К', 'Л', 'М'},
//                {'Н', 'О', 'П', 'Р'}};

        String wordFromUser = "Pomah";
        assertFalse(WordValidator.isValidWord(wordFromUser, board));
    }
}