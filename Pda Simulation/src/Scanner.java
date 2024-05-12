import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Scanner  implements Serializable{
    private int current ;
    private String token;

    Scanner(String token){
        this.current = 0;
        this.token = token + '$';
    }

    Scanner(Scanner scanner){
        this.current = scanner.current;
        this.token = scanner.token;
    }

    public Scanner deepCopy() throws IOException, ClassNotFoundException {
            // Serialize the object
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            // Deserialize the object
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            return (Scanner) ois.readObject();
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

    public char consume(){
       return token.charAt(current++);
    }

    public void reset(){
        current = 0;
    }

    public boolean match(char c){
        if(c != token.charAt(current)){
            return false;
        }
        else{
            current++;
            return true;
        }
    }

    public void setToken(String token){
        this.token = token + '$';
    }
    
}