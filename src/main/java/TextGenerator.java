import java.util.Map;

public class TextGenerator {

    public static String generate(int textLength){
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<textLength; i++){
            builder.append(Dictionary.getRandomCharOrSpace());
        }
        return builder.toString();
    }

    public static String generateFromWords(int textLength, ) {

    }

    public static String getNextWord(String substring, double predecessorProbability, Map<String, Double> probabilityMap){
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
