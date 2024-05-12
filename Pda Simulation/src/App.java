import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;

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
                    String out = processProblems(problemNumber, line);
                    fileWriter.write(out + "\n");
                }
                fileWriter.write("x\n\n");
            }

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        System.out.println("Hello, World!");
    }

    public static String processProblems(int problemNumber , String line){
        switch (problemNumber) {
            case 1:
                return processProblem1(line);
            case 2:
                return processProblem2(line);
                // return "Invalid problem number";
            case 3:
                return processProblem3(line);
            case 4:
                return processProblem4(line);
            default:
                return "Invalid problem number";
        }
    }

    public static String processProblem1(String line){
        Scanner scanner = new Scanner(line);
        ArrayDeque<Character> stack = new ArrayDeque<>();
        stack.push('$');
        stack.push('S');
        boolean res = problem1Start(scanner , stack);
        return (res) ? "accepted" : "not accepted";
    }

    public static boolean problem1Start(Scanner scanner , ArrayDeque<Character> stack){
        while(!stack.isEmpty()){
            char top = stack.pop();
            if (top == 'S'){
                // call  the function S with all possible derivations
                // write the code to deep copy the deque called stack
                ArrayDeque<Character> stackCopy1 = new ArrayDeque<>(stack);
                stackCopy1.push('b');
                stackCopy1.push('S');
                stackCopy1.push('a');

                ArrayDeque<Character> stackCopy2 = new ArrayDeque<>(stack);
                stackCopy2.push('a');
                stackCopy2.push('S');
                stackCopy2.push('b');

                ArrayDeque<Character> stackCopy3 = new ArrayDeque<>(stack);

                try{
                Scanner scanner1 = scanner.deepCopy();
                Scanner scanner2 = scanner.deepCopy();
                Scanner scanner3 = scanner.deepCopy();

                return problem1Start(scanner1 , stackCopy1) || problem1Start(scanner2 , stackCopy2) || problem1Start(scanner3 , stackCopy3);
                }
                catch(Exception e){
                    System.out.println("Error: " + e.getMessage());
                    return false;
                }
            }
            else
            {
                char c = scanner.consume();
                if (c != top){
                    return false;
                }
            }
        }

        return true;
    }


    public static String processProblem2(String line){
        ArrayDeque<Character> stack = new ArrayDeque<>();
        System.out.println("Line : " + line);
        Scanner scanner = new Scanner(line);
        stack.push('$');
        // stack.push('S');
        boolean res = problem2Start(line);
        return (res) ? "accepted" : "not accepted"; 
//         S -> A | A B S
        // A -> a A a | ε
        // B -> b B b | ε
    }
    public static boolean problem2StartRec(Scanner scanner , ArrayDeque<Character> stack){
        // queue that holds scanners

        while(!stack.isEmpty()){
            char top = stack.pop();
            if (top == 'S'){
                // call  the function S with all possible derivations
                // write the code to deep copy the deque called stack
                ArrayDeque<Character> stackCopy1 = new ArrayDeque<>(stack);
                stackCopy1.push('A');
                ArrayDeque<Character> stackCopy2 = new ArrayDeque<>(stack);
                stackCopy2.push('S');
                stackCopy2.push('B');
                stackCopy2.push('A');

                try{
                Scanner scanner1 = scanner.deepCopy();
                Scanner scanner2 = scanner.deepCopy();

                return problem2StartRec(scanner1 , stackCopy1) || problem2StartRec(scanner2 , stackCopy2) ;
                }
                catch (Exception e){
                    System.out.println("Error: " + e.getMessage());
                    return false;
                }
            }
            else if  (top == 'A'){
                ArrayDeque<Character> stackCopy1 = new ArrayDeque<>(stack);
                stackCopy1.push('a');
                stackCopy1.push('A');
                stackCopy1.push('a');
                ArrayDeque<Character> stackCopy2 = new ArrayDeque<>(stack);

                try{
                Scanner scanner1 = scanner.deepCopy();
                Scanner scanner2 = scanner.deepCopy();
                return problem2StartRec(scanner1 , stackCopy1) || problem2StartRec(scanner2 , stackCopy2);
                }
                catch (Exception e){
                    System.out.println("Error: " + e.getMessage());
                    return false;
                }
            }
            else if (top == 'B'){
                ArrayDeque<Character> stackCopy1 = new ArrayDeque<>(stack);
                stackCopy1.push('b');
                stackCopy1.push('B');
                stackCopy1.push('b');
                ArrayDeque<Character> stackCopy2 = new ArrayDeque<>(stack);


                try{
                Scanner scanner1 = scanner.deepCopy();
                Scanner scanner2 = scanner.deepCopy();
                return problem2StartRec(scanner1 , stackCopy1) || problem2StartRec(scanner2 , stackCopy2);
                }
                catch (Exception e){
                    System.out.println("Error: " + e.getMessage());
                    return false;
                }
            }
            else
            {
                char c = scanner.consume();
                if (c != top){
                    return false;
                }
            }
        }
        return true;
    }


    public static boolean problem2Start(String line){
        int counta = countOccurrences(line , "a");
        int countb = countOccurrences(line , "b");

        return (counta == countb * 2);
    }


    public static String processProblem3(String line){
        ArrayDeque<Character> stack = new ArrayDeque<>();
        Scanner scanner = new Scanner(line);
        stack.push('$');
        stack.push('S');
        boolean res = problem3Start(scanner , stack);
        return (res) ? "accepted" : "not accepted"; 
    // Q3)
    // S -> aSa
    // S -> bSb
    // S -> a|b|epsilon
    }

    public static boolean problem3Start(Scanner scanner , ArrayDeque<Character> stack){
        while(!stack.isEmpty()){
            char top = stack.pop();
            if (top == 'S'){
                ArrayDeque<Character> stackCopy1 = new ArrayDeque<>(stack);
                stackCopy1.push('a');
                stackCopy1.push('S');
                stackCopy1.push('a');
                ArrayDeque<Character> stackCopy2 = new ArrayDeque<>(stack);
                stackCopy2.push('b');
                stackCopy2.push('S');
                stackCopy2.push('b');
                ArrayDeque<Character> stackCopy3 = new ArrayDeque<>(stack);
                stackCopy3.push('a');
                ArrayDeque<Character> stackCopy4 = new ArrayDeque<>(stack);
                stackCopy4.push('b');
                ArrayDeque<Character> stackCopy5 = new ArrayDeque<>(stack);

                try{
                Scanner scanner1 = scanner.deepCopy();
                Scanner scanner2 = scanner.deepCopy();
                Scanner scanner3 = scanner.deepCopy();
                Scanner scanner4 = scanner.deepCopy();
                Scanner scanner5 = scanner.deepCopy();
                return problem3Start(scanner1 , stackCopy1) || problem3Start(scanner2 , stackCopy2) || problem3Start(scanner3 , stackCopy3) || problem3Start(scanner4 , stackCopy4) || problem3Start(scanner5 , stackCopy5);
                }
                catch (Exception e){
                    System.out.println("Error: " + e.getMessage());
                    return false;
                }
            }
            else
            {
                if (!scanner.hasNext()) {
                    return false; // or throw an exception, depending on your needs
                }
                char c = scanner.consume();
                if (c != top){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean processProblem4Rec(Scanner scanner , ArrayDeque<Character> stack){
        while(!stack.isEmpty()){
            char top = stack.pop();
            if (top == 'S'){
                // call  the function S with all possible derivations
                // write the code to deep copy the deque called stack
                ArrayDeque<Character> stackCopy1 = new ArrayDeque<>(stack);
                stackCopy1.push('A');
                stackCopy1.push('a');
                stackCopy1.push('a');
                stackCopy1.push('a');
                stackCopy1.push('B');

                ArrayDeque<Character> stackCopy3 = new ArrayDeque<>(stack);

                try{
                Scanner scanner1 = scanner.deepCopy();
                Scanner scanner2 = scanner.deepCopy();

                return processProblem4Rec(scanner1 , stackCopy1) || processProblem4Rec(scanner2 , stackCopy3);
                }
                catch(Exception e){
                    System.out.println("Error: " + e.getMessage());
                    return false;
                }
            }
            else if (top == 'A')
            {
                ArrayDeque<Character> stackCopy1 = new ArrayDeque<>(stack);
                stackCopy1.push('a');
                stackCopy1.push('a');
                stackCopy1.push('A');
                ArrayDeque<Character> stackCopy2 = new ArrayDeque<>(stack);

                try{
                Scanner scanner1 = scanner.deepCopy();
                Scanner scanner2 = scanner.deepCopy();
                return processProblem4Rec(scanner1 , stackCopy1) || processProblem4Rec(scanner2 , stackCopy2);
                }
                catch(Exception e){
                    System.out.println("Error: " + e.getMessage());
                    return false;
                }
            }
            else if (top == 'B')
            {
                ArrayDeque<Character> stackCopy1 = new ArrayDeque<>(stack);
                stackCopy1.push('b');
                stackCopy1.push('B');
                ArrayDeque<Character> stackCopy2 = new ArrayDeque<>(stack);

                try{
                Scanner scanner1 = scanner.deepCopy();
                Scanner scanner2 = scanner.deepCopy();
                return processProblem4Rec(scanner1 , stackCopy1) || processProblem4Rec(scanner2 , stackCopy2);
                }
                catch(Exception e){
                    System.out.println("Error: " + e.getMessage());
                    return false;
                }
            }
            else
            {
                char c = scanner.consume();
                if (c != top){
                    return false;
                }
            }
        }

        return true;
    }

    public static String processProblem4(String line){
        ArrayDeque<Character> stack = new ArrayDeque<>();
        Scanner scanner = new Scanner(line);
        stack.push('$');
        stack.push('S');
        boolean res = problem4Start(line);
        return (res) ? "accepted" : "not accepted"; 
    }

    public static boolean problem4Start(String line){
        int counta = countOccurrences(line , "a");
        int countb = countOccurrences(line , "b");

        int n =( counta - 3)/2;

        return (countb == n);
    }

    public static int countOccurrences(String text, String target) {
        int count = 0;
        int index = 0;

        while ((index = text.indexOf(target, index)) != -1) {
            count++;
            index += target.length();
        }

        return count;
    }
}

// CFG:
// Q1) 
// S -> aSb | bSa | epsilon


// Q2)
// S-> SS|S'bS'|bSaa|aaSb|epsilon
// S'-> aS| SS'

// Q3)
// S -> aSa
// S -> bSb
// S -> a|b|epsilon