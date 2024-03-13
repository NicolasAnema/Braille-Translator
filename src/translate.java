package src;
import java.util.HashMap;
import java.util.Map;

public class translate {

    private static final Map<String, String> brailleToTextMap = createBrailleToTextMap();
    // create numbers map
    private static final Map<String, String> brailleToNumbersMap = createBrailleToNumbersMap();

    public static void main(String[] args) {
            // print braille to text map
            brailleToTextMap.forEach((braille, text) -> System.out.println("Braille: " + braille + " -> Text: " + text));

            // print braille to numbers map
            brailleToNumbersMap.forEach((braille, number) -> System.out.println("Braille: " + braille + " -> Number: " + number));

            // testing translateBrailleToText
            String brailleSentence = "6-145-24-234 134-135-135-24 145-1-1-1235 12-136-24-2345-15 1236-1-1345-145-1-1245-256";
            System.out.println("Braille: " + brailleSentence + " -> Text: " + translateBrailleToText(brailleSentence));

        }

    private static Map<String, String> createBrailleToTextMap() {
        Map<String, String> map = new HashMap<>();
        // alphabet
        map.put("1", "a");
        map.put("12", "b");
        map.put("14", "c");
        map.put("145", "d");
        map.put("15", "e");
        map.put("124", "f");
        map.put("1245", "g");
        map.put("125", "h");
        map.put("24", "i");
        map.put("245", "j");
        map.put("13", "k");
        map.put("123", "l");
        map.put("134", "m");
        map.put("1345", "n");
        map.put("135", "o");
        map.put("1234", "p");
        map.put("12345", "q");
        map.put("1235", "r");
        map.put("234", "s");
        map.put("2345", "t");
        map.put("136", "u");
        map.put("1236", "v");
        map.put("2456", "w");
        map.put("1346", "x");
        map.put("13456", "y");
        map.put("1356", "z");

        // numbers
        map.put("3456-1", "1");
        map.put("3456-12", "2");
        map.put("3456-14", "3");
        map.put("3456-145", "4");
        map.put("3456-15", "5");
        map.put("3456-124", "6");
        map.put("3456-1245", "7");
        map.put("3456-125", "8");
        map.put("3456-24", "9");
        map.put("3456-245", "0");
        
        return map;
    }

    private static Map<String, String> createBrailleToNumbersMap() {
        Map<String, String> numbersMap = new HashMap<>();
        numbersMap.put("1", "1");
        numbersMap.put("12", "2");
        numbersMap.put("14", "3");
        numbersMap.put("145", "4");
        numbersMap.put("15", "5");
        numbersMap.put("124", "6");
        numbersMap.put("1245", "7");
        numbersMap.put("125", "8");
        numbersMap.put("24", "9");
        numbersMap.put("245", "0");
        return numbersMap;
    }

    private static String translateBrailleToText(String brailleSentence) {
        StringBuilder translatedText = new StringBuilder();
        String[] words = brailleSentence.split(" ");
        boolean capitalizeNext = false;
        boolean isNumber = false;
    
        for (String word : words) {
            String[] brailleChars = word.split("-");
            StringBuilder translatedWord = new StringBuilder();
    
            for (String brailleChar : brailleChars) {
                String textChar = null; // reset

                // check for capitalization
                if ("6".equals(brailleChar)) {
                    capitalizeNext = true;
                    continue;

                // number check
                } else if ("3456".equals(brailleChar)) {
                    isNumber = true;
                    continue;
                }
                
                // map braille to number
                if (isNumber) {
                    textChar = brailleToNumbersMap.get(brailleChar);
                    if (textChar == null) {
                        isNumber = false;
                    }
                }
                
                // map braille to text
                if (textChar == null) {
                    textChar = brailleToTextMap.get(brailleChar);
                }
                
                // capitalize next letter
                if (textChar != null) {
                    if (capitalizeNext) {
                        translatedWord.append(textChar.toUpperCase());
                        capitalizeNext = false;
                    } else {
                        translatedWord.append(textChar);
                    }
                }
            }
    
            translatedText.append(translatedWord).append(" ");
        }
    
        return translatedText.toString().trim();
    }
}    