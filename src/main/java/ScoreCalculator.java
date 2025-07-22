import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ScoreCalculator {

    private static final Map<Character, Integer> letterScores = new HashMap<>();

    static {
        letterScores.put('А', 1);
        letterScores.put('Б', 3);
        letterScores.put('В', 1);
        letterScores.put('Г', 3);
        letterScores.put('Д', 2);
        letterScores.put('Е', 1);
        letterScores.put('Ё', 3);
        letterScores.put('Ж', 5);
        letterScores.put('З', 5);
        letterScores.put('И', 1);
        letterScores.put('Й', 4);
        letterScores.put('К', 2);
        letterScores.put('Л', 2);
        letterScores.put('М', 2);
        letterScores.put('Н', 1);
        letterScores.put('О', 1);
        letterScores.put('П', 2);
        letterScores.put('Р', 1);
        letterScores.put('С', 1);
        letterScores.put('Т', 2);
        letterScores.put('У', 1);
        letterScores.put('Ф', 10);
        letterScores.put('Х', 5);
        letterScores.put('Ц', 5);
        letterScores.put('Ч', 5);
        letterScores.put('Ш', 8);
        letterScores.put('Щ', 10);
        letterScores.put('Ы', 4);
        letterScores.put('Э', 8);
        letterScores.put('Ю', 8);
        letterScores.put('Я', 3);
        letterScores.put('Ъ', 10);
        letterScores.put('Ь', 3);
    }

    public static int getScoreForSingleWord(String word) {
        if (word == null || word.isBlank()) {
            return 0;
        }

        int score = 0;
        for (char letter : word.toUpperCase().toCharArray()) {
            score += letterScores.getOrDefault(letter, 0);
        }
        return score;
    }

    public static int getTotalScore(Collection<String> words) {
        int score = 0;
        for (String word : words) {
            if (word.length() == 3 || word.length() == 4) {
                score += getScoreForSingleWord(word);
            } else if (word.length() == 5) {
                score += getScoreForSingleWord(word) * 2;
            } else if (word.length() == 6) {
                score += getScoreForSingleWord(word) * 3;
            } else if (word.length() == 7 || word.length() == 8) {
                score += getScoreForSingleWord(word) * 5;
            } else if (word.length() > 8) {
                score += getScoreForSingleWord(word) * 10;
            }
        }
        return score;
    }
}
