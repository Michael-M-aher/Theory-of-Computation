import java.util.ArrayDeque;
import java.util.ArrayList;

public class PDAState {
    private ArrayList<PDAState> nextStates;
    private ArrayList<Character> consumtionSymbols;
    private ArrayList<Character> popSymbols;
    private ArrayList<Character> pushSymbols;
    private int id;

    public PDAState(int id) {
        this.id = id;
        this.nextStates = new ArrayList<PDAState>();
        this.consumtionSymbols = new ArrayList<Character>();
        this.popSymbols = new ArrayList<Character>();
        this.pushSymbols = new ArrayList<Character>();
    }

    public void addNextState(PDAState nextState, char consumtionSymbol, char popSymbol ,char pushSymbol) {
        this.nextStates.add(nextState);
        this.consumtionSymbols.add(consumtionSymbol);
        this.popSymbols.add(popSymbol);
        this.pushSymbols.add(pushSymbol); 
    }

    public PDAState getNextState(Scanner scanner , ArrayDeque<Character> stack) {
        char input = scanner.peek(0);
        char top = stack.peek();

        for (int i = 0; i < this.nextStates.size(); i++) {
            if ((this.consumtionSymbols.get(i) == 'e' ||this.consumtionSymbols.get(i) == input) && 
            (this.popSymbols.get(i) == 'e' || this.popSymbols.get(i) == top) ) 
            {
                if (this.popSymbols.get(i) != 'e') {
                    stack.pop();
                }
                if (this.pushSymbols.get(i) !='e') {
                    stack.push(this.pushSymbols.get(i));
                }
                if (this.consumtionSymbols.get(i) != 'e') {
                    scanner.consume();
                }
                return this.nextStates.get(i);
            }
        }
        return null;
    }
    
}
