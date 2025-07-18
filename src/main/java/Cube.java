import java.util.Random;

public class Cube {

    private final char[] letters;
    private final Random random = new Random();


    public Cube(char[] letters) {
        if (letters == null || letters.length != 6) {
            throw new IllegalArgumentException("Кубик должен содержать ровно 6 букв");
        }
        this.letters = letters;
    }

    public char rollCube(){
        return letters[random.nextInt(letters.length)];
    }

    public char[] getLetters() {
        return letters;
    }
}
