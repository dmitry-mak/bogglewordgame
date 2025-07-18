import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GameManager {

    private final BoggleBoard board;
    private final Dictionary dictionary;
    private final Set<String> wordsFromUser = new HashSet<>();

    public GameManager(BoggleBoard board, Dictionary dictionary) {
        this.board = board;
        this.dictionary = dictionary;
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Начинаем игру.");
        System.out.println();
        System.out.println("Введите слово или 'end' для выхода из игры");

        board.printBoard();

        while (true) {
            System.out.printf("Слово: ");

            String inputFromUser = scanner.nextLine().trim();

            if (inputFromUser.equalsIgnoreCase("end")) {
                break;
            }

            if (inputFromUser.length()<3){
                System.out.println("Слово должно быть не короче 3 букв");
                continue;
            }
            if (!inputFromUser.matches("[а-яА-ЯёЁ]+")) {
                System.out.println("Слово должно состоять из букв русского алфавита");
                continue;
            }
            if (!WordValidator.isValidWord(inputFromUser, board.getBoard())){
                System.out.println("Слово нельзя составить из букв на доске");
                continue;
            }
            if (!dictionary.contains(inputFromUser)) {
                System.out.println("Такого слова нет в словаре");
                continue;
            }
            if (wordsFromUser.contains(inputFromUser)) {
                System.out.println("Вы уже вводили это слово");
                continue;
            }

            wordsFromUser.add(inputFromUser);
            System.out.println("Слово засчитано");
        }

        System.out.println("Игра окончена");
        System.out.printf("Вы ввели %d слов", wordsFromUser.size()) ;
    }
}
