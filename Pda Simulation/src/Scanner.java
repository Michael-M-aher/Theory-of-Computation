/**
 * Scanner
 */
public class Scanner {
    private int current ;
    private String token;

    Scanner(String token){
        this.current = 0;
        this.token = token + '$';
    }

    public boolean hasNext(){
        return current < token.length();
    }

    public char peek(int i){
        if (current + i >= token.length()){
            return '?';
        }
        return token.charAt(current+i); // a$
    }

    public char get(){
        return token.charAt(current++);
    }

    public void reset(){
        current = 0;
    }

    public void setToken(String token){
        this.token = token + '$';
    }
    
}