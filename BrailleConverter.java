import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * This class provides translation functionality.
 */
public class BrailleConverter {
    /**
     * The entry point of the program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        // checking for correct number of args
        if (args.length != 4) {
            System.err.println("Input Error - Invalid number of arguments");
            System.exit(1);
        }
            String gui = args[0];
            String dir = args[1];
            String level = args[2];
            String path = args[3];

            // checking valid GUI option
            if (!gui.equals("GUI") && !gui.equals("noGUI")) {
                System.err.println("Input Error - Invalid GUI argument");
                System.exit(1);
            }
            
            if (!dir.equals("b2t") && !dir.equals("t2b")) {
                System.err.println("Input Error - Invalid direction");
                System.exit(1);
            }

            // checking valid level
            if (!Arrays.asList("1.0", "1.1", "1.2", "1.3", "2.1", "2.2", "2.3", "2.4", 
                               "3.1", "3.2", "3.3", "4.1").contains(level)) {
                System.err.println("Input Error - Invalid level");
                System.exit(1);
            }

            String line = null;

            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                line = reader.readLine();

                if (path.endsWith(".brf")) {
                    if (line == null || !line.matches("[0-9-]+")) {
                        System.err.println("Input Error - Invalid file content");
                    }
                } else if (path.endsWith(".txt")) {
                    if (line == null || !line.matches(".*[a-zA-Z]+.*")) {
                        System.err.println("Input Error - Invalid file content");
                    }
                }
            } catch (IOException e) {
                System.err.println("Input Error - Invalid or missing file");
                System.exit(1);
            }

                if (path.endsWith(".brf") && dir.equals("b2t")) {
                    try {
                        String fileName = Paths.get(path).getFileName().toString().
                        replace(".brf", "");
                        String newFilePath = "out/" + fileName + "_b2t.txt";
                        FileWriter fileWriter = new FileWriter(newFilePath);
                        if (line != null) {
                            fileWriter.write(brailleToText(line));
                            fileWriter.write("\n"); // New line
                        }
                        fileWriter.close();

                    } catch (IOException e) {
                        System.out.println("An error occurred while writing to the file.");
                        e.printStackTrace();
                    }

                } else if (path.endsWith(".txt")) {
                    try {
                        String fileName = Paths.get(path).getFileName().toString().
                        replace(".txt", "");
                        String newFilePath = "out/" + fileName + "_t2b.brf";
                        FileWriter fileWriter = new FileWriter(newFilePath);
                        if (line != null) {
                            fileWriter.write(textToBraille(line));
                            fileWriter.write("\n");
                        }
                        fileWriter.close();

                    } catch (IOException e) {
                        System.out.println("An error occurred while writing to the file.");
                        e.printStackTrace();
                    }
                }
            
        }

    // Text to braille maps
    // Letters
    private static final Map<Character, String> CHAR_TO_BRAILLE_MAP = new HashMap<>();
    // Numbers
    private static final Map<Character, String> NUMBER_TO_BRAILLE_MAP = new HashMap<>();
    // Punctuations
    private static final Map<Character, String> PUNCTUATION_TO_BRAILLE_MAP = new HashMap<>();

    // Braille to text maps
    // Letters
    private static final Map<String, Character> BRAILLE_TO_CHAR_MAP = new HashMap<>();
    // Numbers
    private static final Map<String, Character> NUMBER_BRAILLE_TO_CHAR_MAP = new HashMap<>();
    // Punctuations
    private static final Map<String, Character> PUNCTUATION_BRAILLE_TO_CHAR_MAP = new HashMap<>();

    // Populate the map with Braille representations
    static {
        // Letters
        CHAR_TO_BRAILLE_MAP.put('a', "1");
        CHAR_TO_BRAILLE_MAP.put('b', "12");
        CHAR_TO_BRAILLE_MAP.put('c', "14");
        CHAR_TO_BRAILLE_MAP.put('d', "145");
        CHAR_TO_BRAILLE_MAP.put('e', "15");
        CHAR_TO_BRAILLE_MAP.put('f', "124");
        CHAR_TO_BRAILLE_MAP.put('g', "1245");
        CHAR_TO_BRAILLE_MAP.put('h', "125");
        CHAR_TO_BRAILLE_MAP.put('i', "24");
        CHAR_TO_BRAILLE_MAP.put('j', "245");
        CHAR_TO_BRAILLE_MAP.put('k', "13");
        CHAR_TO_BRAILLE_MAP.put('l', "123");
        CHAR_TO_BRAILLE_MAP.put('m', "134");
        CHAR_TO_BRAILLE_MAP.put('n', "1345");
        CHAR_TO_BRAILLE_MAP.put('o', "135");
        CHAR_TO_BRAILLE_MAP.put('p', "1234");
        CHAR_TO_BRAILLE_MAP.put('q', "12345");
        CHAR_TO_BRAILLE_MAP.put('r', "1235");
        CHAR_TO_BRAILLE_MAP.put('s', "234");
        CHAR_TO_BRAILLE_MAP.put('t', "2345");
        CHAR_TO_BRAILLE_MAP.put('u', "136");
        CHAR_TO_BRAILLE_MAP.put('v', "1236");
        CHAR_TO_BRAILLE_MAP.put('w', "2456");
        CHAR_TO_BRAILLE_MAP.put('x', "1346");
        CHAR_TO_BRAILLE_MAP.put('y', "13456");
        CHAR_TO_BRAILLE_MAP.put('z', "1356");

        // Numbers
        NUMBER_TO_BRAILLE_MAP.put('1', "1");
        NUMBER_TO_BRAILLE_MAP.put('2', "12");
        NUMBER_TO_BRAILLE_MAP.put('3', "14");
        NUMBER_TO_BRAILLE_MAP.put('4', "145");
        NUMBER_TO_BRAILLE_MAP.put('5', "15");
        NUMBER_TO_BRAILLE_MAP.put('6', "124");
        NUMBER_TO_BRAILLE_MAP.put('7', "1245");
        NUMBER_TO_BRAILLE_MAP.put('8', "125");
        NUMBER_TO_BRAILLE_MAP.put('9', "24");
        NUMBER_TO_BRAILLE_MAP.put('0', "245");

        //punctuations
        PUNCTUATION_TO_BRAILLE_MAP.put(',', "2");
        PUNCTUATION_TO_BRAILLE_MAP.put('.', "256");
        PUNCTUATION_TO_BRAILLE_MAP.put(';', "23");
        PUNCTUATION_TO_BRAILLE_MAP.put(':', "25");
        PUNCTUATION_TO_BRAILLE_MAP.put('=', "6-2356");
        PUNCTUATION_TO_BRAILLE_MAP.put('!', "235");
        PUNCTUATION_TO_BRAILLE_MAP.put('#', "456-1456");
        PUNCTUATION_TO_BRAILLE_MAP.put('/', "456-34");
        PUNCTUATION_TO_BRAILLE_MAP.put('?', "236");
        PUNCTUATION_TO_BRAILLE_MAP.put('%', "46-356");
        PUNCTUATION_TO_BRAILLE_MAP.put('^', "4-26");
        PUNCTUATION_TO_BRAILLE_MAP.put('~', "4-35");
        PUNCTUATION_TO_BRAILLE_MAP.put('&', "4-12346");
        PUNCTUATION_TO_BRAILLE_MAP.put('*', "5-35");
        PUNCTUATION_TO_BRAILLE_MAP.put('(', "5-126");
        PUNCTUATION_TO_BRAILLE_MAP.put(')', "5-345");

        // Populate brailleToCharMap
        for (Map.Entry<Character, String> entry : CHAR_TO_BRAILLE_MAP.entrySet()) {
            BRAILLE_TO_CHAR_MAP.put(entry.getValue(), entry.getKey());
        }

        for (Map.Entry<Character, String> entry : NUMBER_TO_BRAILLE_MAP.entrySet()) {
            NUMBER_BRAILLE_TO_CHAR_MAP.put(entry.getValue(), entry.getKey());
        }

        for (Map.Entry<Character, String> entry : PUNCTUATION_TO_BRAILLE_MAP.entrySet()) {
            PUNCTUATION_BRAILLE_TO_CHAR_MAP.put(entry.getValue(), entry.getKey());
        }
    }

    /**
     * Converts a given text into Braille representation.
     *
     * @param text the text to be converted
     * @return the Braille representation of the text
     */
    public static String textToBraille(String text) {
        StringBuilder braille = new StringBuilder();
        String[] words = text.split(" ");
        for (String word : words) {
            boolean isNumber = false;
            boolean isCapitalWord = word.equals(word.toUpperCase());
            if (isCapitalWord && !word.matches("[0-9]+")) {
                braille.append("6-6-");
            }
            char[] textChars = word.toCharArray();
            for (char c : textChars) {
                if (Character.isDigit(c) && !isNumber) {
                    isNumber = true;
                    braille.append("3456-");
                }

                if (isNumber && Character.isAlphabetic(c)) {
                    isNumber = false;
                }

                if (Character.isUpperCase(c) && !isCapitalWord) {
                    // Handle space
                    braille.append("6-");
                }
                String brailleChar = null;
                if (Character.isDigit(c)) {
                    brailleChar = NUMBER_TO_BRAILLE_MAP.get(c);
                } else if (Character.isAlphabetic(c)) {
                    brailleChar = CHAR_TO_BRAILLE_MAP.get(Character.toLowerCase(c));
                } else {
                    brailleChar = PUNCTUATION_TO_BRAILLE_MAP.get(Character.toLowerCase(c));
                    isNumber = false;
                }
                braille.append(brailleChar).append("-");

            }
            braille.deleteCharAt(braille.length() - 1);
            braille.append(" ");
        }
        return braille.toString().trim();
    }

    /**
     * Converts a given Braille representation into text.
     *
     * @param braille the Braille representation to be converted
     * @return the text representation of the Braille
     */
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
                if ("6".equals(brailleChar)) {
                    if (isCapital) {
                        isCapitalWord = true;
                    }
                    isCapital = true;
                    continue;
                }

                if (isNumber) {
                    Character textChar = NUMBER_BRAILLE_TO_CHAR_MAP.get(brailleChar);
                    if (textChar == null) {
                        textChar = PUNCTUATION_BRAILLE_TO_CHAR_MAP.get(brailleChar);
                    }
                    text.append(textChar);
                } else {
                    // Convert each Braille character to text
                    Character textChar = BRAILLE_TO_CHAR_MAP.get(brailleChar);
                    if (textChar == null) {
                        textChar = PUNCTUATION_BRAILLE_TO_CHAR_MAP.get(brailleChar);
                    }
                    if (isCapital) {
                        text.append(Character.toUpperCase(textChar));
                        if (!isCapitalWord) {
                            isCapital = false;
                        }
                    } else {
                        text.append(textChar);
                    }
                }

            }
            text.append(" ");
        }
        return text.toString().trim();
    }
}
