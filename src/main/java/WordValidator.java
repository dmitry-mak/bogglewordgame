import java.util.HashMap;
import java.util.Map;

public class WordValidator {

/**  WordValidator - проверяет соответствие слова, полученного от пользователя
 на соответствие правилам игры:
- кириллица;
- не менее 3х букв
- использование букв только из набора, представленного на доске
 */


    public static boolean isValidWord(String wordFromUser, char[][] lettersOnBoard) {

//       Считаем частоту букв на доске
        Map<Character, Integer> boardLetterCounts = new HashMap<>();
        for (char[] row : lettersOnBoard) {
            for (char letter : row) {
                boardLetterCounts.put(letter, boardLetterCounts.getOrDefault(letter, 0) + 1);
            }
        }

        wordFromUser = wordFromUser.toUpperCase();

        // Проверяем каждую букву введенного слова
        for (char letter : wordFromUser.toCharArray()) {
            if (!boardLetterCounts.containsKey(letter)) {
                return false;  // введенной буквы нет на доске
            }
            int count = boardLetterCounts.get(letter);
            if (count <= 0) {
                return false; // буква уже использована нужное число раз
            }
            boardLetterCounts.put(letter, count - 1);
        }
        return true;
    }

    public static String validateWord(String wordFromUser, char[][] lettersOnBoard) {
        if (wordFromUser == null || wordFromUser.isBlank()) {
            return "Вы забыли ввести слово";
        }
        if (wordFromUser.length() < 3) {
            return "Слово не может быть короче 3 букв";
        }
        if (isValidWord(wordFromUser, lettersOnBoard)) {
            return "Слово " + wordFromUser + " засчитано!";
        } else {
            return "Это слово нельзя составить из букв на доске";
        }
    }
}
