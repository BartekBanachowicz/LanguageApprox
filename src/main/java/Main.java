import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
//        zad1(400);
//        zad2("src/main/resources/norm_hamlet.txt");
//        if(args.length==3){
//            makeMultiPrediction(args[0], Integer.parseInt(args[1]), args[2]);
//
//        } else {
//            makeMultiPrediction("src/main/resources/norm_hamlet.txt", 300, "src/main/resources/new_file.txt");
//        }
         zad1_1();

    }

    private static void zad1(int textLength) {
        System.out.println("Zad. 1 -------\n");
        String generatedText01 = TextGenerator.generate(textLength);
        System.out.println(generatedText01 + "\n");
        double avgWordLength = TextUtils.calcAvgWordLength(generatedText01);
        System.out.println("Avg word length: " + avgWordLength + "\n");
        System.out.println("--------------\n");
    }

    private static void zad2(String fileName) throws FileNotFoundException {
        System.out.println("Zad. 2 -------\n");
        String text = TextReader.readFile(fileName);
        Map<Character, Long> map = TextUtils.countCharacters(text);
        for (Character character : map.keySet()) {
            System.out.println(character + ": " + map.get(character) + "\n");
        }
        System.out.println("--------------\n");
    }

    private static void makeMultiPrediction(String fileName, int textLength, String outputFilename) throws IOException {
        String output = predict(1, fileName, textLength) +
                predict(3, fileName, textLength) +
                predict(5, fileName, textLength) +
                predict(7, fileName, textLength);
        System.out.println(output);
        TextWriter.write(output, outputFilename);
    }

    private static String predict(int substringLength, String fileName, int textLength) throws FileNotFoundException {
        String text = getPrediction(substringLength, fileName, textLength);
        return buildOutput(substringLength, text);
    }

    private static String getPrediction(int substringLength, String fileName, int textLength) throws FileNotFoundException {

        String text = TextReader.readFile(fileName);
        StringBuilder builder = new StringBuilder("probability");
        Map<String, Double> baseSubstringsProbability = TextUtils.getProbabilityOfSubstrings(substringLength, text);
        Map<String, Double> newSubstringsProbability = TextUtils.getProbabilityOfSubstrings(substringLength + 1, text);

        for (int i = 0; i < textLength; i++) {
            String substring = builder.substring(builder.length() - substringLength);
            builder.append(TextUtils.getNextChar(substring, baseSubstringsProbability.get(substring), newSubstringsProbability));
        }

        return builder.toString();
    }

    private static String buildOutput(int substringLength, String text) {
        return "Approximation " + substringLength + "th order:\n" + text + "\n" + "Avg word length: "
                + TextUtils.calcAvgWordLength(text) + "\n--------------\n";
    }

    public static void zad1_1() throws FileNotFoundException {
        System.out.println("Zad. 1.1 -------\n");
        String text = TextReader.readFile("src/main/resources/norm_wiki_sample.txt");
        int numOfWords = WordUtils.getNumOfAllWords(text);
        HashMap<String, Double> wordsMap = WordUtils.getWordOccurMap(text);
        HashMap<String, Double> probMap = WordUtils.getProbabilityOfWords(wordsMap, numOfWords);

//        for(String key : probMap.keySet()) {
//            System.out.println(key + " : " + probMap.get(key));
//        }
        double[] array = probMap.values().stream().mapToDouble(x -> x).sorted().toArray();
        double sum = 0.0;
        for(int i=0; i<30_000; i++) {
            sum += array[array.length-1-i];
            if(i == 5_999) {
                System.out.println(sum);
            }
        }
        System.out.println(sum);
    }
}
