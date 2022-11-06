import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextUtils {

    public static double calcAvgWordLength(String text) {
        long wordCount = text.split(" ").length;
        Stream<String> stream = Arrays.stream(text.split(" "));
        double wordLength = stream.mapToDouble(String::length).sum();
        return wordLength/wordCount;
    }

    public static Map<Character, Long> countCharacters(String text) {
        return text.chars().mapToObj(x -> (char) x).collect(Collectors.groupingBy(x->x, Collectors.counting()));
    }

    public static Map<String, Double> getNumberOfSubstrings(int substringLength, String text) {
        Map<String, Double> map = new HashMap<>();

        for(int i = 0; i<text.length()-substringLength;i++) {
            String substring = text.substring(i, i+substringLength);
            double currNumber = map.getOrDefault(substring, 0.0);
            map.put(substring, currNumber+1.0);
        }

        return map;
    }

    public static Map<String, Double> getProbabilityOfSubstrings(int substringLength, String text) {
        Map<String, Double> map = new HashMap<>();
        Map<String, Double> mapOfOccurrence = getNumberOfSubstrings(substringLength, text);
        int numberOfSubstrings = getNumberOfSubstrings(substringLength, text.length());

        for(String substring : mapOfOccurrence.keySet()){
            Double newValue = (double) (mapOfOccurrence.get(substring) / numberOfSubstrings);
            map.put(substring, newValue);
        }

        return map;
    }

    public static int getNumberOfSubstrings(int substringLength, int textLength) {
        return textLength - substringLength + 1;
    }

    public static double getProbabilityOfSubstring(String substring, int occurNumber, String text){
        int numberOfSubstrings = getNumberOfSubstrings(substring.length(), text.length());
        return (double) occurNumber/numberOfSubstrings;
    }

    public static String getNextChar(String substring, double predecessorProbability, Map<String, Double> probabilityMap){
        double random = Math.random();
        for(String newSubstring : probabilityMap.keySet()) {
            if(newSubstring.substring(0,substring.length()).equals(substring)) {
                random -= probabilityMap.get(newSubstring)/predecessorProbability;
                if(random <= 0.0){
                    return newSubstring.substring(newSubstring.length() - 1);
                }
            }
        }
        return null;
    }
}