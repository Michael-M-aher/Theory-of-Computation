import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {

    public static void main(String[] args)  {
        String inpFileName = "input.txt";
        String outFileName = "output.txt";
        try (FileReader fileReader = new FileReader(inpFileName);
                BufferedReader bufferedReader = new BufferedReader(fileReader); FileWriter fileWriter = new FileWriter(outFileName); ) {
            
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                int problemNumber = Integer.parseInt(line);
                fileWriter.write(problemNumber + "\n");
                while ((line = bufferedReader.readLine()) != null && !line.equals("end")) {
                    String out = processProblem(problemNumber, line);
                    fileWriter.write(out + "\n");
                }
                fileWriter.write("x\n");
            }

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

    }

    private static String processProblem(int problemNumber, String input) {
        switch (problemNumber) {
            case 1:
                return processProblem1(input);
            case 2:
                return processProblem2(input);
            case 3:
                return processProblem3(input);
            case 4:
                return processProblem4(input);    
            case 5:
                return processProblem5(input);
            case 6:
                return processProblem6(input);
            case 7:
                return processProblem7(input);
            case 8:
                return processProblem8(input);
            case 9:
                // read from the file input then pass it to the processProblem
                try(FileReader reader = new FileReader(input)) {
                    return processProblem9(reader , input);
                } catch (FileNotFoundException e) {
                    System.out.println("File Not Found");
                } catch (IOException e) {
                    System.out.println("Error During I/O");
                }
            case 10:
                return processProblem10(input);    
            default:
                return "Invalid option";
        }
    }

    private static String processProblem1(String input) {
        String emailRegex = "^.+@.+\\.[a-zA-Z]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        if (pattern.matcher(input).matches()) {
            return "valid email";
        } else {
            return "invalid email";
        }
    }

    private static String processProblem2(String input) {
        String p1 = "(\\(\\d{3}\\)|\\d{3})-\\d{3}-\\d{4}";
        String p2 = "\\d{3}\\.\\d{3}\\.\\d{4}";
        String p3 = "\\d{3} \\d{3} \\d{4}";
        String p4 = "\\d{10}";
        String phoneNumberRegex =String.format("^(%s)|(%s)|(%s)|(%s)$", p1, p2, p3, p4);
        Pattern pattern = Pattern.compile(phoneNumberRegex , Pattern.CASE_INSENSITIVE);
        if (pattern.matcher(input).matches()){
             return "valid phone number";
         } else {
             return "invalid phone number";
         }
    }

    private static String processProblem3(String input) {
        String date1 = "\\d{4}/\\d{2}/\\d{2}";
        String date2 = "\\d{4}-\\d{2}-\\d{2}";
        String date3 = "\\d{1,2}/\\d{2}/\\d{4}";
        String date4 = "\\d{2}/\\d/\\d{4}";
        String date5 = "\\d{1,2}-\\d{2}-\\d{4}";
        String date6 = "\\d{2}-\\d-\\d{4}";
        String dateRegex = String.format("^(%s)|(%s)|(%s)|(%s)|(%s)|(%s)$", date1, date2, date3, date4, date5, date6);
        Pattern pattern = Pattern.compile(dateRegex, Pattern.CASE_INSENSITIVE);
        if (pattern.matcher(input).matches()) {
            return "valid date";
        } else {
            return "invalid date";
        }
    }

    private static String processProblem4(String input) {
        String ip1 = "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)";
        String IPRegex = String.format("%s\\.%s\\.%s\\.%s", ip1, ip1, ip1, ip1);
        Pattern pattern = Pattern.compile(IPRegex , Pattern.CASE_INSENSITIVE);
        if (pattern.matcher(input).matches()){
             return "valid IP address";
         } else {
             return "invalid IP address";
         }
    }

    private static String processProblem5(String input) {
        String variableRegex = "^[a-zA-Z_][a-zA-Z0-9_]*$";
        Pattern pattern = Pattern.compile(variableRegex, Pattern.CASE_INSENSITIVE);
        if (pattern.matcher(input).matches()) {
            return "valid C++ variable name";
        } else {
            return "invalid C++ variable name";
        }
    }
    private static String processProblem6(String input) {
        String tripelBRegex = ".*(bbb).*";
        Pattern pattern = Pattern.compile(tripelBRegex , Pattern.CASE_INSENSITIVE);
        if (pattern.matcher(input).matches()) {
            return "invalid string, has 3 consecutive b’s";
        } else {
            return "valid string";
        }
    }
// baa
    private static String processProblem7(String input) {
        String oddARegex = "a(aa)*";
        String oddBRegex = "b(bb)*";
        String oddRegex = String.format("((aa)*|(bb)*)(%s%s|%s%s)", oddARegex,oddBRegex,oddBRegex, oddARegex);
        Pattern pattern = Pattern.compile(oddRegex, Pattern.CASE_INSENSITIVE);
        int count = 0;
        StringBuilder s = new StringBuilder();
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            count++;
            s.append("matched substring: " + input.substring(matcher.start(), matcher.end()) + "\nstart index: " + matcher.start() + ", end index: " + matcher.end() + "\n");
        }
        return String.format("*%s*%n", input) + "number of matched substrings: " + count + "\n" + s.toString();

    }

    private static String processProblem8(String input) {
        String multipleOf3 = "\\b\\w{3}(?:\\w{3})*\\b";
        Pattern pattern = Pattern.compile(multipleOf3);
        String output = '*'+input + "*\n";
        StringBuilder s = new StringBuilder();
        int count = 0;
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            count++;
            s.append("matched word: " + input.substring(matcher.start(), matcher.end()) + "\nstart index: " + matcher.start() + ", end index: " + matcher.end() + "\n");
        }
        if (count == 0){
            return output + "No word matches";
        }
        output += "number of matched words: " +Integer.toString(count) + '\n' + s.toString();
        return output;
    }

    private static String processProblem9(FileReader file , String filename) throws IOException {
        String urlPattern = "http[s]?:\\/\\/.+\\.com(?:\\/\\w*)*";
        Pattern pattern = Pattern.compile(urlPattern);
        String output = '*'+filename + "*\n";
        int countMatches = 0;
        int lineNum = 1;
        BufferedReader bufferedReader = new BufferedReader(file);
        String line;
        StringBuilder s = new StringBuilder();
        while((line = bufferedReader.readLine()) != null)
        {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                countMatches++;
                s.append("URL: " + line.substring(matcher.start(), matcher.end()) + '\n' + "Line: " + lineNum + '\n' + "start index: " + matcher.start() + ", end index: " + matcher.end() + '\n');
            }
            lineNum++;
        }
        return output + "Number of URLs: " + countMatches + '\n' + s.toString();
    }

    private static String processProblem10(String input) {
        String expressionRegex = "^(?:[a-zA-Z]+|[0-9]+(?:\\.[0-9]+)?)(?:[+\\-*/]*(?:[a-zA-Z]+|[0-9]+(?:\\.[0-9]+)?))*=(?:[a-zA-Z]+|[0-9]+(?:\\.[0-9]+)?)(?:[+\\-*/](?:[a-zA-Z]+|[0-9]+(?:\\.[0-9]+)?))*$";
        Pattern pattern = Pattern.compile(expressionRegex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            return("valid mathematical expression");
        } else {
            return ("invalid mathematical expression");
        }
    }
}
