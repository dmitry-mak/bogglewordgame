import java.util.Random;

public class BoggleBoard {

    //   List of cubes (6 letters on each)
    private final char[][] CUBE_SET = {
            {'А', 'В', 'Е', 'И', 'Р', 'С'},
            {'А', 'К', 'Л', 'Н', 'О', 'Т'},
            {'Б', 'Е', 'К', 'М', 'О', 'Р'},
            {'Г', 'Е', 'И', 'Н', 'С', 'Т'},
            {'Р', 'У', 'Д', 'Е', 'Л', 'О'},
            {'Ж', 'И', 'Н', 'П', 'С', 'Т'},
            {'З', 'А', 'Е', 'К', 'Н', 'У'},
            {'Й', 'В', 'Е', 'О', 'Р', 'С'},
            {'Л', 'М', 'О', 'П', 'Т', 'У'},
            {'Н', 'С', 'Т', 'У', 'Х', 'Я'},
            {'Ы', 'Д', 'Е', 'К', 'Р', 'Я'},
            {'Б', 'Е', 'Л', 'Н', 'О', 'Ц'},
            {'Г', 'Е', 'М', 'П', 'У', 'Ф'},
            {'Д', 'И', 'К', 'О', 'С', 'Щ'},
            {'Ж', 'З', 'Л', 'Н', 'Т', 'Ю'},
            {'В', 'Е', 'М', 'О', 'Р', 'Ч'}
    };

    private final Random random = new Random();

    // Array of ready cubes (Cube type objects)
    private final Cube[] cubes = new Cube[CUBE_SET.length];

    // playing board 4x4
    private final char[][] board = new char[4][4];

    public BoggleBoard() {
        // TODO: create cubes from CUBE_SET
//         TODO: shuffle cubes
//        TODO: put cubes on the board
        createCubes();
        shuffleCubes();
        fillBoard();
    }

    private void createCubes() {
        for (int i = 0; i < cubes.length; i++) {
            cubes[i] = new Cube(CUBE_SET[i]);
        }
    }

    //    Shuffle cubes
    private void shuffleCubes() {
//        TODO: make shuffling algorithm using Fisher–Yates shuffle
        for (int i = cubes.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Cube temp = cubes[i];
            cubes[i] = cubes[j];
            cubes[j] = temp;
        }
    }


    //            Set cubes on the board using shuffleCubes() method
    private void fillBoard() {
//         TODO: comes along the cubes and initiate  roll()  to get a letter
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int cubeIndex = i * 4 + j;
                board[i][j] = cubes[cubeIndex].rollCube();
            }
        }
    }

    public void printBoard() {
//        TODO: board output
        for (char[] row : board) {
            for (char letter : row) {
                System.out.printf("%3c", letter);
            }
            System.out.println();
        }
    }

    public char getCharAt(int row, int col) {
// TODO: checking diapason + return symbol
        return 0;
    }

    //    Returns whole board (for the tests)
    public char[][] getBoard() {
//        TODO: Returns copy to keep original untouched
        return board;
    }

}
