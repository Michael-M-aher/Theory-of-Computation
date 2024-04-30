import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class App {
    private static final int ACCEPTED = 0;
    private static final int DEAD = -1;

    public static void main(String[] args) throws Exception {
        String inpFileName = "input.txt";
        String outFileName = "output.txt";
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
                    String out = processProblem(problemNumber, line);
                    fileWriter.write(out + "\n");
                }
                fileWriter.write("x\n\n");
            }

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        System.out.println("Hello, World!");
    }

    private static String processProblem(int problemNumber, String input) {
        Map<Character, Integer> map = new HashMap<>();
        Deque<Character> stack = new ArrayDeque<>();
        stack.push('$');

        for (int i = input.length() - 1; i >= 0; i--) {
            stack.push(input.charAt(i));
        }
        switch (problemNumber) {
            case 1: {
                map.put('a', 1);
                map.put('b', 2);
                map.put('$', 3);

                int[][] arr = {
                        { DEAD, DEAD, DEAD, DEAD },
                        { DEAD, 1, 2, ACCEPTED },
                        { DEAD, DEAD, 2, ACCEPTED }
                };
                return processProblem(map, arr, stack);
            }
            case 2: {
                map.put('0', 1);
                map.put('1', 2);
                map.put('$', 3);

                int[][] arr = {
                        { DEAD, DEAD, DEAD, DEAD },
                        { DEAD, 2, 3, DEAD },
                        { DEAD, 1, DEAD, DEAD },
                        { DEAD, DEAD, DEAD, ACCEPTED }
                };
                return processProblem(map, arr, stack);
            }
            case 3: {
                map.put('x', 1);
                map.put('y', 2);
                map.put('$', 3);

                int[][] arr = {
                        { DEAD, DEAD, DEAD, DEAD },
                        { DEAD, 2, 4, DEAD },
                        { DEAD, 3, 2, ACCEPTED },
                        { DEAD, 2, 3, DEAD },
                        { DEAD, 5, 4, DEAD },
                        { DEAD, 6, 5, ACCEPTED },
                        { DEAD, 5, 6, DEAD }
                };
                return processProblem(map, arr, stack);
            }
            case 4: {
                map.put('a', 1);
                map.put('b', 2);
                map.put('$', 3);

                int[][] arr = {
                        { DEAD, DEAD, DEAD, DEAD },
                        { DEAD, 2, 4, DEAD },
                        { DEAD, 3, 2, DEAD },
                        { DEAD, 3, 2, ACCEPTED },
                        { DEAD, 4, 5, DEAD },
                        { DEAD, 4, 5, ACCEPTED },
                };
                return processProblem(map, arr, stack);
            }
            case 5: {
                map.put('0', 1);
                map.put('1', 2);
                map.put('$', 3);

                int[][] arr = {
                        { DEAD, DEAD, DEAD, DEAD },
                        { DEAD, 5, 2, DEAD },
                        { DEAD, 3, DEAD, DEAD },
                        { DEAD, 4, DEAD, DEAD },
                        { DEAD, 4, DEAD, ACCEPTED },
                        { DEAD, 5, DEAD, ACCEPTED }
                };
                return processProblem(map, arr, stack);
            }
            case 6: {
                map.put('0', 1);
                map.put('1', 2);
                map.put('$', 3);

                int[][] arr = {
                        { DEAD, DEAD, DEAD, DEAD },
                        { DEAD, 5, 2, DEAD },
                        { DEAD, 6, 3, ACCEPTED },
                        { DEAD, 4, 3, DEAD },
                        { DEAD, 4, 4, ACCEPTED },
                        { DEAD, 5, 5, ACCEPTED },
                        { DEAD, 6, 6, ACCEPTED }
                };
                return processProblem(map, arr, stack);
            }
            case 7: {
            // Not Possible to Get NFA or DFA
            int count01 = countOccurrences(input , "01");
            int count10 = countOccurrences(input , "10");
            if (count10 == count01)
                return "True";
            return "False";
            }
            case 8: {
                State s0 = new State(0);
                State s1 = new State(1);
                State s2 = new State(2);
                State s3 = new State(3);
                State s4 = new State(4);
                State s5 = new State(5);
                State s6 = new State(6);
                State s7 = new State(7);
                State s8 = new State(8);
                State s9 = new State(9);
                State s10 = new State(10);
        
                s0.addTransition('0', s1);
                s0.addTransition('1', s2);
                s0.addTransition('0', s3);
                s0.addTransition('1', s4);

                s1.addTransition('0', s1);
                s1.addTransition('1', s2);
                s1.addTransition('0', s3);
                s1.addTransition('1', s4);

                s2.addTransition('0', s1);
                s2.addTransition('1', s2);
                s2.addTransition('0', s3);
                s2.addTransition('1', s4);

                s3.addTransition('1', s6);

                s4.addTransition('0', s5);

                s5.addTransition('1', s8);

                s6.addTransition('0', s7);

                s7.addTransition('0', s9);
                s7.addTransition('1', s10);

                s8.addTransition('0', s9);
                s8.addTransition('1', s10);

                s9.addTransition('0', s9);
                s9.addTransition('1', s10);

                s10.addTransition('0', s9);
                s10.addTransition('1', s10);
        
                State startState = s0;
                Set<State> acceptStates = new HashSet<>();
                acceptStates.add(s7);
                acceptStates.add(s8);
                acceptStates.add(s9);
                acceptStates.add(s10);
                return processNfaProblem(acceptStates, startState, input);
            }
            case 9: {
                State s0 = new State(0);
                State s1 = new State(1);
                State s2 = new State(2);
                State s3 = new State(3);
                State s4 = new State(4);
                State s5 = new State(5);
                State s6 = new State(6);
                State s7 = new State(7);
                State s8 = new State(8);
                State s9 = new State(9);
                State s10 = new State(10);
        
                s0.addTransition('1', s1);
                s0.addTransition('0', s2);

                s1.addTransition('0', s3);

                s2.addTransition('1', s4);

                s3.addTransition('1', s5);
                s3.addTransition('1', s9);

                s4.addTransition('0', s7);
                s4.addTransition('0', s10);

                s5.addTransition('0', s6);

                s6.addTransition('1', s5);
                s6.addTransition('1', s9);

                s7.addTransition('1', s8);

                s8.addTransition('0', s7);
                s8.addTransition('0', s10);
        
                State startState = s0;
                Set<State> acceptStates = new HashSet<>();
                acceptStates.add(s3);
                acceptStates.add(s4);
                acceptStates.add(s6);
                acceptStates.add(s8);
                acceptStates.add(s9);
                acceptStates.add(s10);
                return processNfaProblem(acceptStates, startState, input);
            }
            case 10: {
                State s0 = new State(0);
                State s1 = new State(1);
                State s2 = new State(2);
                State s3 = new State(3);
                State s4 = new State(4);
                State s5 = new State(5);

                s0.addTransition('0', s1);
                s0.addTransition('0', s2);
                s0.addTransition('1', s5);

                s1.addTransition('0', s1);
                s1.addTransition('0', s2);
                s1.addTransition('1', s5);

                s2.addTransition('1', s3);

                s3.addTransition('0', s2);
                s3.addTransition('1', s4);
                s3.addTransition('1', s5);

                s4.addTransition('0', s2);
                s4.addTransition('1', s4);
                s4.addTransition('1', s5);

                s5.addTransition('1', s5);

                State startState = s0;
                Set<State> acceptStates = new HashSet<>();
                acceptStates.add(s1);
                acceptStates.add(s3);
                acceptStates.add(s4);
                acceptStates.add(s5);
                return processNfaProblem(acceptStates, startState, input);
            }
            default:
                return "Invalid option";
        }
    }

    private static String processProblem(Map<Character, Integer> map, int[][] arr, Deque<Character> stack) {
        int state = 1;
        while (!stack.isEmpty()) {
            char top = stack.pop();
            state = arr[state][map.get(top)];
            if (state == DEAD)
                return "False";
        }

        if (state == ACCEPTED)
            return "True";

        return "False";
    }

    private static String processNfaProblem(Set<State> acceptStates, State startState, String input) {

        Set<State> currentStates = new HashSet<>();
        currentStates.add(startState);

        for (char symbol : input.toCharArray()) {
            Set<State> nextStates = new HashSet<>();
            for (State currentState : currentStates) {
                Set<State> reachableStates = currentState.transitions.getOrDefault(symbol, Collections.emptySet());
                nextStates.addAll(reachableStates);
            }
            currentStates = nextStates;
        }

        for (State state : currentStates) {
            if (acceptStates.contains(state)) {
                return "True";
            }
        }

        return "False";
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
