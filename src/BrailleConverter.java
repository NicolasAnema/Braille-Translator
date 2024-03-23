// import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class BrailleConverter {

    // Define a map to store the Braille representation of characters
    private static final Map<Character, String> charToBrailleMap = new HashMap<>();
    // Define a map to store the Braille representation of punctuation numbers
    private static final Map<Character, String> numberToBrailleMap = new HashMap<>();
    // Define a map to store the Braille representation of punctuation symbols
    private static final Map<Character, String> punctuationToBrailleMap = new HashMap<>();
    private static final Map<String, Character> brailleToCharMap = new HashMap<>();
    private static final Map<String, Character> numberBrailleToCharMap = new HashMap<>();

    private static final Map<String, Character> punctuationBrailleToCharMap = new HashMap<>();

    // Populate the map with Braille representations
    static {
        // Letters
        charToBrailleMap.put('a', "1");
        charToBrailleMap.put('b', "12");
        charToBrailleMap.put('c', "14");
        charToBrailleMap.put('d', "145");
        charToBrailleMap.put('e', "15");
        charToBrailleMap.put('f', "124");
        charToBrailleMap.put('g', "1245");
        charToBrailleMap.put('h', "125");
        charToBrailleMap.put('i', "24");
        charToBrailleMap.put('j', "245");
        charToBrailleMap.put('k', "13");
        charToBrailleMap.put('l', "123");
        charToBrailleMap.put('m', "134");
        charToBrailleMap.put('n', "1345");
        charToBrailleMap.put('o', "135");
        charToBrailleMap.put('p', "1234");
        charToBrailleMap.put('q', "12345");
        charToBrailleMap.put('r', "1235");
        charToBrailleMap.put('s', "234");
        charToBrailleMap.put('t', "2345");
        charToBrailleMap.put('u', "136");
        charToBrailleMap.put('v', "1236");
        charToBrailleMap.put('w', "2456");
        charToBrailleMap.put('x', "1346");
        charToBrailleMap.put('y', "13456");
        charToBrailleMap.put('z', "1356");

        // Numbers
        numberToBrailleMap.put('1', "1");
        numberToBrailleMap.put('2', "12");
        numberToBrailleMap.put('3', "14");
        numberToBrailleMap.put('4', "145");
        numberToBrailleMap.put('5', "15");
        numberToBrailleMap.put('6', "124");
        numberToBrailleMap.put('7', "1245");
        numberToBrailleMap.put('8', "125");
        numberToBrailleMap.put('9', "24");
        numberToBrailleMap.put('0', "245");

        //punctuations
        punctuationToBrailleMap.put(',', "2");
        punctuationToBrailleMap.put('.', "256");
        punctuationToBrailleMap.put(';',"23");
        punctuationToBrailleMap.put(':',"25");
        punctuationToBrailleMap.put('=',"6-2356");
        punctuationToBrailleMap.put('!',"235");
        punctuationToBrailleMap.put('#',"456-1456");
        punctuationToBrailleMap.put('/', "456-34");
        punctuationToBrailleMap.put('?', "236");
        punctuationToBrailleMap.put('%', "46-356");
        punctuationToBrailleMap.put('^', "4-26");
        punctuationToBrailleMap.put('~', "4-35");
        punctuationToBrailleMap.put('&', "4-12346");
        punctuationToBrailleMap.put('*', "5-35");
        punctuationToBrailleMap.put('(', "5-126");
        punctuationToBrailleMap.put(')', "5-345");
        // hyphen
        punctuationToBrailleMap.put('-', "36");

        // Populate brailleToCharMap
        for (Map.Entry<Character, String> entry : charToBrailleMap.entrySet()) {
            brailleToCharMap.put(entry.getValue(), entry.getKey());
        }

        for (Map.Entry<Character, String> entry : numberToBrailleMap.entrySet()) {
            numberBrailleToCharMap.put(entry.getValue(), entry.getKey());
        }

        for (Map.Entry<Character, String> entry : punctuationToBrailleMap.entrySet()) {
            punctuationBrailleToCharMap.put(entry.getValue(), entry.getKey());
        }
    }

    // Convert text to Braille
    public static String textToBraille(String text) {
        StringBuilder braille = new StringBuilder();
        String[] words = text.split(" ");
        for (String word : words) {
            boolean isNumber = false;
            boolean isCapitalWord = word.equals(word.toUpperCase());
            if(isCapitalWord && !word.matches("[0-9]+")){
                braille.append("6-6-");
            }
            char[] textChars = word.toCharArray();
            for (char c : textChars) {
                if(Character.isDigit(c) && !isNumber){
                    isNumber=true;
                    braille.append("3456-");
                }

                if(isNumber && Character.isAlphabetic(c)){
                    isNumber = false;
                }

                if (Character.isUpperCase(c) && !isCapitalWord) {
                    // Handle space
                    braille.append("6-");
                }
                String brailleChar = null;
                if(Character.isDigit(c)){
                    brailleChar = numberToBrailleMap.get(c);
                }
                else if (Character.isAlphabetic(c)){
                    brailleChar = charToBrailleMap.get(Character.toLowerCase(c));
                }else{
                    brailleChar = punctuationToBrailleMap.get(Character.toLowerCase(c));
                    isNumber = false;
                }
                braille.append(brailleChar).append("-");

            }
            braille.deleteCharAt(braille.length() - 1);
            braille.append(" ");
        }
        return braille.toString().trim();
    }

    // Convert Braille to text
    public static String brailleToText(String braille) {
        StringBuilder text = new StringBuilder();
        String[] words = braille.split(" ");
        for (String word : words) {
            boolean isNumber = false;
            boolean isCapital = false;
            boolean isCapitalWord = false;
            String[] brailleChars = word.split("-");
            for (String brailleChar : brailleChars) {
                if ("3456".equals(brailleChar)) {
                    isNumber = true;
                    continue;
                }
                if("6".equals(brailleChar)){
                    if(isCapital){
                        isCapitalWord = true;
                    }
                    isCapital = true;
                    continue;
                }

                if(isNumber){
                    Character textChar = numberBrailleToCharMap.get(brailleChar);
                    if(textChar == null){
                        textChar = punctuationBrailleToCharMap.get(brailleChar);
                    }
                    text.append(textChar);
                }else{
                    // Convert each Braille character to text
                    Character textChar = brailleToCharMap.get(brailleChar);
                    if(textChar == null){
                        textChar = punctuationBrailleToCharMap.get(brailleChar);
                    }
                    if(isCapital){
                        text.append(Character.toUpperCase(textChar));
                        if(!isCapitalWord){
                            isCapital=false;
                        }
                    }else{
                        text.append(textChar);
                    }
                }

            }
            text.append(" ");
        }
        return text.toString().trim();
    }

    public static void main(String[] args) {
        // String text = "!";
        // String braille = textToBraille(text);
        // System.out.println("Text to Braille: " + braille);

        String convertedText = brailleToText("235");
        System.out.println("Braille to Text: " + convertedText);
    }
}