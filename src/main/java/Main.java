public class Main {
    public static void main(String[] args) {

        BoggleBoard boggle = new BoggleBoard();
        Dictionary dictionary = new Dictionary("/dictionary/russianNounsDictionary.txt");
        GameManager game = new GameManager(boggle, dictionary);
        game.startGame();
    }
}
