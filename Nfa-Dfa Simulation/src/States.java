import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class State {
    int id;
    Map<Character, Set<State>> transitions;

    public State(int id) {
        this.id = id;
        this.transitions = new HashMap<>();
    }

    public void addTransition(char symbol, State nextState) {
        transitions.computeIfAbsent(symbol, k -> new HashSet<>()).add(nextState);
    }
}
