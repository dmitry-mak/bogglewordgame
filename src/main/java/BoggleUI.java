import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.HashSet;
import java.util.Set;

public class BoggleUI extends Application {

    private final BoggleBoard board;
    private final Dictionary dictionary;
    private final GameManager gameManager;

    private final TextField inputArea = new TextField();
    //    private final Label historyLabel = new Label("Введите слово...");
    private final Label historyLabel = new Label(" ");
    private final Label timerLabel = new Label("Осталось времени: 180 сек.");
    private final GridPane boardPane = new GridPane();
    private Timeline timer;

    private Button stopButton;
    private Button restartButton;

    private final Set<String> wordsFromUser = new HashSet<>();

    public BoggleUI() {
        try {
            board = new BoggleBoard();
            dictionary = new Dictionary("/dictionary/russianNounsDictionary.txt");
            gameManager = new GameManager(board, dictionary, 180);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка инициализации игры: " + e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        setupBoard();

        inputArea.setPromptText("Введите слово");
        inputArea.setMaxWidth(200);

        Button submitButton = new Button("Отправить");
        submitButton.setOnAction(e -> processUserInput());

        inputArea.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                submitButton.fire();
            }
        });

//        Button stopButton = new Button("Завершить игру");
        stopButton = new Button("Завершить игру");
        stopButton.setOnAction(e -> endGameEarly());

        restartButton = new Button("Начать заново");
        restartButton.setDisable(true);
        restartButton.setOnAction(e -> restartGame());

        HBox buttonsBox = new HBox(10, submitButton, stopButton, restartButton);
        buttonsBox.setAlignment(Pos.CENTER_LEFT);

        HBox statusBox = new HBox(10, historyLabel, timerLabel);
        statusBox.setAlignment(Pos.CENTER_LEFT);

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(
//                new Label("Доска:"),
//                boardPane,
//                new HBox(10, inputArea, submitButton),
//                statusBox,
//                stopButton
                new Label("Доска: "),
                boardPane,
                new Label("Введите слово"),
                new HBox(10, inputArea, submitButton),
                statusBox,
                timerLabel,
//                stopButton
                buttonsBox
        );

        startTimer();

        Scene scene = new Scene(root, 400, 500);
        primaryStage.setTitle("Boggle на русском");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void setupBoard() {
        char[][] letters = board.getBoard();
        boardPane.getChildren().clear();
        boardPane.setVgap(5);
        boardPane.setHgap(5);

        for (int row = 0; row < letters.length; row++) {
            for (int col = 0; col < letters[row].length; col++) {
                Label label = new Label(String.valueOf(letters[row][col]));
                label.setFont(javafx.scene.text.Font.font("Arial", 24));
                label.setMinWidth(60);
                label.setMinHeight(60);
                label.setAlignment(Pos.CENTER);
                label.setStyle("-fx-border-color: #000; -fx-padding: 10px;");
                boardPane.add(label, col, row);
            }
        }
    }

    private void processUserInput() {
        String input = inputArea.getText().trim();
        inputArea.clear();

        if (input.isEmpty()) return;

        if (input.length() < 3) {
            historyLabel.setText("Слово слишком короткое");
            return;
        }

        if (!input.matches("[а-яА-ЯёЁ]+")) {
            historyLabel.setText("Только русские буквы");
            return;
        }

        if (!WordValidator.isValidWord(input, board.getBoard())) {
            historyLabel.setText("Невозможно составить из букв на доске");
            return;
        }

        if (!dictionary.contains(input)) {
            historyLabel.setText("Слова " + input + " нет в словаре");
            return;
        }
        if (wordsFromUser.contains(input)) {
            historyLabel.setText("Слово " + input + " уже было введено");
            return;
        }

        historyLabel.setText("Слово засчитано: " + input);
        wordsFromUser.add(input);
    }

    private void startTimer() {
        final int[] timeLeft = {180};

        timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            timeLeft[0]--;
            if (timeLeft[0] >= 0) {
                timerLabel.setText("Осталось времени: " + timeLeft[0] + " сек.");
            }
            if (timeLeft[0] == 0) {
                timerLabel.setText("Время вышло!");
                timer.stop();
                endGame();
            }
        }));
        timer.setCycleCount(180);
        timer.play();
    }

    private void endGame() {
        if (timer != null) {
            timer.stop();
        }
        inputArea.setDisable(true);
        stopButton.setDisable(true);
        restartButton.setDisable(false);
        int totalScore = ScoreCalculator.getTotalScore(wordsFromUser);
        historyLabel.setText("Игра завершена.\n" +
                "Вы составили " + wordsFromUser.size() + " слов.\n " +
                "Итоговый счет " + totalScore + " очков");
    }

    private void endGameEarly() {
        if (timer != null) {
            timer.stop();
        }
        inputArea.setDisable(true);
        stopButton.setDisable(true);
        restartButton.setDisable(false);
        int totalScore = ScoreCalculator.getTotalScore(wordsFromUser);
        historyLabel.setText("Игра досрочно завершена\n" +
                "Вы составили " + wordsFromUser.size() + " слов.\n " +
                "Итоговый счет " + totalScore + " очков");
    }

    private void restartGame() {
        board.generateNewBoard();
        startTimer();

        inputArea.setDisable(false);
        stopButton.setDisable(false);
        restartButton.setDisable(true);

        historyLabel.setText("Ну, удачи, мой маленький филолог");
        timerLabel.setText("Осталось времени");
        wordsFromUser.clear();
        setupBoard();
    }
}