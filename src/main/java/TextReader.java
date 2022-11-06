import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextReader {

    static String readFile(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        return scanner.useDelimiter("\\A").next();
    }
}
