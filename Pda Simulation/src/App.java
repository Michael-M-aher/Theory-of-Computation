import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;

public class App {
    public static void main(String[] args) throws Exception {
        String inpFileName = "input_cfg.txt";
        String outFileName = "output_cfg.txt";
        try (FileReader fileReader = new FileReader(inpFileName);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                FileWriter fileWriter = new FileWriter(outFileName);) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.isEmpty())
                    continue;
                int problemNumber = Integer.parseInt(line);
                fileWriter.write(problemNumber + "\n");
                while ((line = bufferedReader.readLine()) != null && !line.equalsIgnoreCase("end")) {
                    String out = processProblem1(problemNumber, line);
                    fileWriter.write(out + "\n");
                }
                fileWriter.write("x\n\n");
            }

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        System.out.println("Hello, World!");
    }

    // public static String processProblem(int problemNumber , String line){
        
    // }

    public static String processProblem1(int problemNumber , String line){
        Scanner scanner = new Scanner(line);
        boolean res = problem1Start(scanner);
        return (res) ? "accepted" : "not accepted";
    }

    public static boolean problem1Start(Scanner scanner)
    {
        
    }
}
// write java code to match cfg grammar
// public static void main(String[] args) {
//     input = "aaabb";
//     if (S() && index == input.length()) {
//         System.out.println("Match!");
//     } else {
//         System.out.println("No match.");
//     }
// }

// private static boolean S() {
//     if (nextChar('a')) {
//         if (S()) {
//             return nextChar('b');
//         } else {
//             return nextChar('b');
//         }
//     } else {
//         return true; // empty string is valid
//     }
// }

// private static boolean nextChar(char c) {
//     if (index < input.length() && input.charAt(index) == c) {
//         index++;
//         return true;
//     } else {
//         return false;
//     }
// }


// CFG:
// Q1) 
// S -> aSb | bSa | epsilon 

// Q3)
// S -> aSa
// S -> bSb
// S -> a|b|epsilon