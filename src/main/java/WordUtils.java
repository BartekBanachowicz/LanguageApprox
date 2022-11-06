import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class WordUtils {

    public static HashMap<String, Double> getWordOccurMap(String text) {
        ArrayList<String> words = new ArrayList<>(Arrays.asList(text.split(" ")));
        HashMap<String, Double> wordsMap = new HashMap<>();
        for(String word : words) {
            if(!wordsMap.containsKey(word)) {
                wordsMap.put(word, 1.0);
            } else {
                double currVal = wordsMap.get(word);
                wordsMap.put(word, currVal+1);
            }
        }
        return wordsMap;
    }

    public static HashMap<String, Double> getProbabilityOfWords(HashMap<String, Double> occurMap, int numOfWords) {
        HashMap<String, Double> probMap = new HashMap<String, Double>();
        for(String key : occurMap.keySet()) {
            double occur = occurMap.get(key);
            probMap.put(key, occur/numOfWords*100);
        }
        return probMap;
    }

    public static int getNumOfAllWords(String text) {
        return Arrays.asList(text.split(" ")).size();
    }
}
