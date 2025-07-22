import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ScoreCalculatorTest {

    @Test
    public void shouldReturnScoreForWord() {

        String wordToTest = "Роман";

        int scoreExpected = 6;
        int scoreActual = ScoreCalculator.getScoreForSingleWord(wordToTest);

        assertAll(
                () -> assertEquals(scoreExpected, scoreActual, "Score should be equal to 6"),
                () -> assertEquals(18, ScoreCalculator.getScoreForSingleWord("ЩавЕлЬ")));
    }

    @Test
    public void shouldCountTotalScore() {
        Set<String> words = new HashSet<>();
        words.add("Аааа"); // 4 балла
        words.add("Ооооо"); // 5 баллов * 2
        words.add("Ддддддддд"); // 18 баллов * 10
//
        int scoreExpected = 4 + 5 * 2 + 18 * 10;
        int scoreActual = ScoreCalculator.getTotalScore(words);
        assertEquals(scoreExpected, scoreActual, "Total score should be equal to 4 + 5 * 2 + 18 * 10");
    }
}