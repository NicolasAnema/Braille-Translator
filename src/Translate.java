package src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
// import java.util.HashMap;
// import java.util.Map;

public class Translate {
    /**
     * @param args
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
                // print line
                System.out.println(line);

                if (reader.readLine() != null) {
                    System.err.println("Input Error - More than one line in file");
                    System.exit(1);
                }
            } catch (IOException e) {
                System.err.println("Input Error - Invalid or missing file");
                System.exit(1);
            }

            if (line != null) {
                if (path.endsWith(".brf")) {
                    try {
                        String fileName = Paths.get(path).getFileName().toString().replace(".brf", "");
                        String newFilePath = "./out/" + fileName + "_b2t.txt";
                        FileWriter fileWriter = new FileWriter(newFilePath);
                        fileWriter.write(line);
                        fileWriter.close();

                    } catch (IOException e) {
                        System.out.println("An error occurred while writing to the file.");
                        e.printStackTrace();
                    }

                } else if (path.endsWith(".txt")) {
                    try {
                        String fileName = Paths.get(path).getFileName().toString().replace(".txt", "");
                        String newFilePath = "./out/" + fileName + "_t2b.brf";
                        FileWriter fileWriter = new FileWriter(newFilePath);
                        fileWriter.write(line);
                        fileWriter.close();

                    } catch (IOException e) {
                        System.out.println("An error occurred while writing to the file.");
                        e.printStackTrace();
                    }
                }
            }
        }
}