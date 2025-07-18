import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryTest {

    private Dictionary dictionary;

    @BeforeEach
    public void setUp() {
        dictionary = new Dictionary("/dictionary/russianNounsDictionary.txt");
    }

    @Test
    public void shouldReturnTrueForValidWord() {
        String wordFromUser = "Роман";
        assertTrue(dictionary.contains(wordFromUser));
    }

    @Test
    public void shouldReturnFalseForInvalidWord() {
        String wordFromUser = "романка";
        assertFalse(dictionary.contains(wordFromUser));
    }

    @Test
    public void shouldReturnFalseForNullOrEmptyWord() {
        assertAll(
                () -> assertFalse(dictionary.contains("")),
                () -> assertFalse(dictionary.contains(null))
        );
    }

    @Test
    public void shouldNotBeCaseSensitive() {
        assertAll(
                () -> assertTrue(dictionary.contains("РОМАН")),
                () -> assertTrue(dictionary.contains("рОмАн"))
        );
    }
}