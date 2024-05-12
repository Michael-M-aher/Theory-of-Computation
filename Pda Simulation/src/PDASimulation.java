import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashSet;

public class PDASimulation {

    public static void main(String[] args) {
        String inpFileName = "input_pda.txt";
        String outFileName = "output_pda.txt";
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
        HashSet<PDAState> acceptedStates = new HashSet<PDAState>();
        stack.push('$');
        char top = stack.peek();
        char c = scanner.peek(0);
        if (top == c)
            return "accepted";
        
        PDAState state1 = new PDAState(1); 
        PDAState state2 = new PDAState(2); 
        PDAState state3 = new PDAState(3); 

        acceptedStates.add(state3);
        
        state1.addNextState(state1, 'a', 'e' , 'a'); 
        state1.addNextState(state2,'b', 'a', 'e' );
        state2.addNextState(state2, 'b', 'a', 'e');
        state2.addNextState(state3, '$', '$', 'e');

        PDAState currentState = state1;
        while(scanner.hasNext())
        {
            PDAState nextState = currentState.getNextState(scanner, stack);
            if (nextState == null)
                return "not accepted";
            currentState = nextState;
        }

        if (acceptedStates.contains(currentState))
            return "accepted";
        
        return "not accepted";
    }

    public static String processProblem2 (String line) {
        Scanner scanner = new Scanner(line);
        ArrayDeque<Character> stack = new ArrayDeque<>();
        HashSet<PDAState> acceptedStates = new HashSet<PDAState>();
        stack.push('$');

        PDAState state1 = new PDAState(1);
        PDAState state2 = new PDAState(2);
        PDAState state3 = new PDAState(3);
        PDAState state4 = new PDAState(4);
        PDAState state5 = new PDAState(5);
        PDAState state6 = new PDAState(6);
        
        acceptedStates.add(state6);

        state1.addNextState(state1, 'a', 'e', 'a');
        state1.addNextState(state2, 'b', 'e', 'e');
        state2.addNextState(state3, 'b', 'e', 'e');
        state3.addNextState(state4 , 'b', 'a', 'e');
        state4.addNextState(state5,'e', 'a', 'e');
        state5.addNextState(state2, 'b', 'e', 'e');
        state5.addNextState(state6,'$', '$', 'e');

        PDAState currentState = state1;
        while(scanner.hasNext())
        {
            PDAState nextState = currentState.getNextState(scanner, stack);
            if (nextState == null)
                return "not accepted";
            currentState = nextState;
        }

        if (acceptedStates.contains(currentState))
            return "accepted";
        
        return "not accepted";

    }

    public static String processProblem3 (String line) {
        Scanner scanner = new Scanner(line);
        ArrayDeque<Character> stack = new ArrayDeque<>();
        HashSet<PDAState> acceptedStates = new HashSet<PDAState>();
        stack.push('$');

        PDAState state1 = new PDAState(1);
        PDAState state2 = new PDAState(2);

        acceptedStates.add(state2);

        state1.addNextState(state1, '{', 'e', '{');
        state1.addNextState(state1, '}', '{', 'e');
        state1.addNextState(state2, '$', '$', 'e');

        PDAState currentState = state1;
        while(scanner.hasNext())
        {
            PDAState nextState = currentState.getNextState(scanner, stack);
            if (nextState == null)
                return "not accepted";
            currentState = nextState;
        }

        if (acceptedStates.contains(currentState))
            return "accepted";
        
        return "not accepted";
    }

    public static String processProblem4(String line) {
        Scanner scanner = new Scanner(line);
        ArrayDeque<Character> stack = new ArrayDeque<>();
        HashSet<PDAState> acceptedStates = new HashSet<PDAState>();
        stack.push('$');

        PDAState state1 = new PDAState(1);
        PDAState state2 = new PDAState(2);
        PDAState state3 = new PDAState(3);
        PDAState state4 = new PDAState(4);


        state1.addNextState(state1, 'a', 'e', 'a');
        state1.addNextState(state2, 'b', 'a', 'e');
        state2.addNextState(state2, 'b', 'a', 'e');
        state2.addNextState(state3, 'c', 'a', 'e');
        state3.addNextState(state3, 'c', 'a', 'e');
        state3.addNextState(state4, '$', '$', 'e');

        acceptedStates.add(state4);

        PDAState currentState = state1;
        while(scanner.hasNext())
        {
            PDAState nextState = currentState.getNextState(scanner, stack);
            if (nextState == null)
                return "not accepted";
            currentState = nextState;
        }

        if (acceptedStates.contains(currentState))
            return "accepted";
        
        return "not accepted";

    }
}
