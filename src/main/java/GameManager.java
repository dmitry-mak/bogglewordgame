import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GameManager {

    private final BoggleBoard board;
    private final Dictionary dictionary;
    private final GameTimer gameTimer;
    private boolean isRunning = true;
    private final Set<String> wordsFromUser = new HashSet<>();

    public GameManager(BoggleBoard board, Dictionary dictionary, int gameDurationSeconds) {
        this.board = board;
        this.dictionary = dictionary;
        this.gameTimer = new GameTimer(gameDurationSeconds,
                this::onTick,
                this::onExpire);
    }

    private void onTick(String message) {

        synchronized (System.out) {

            System.out.print("\033[s");
            System.out.print("\033[1A");
            System.out.print("\033[2K");
            System.out.print(message + "\n");
            System.out.print("\033[u");
            System.out.flush();
        }
    }

    private void onExpire() {
        System.out.println("\nВремя вышло!");
        isRunning = false;
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Начинаем игру.");
        System.out.println();
        System.out.println("Введите слово или 'end' для выхода из игры");

        board.printBoard();

        System.out.println();

        new Thread(gameTimer::startTimer).start();
        while (isRunning && gameTimer.isRunning()) {
            System.out.print("Слово: ");

            String inputFromUser = scanner.nextLine().trim();
            if (inputFromUser.equalsIgnoreCase("end")) {
                isRunning = false;
                gameTimer.stopTimer();
                break;
            }

            if (inputFromUser.length() < 3) {
                System.out.println("Слово должно быть не короче 3 букв");
                continue;
            }
            if (!inputFromUser.matches("[а-яА-ЯёЁ]+")) {
                System.out.println("Слово должно состоять из букв русского алфавита");
                continue;
            }
            if (!WordValidator.isValidWord(inputFromUser, board.getBoard())) {
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
            displayRemainingTime();
        }

        System.out.println("Игра окончена");
        System.out.printf("Вы ввели %d слов", wordsFromUser.size());
    }

    private void clearScreen() {
        System.out.printf("\033[H\033[2J"); // "\033[H" - перемещение курсора в начало экрана; "\033[2J" -очистка экрана
        System.out.flush();
        board.printBoard();
        System.out.println();
    }

    private void displayRemainingTime() {
        System.out.printf("Осталось времени: %d cek.\n", gameTimer.getRemainingTime());
    }
}

