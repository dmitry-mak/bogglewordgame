import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

/**
 * Dictionary - простой словарь слов.
 * Загружает слова из текстового файла и позволяет проверять,
 * находится ли слово в словаре.
 */

public class Dictionary {

    private final Set<String> validWords;

    public Dictionary(String resourcePath) {
        this.validWords = loadWordFromResource(resourcePath);
    }

    private Set<String> loadWordFromResource(String resourcePath) {
        Set<String> validWords = new HashSet<>();

        try (InputStream inputStream = getClass().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("Файл не найден по адресе: " + resourcePath);
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String word = line.trim();
                    if (!(word.isEmpty())) {
                        validWords.add(word.toLowerCase());
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении словаря", e);
        }
        return validWords;
    }

    /**
     * Проверяет, содержится ли слово в словаре.
     * Также проверяет, не является ли слово null или пустой строкой.
     *
     * @param word слово, которое нужно проверить
     * @return true, если слово есть в словаре
     */
    public boolean contains(String word) {
        return word != null && !word.isEmpty() && validWords.contains(word.toLowerCase());
    }
}
