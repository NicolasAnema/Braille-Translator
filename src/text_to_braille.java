import java.util.HashMap;
import java.util.Map;

public class text_to_braille {

    private static final Map<Character, String> textToBrailleMap = createTextToBrailleMap();

    public static void main(String[] args) {
        System.out.println(textToBrailleMap);
        // test translateTextToBraille
        String text = "dis mooi daar buite vandag";
        System.out.println(translateTextToBraille(text));
    }    


    public static String translateTextToBraille(String text) {
        StringBuilder braille = new StringBuilder();
        
        // Split the sentence into words to handle spaces between words
        String[] words = text.split("\\s+");
        
        for (int i = 0; i < words.length; i++) {
            if (i > 0) {
                braille.append(" ");  // Add space between words
            }
            char[] letters = words[i].toCharArray();
            for (int j = 0; j < letters.length; j++) {
                String brailleChar = textToBrailleMap.get(letters[j]);
                if (brailleChar != null) {
                    braille.append(brailleChar);
                    if (j < letters.length - 1) {
                        braille.append("-");  // Add hyphen between letters
                    }
                }
            }
        }

        return braille.toString();
    }


    private static Map<Character, String> createTextToBrailleMap() {
    Map<Character, String> map = new HashMap<>();
    map.put('a', "1");
    map.put('b', "12");
    map.put('c', "14");
    map.put('d', "145");
    map.put('e', "15");
    map.put('f', "124");
    map.put('g', "1245");
    map.put('h', "125");
    map.put('i', "24");
    map.put('j', "245");
    map.put('k', "13");
    map.put('l', "123");
    map.put('m', "134");
    map.put('n', "1345");
    map.put('o', "135");
    map.put('p', "1234");
    map.put('q', "12345");
    map.put('r', "1235");
    map.put('s', "234");
    map.put('t', "2345");
    map.put('u', "136");
    map.put('v', "1236");
    map.put('w', "2456");
    map.put('x', "1346");
    map.put('y', "13456");
    map.put('z', "1356");
    return map;
}

}


