import java.util.concurrent.ThreadLocalRandom;

public class Dictionary {
    private static final char[] alphabet = "abcdefghijklmnopqrstuvwxyz ".toCharArray();

    static char getRandomCharOrSpace() {
        return alphabet[ThreadLocalRandom.current().nextInt(0, 27)];
    }
}
