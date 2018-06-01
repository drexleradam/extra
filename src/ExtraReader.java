import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ExtraReader {

    public void doWork() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("test.lol"));

        List<Token> tokens = new ArrayList<>();
        int lineNumber = 0;
        String line;
        while ((line = reader.readLine()) != null) {
            lineNumber++;
            boolean lineMatch = false;
            for (COMMAND_TYPE c : COMMAND_TYPE.values()) {
                if (c.getPattern().matcher(line).matches()) {
                    String tmp[];
                    lineMatch = true;
                    switch (c) {
                        case PRINT_STRING:
                            tmp = new String[1];
                            tmp[0] = line.substring(5, line.length() - 1);
                            break;
                        case PRINT_VARIABLE:
                            tmp = new String[1];
                            tmp[0] = line.substring(4);
                            break;
                        case INTEGER_VARIABLE:
                            tmp = new String[2];
                            tmp[0] = line.split(" ")[1];
                            tmp[1] = line.split(" ")[2];
                            break;
                        case DEFAULT_INTEGER_VARIABLE:
                            tmp = new String[1];
                            tmp[0] = line.split(" ")[1];
                            break;
                        case STRING_VARIABLE:
                            tmp = new String[2];
                            tmp[0] = line.split(" ")[1];
                            tmp[1] = line.split(" ")[2];
                            break;
                        case DEFAULT_STRING_VARIABLE:
                            tmp = new String[1];
                            tmp[0] = line.split(" ")[1];
                            break;
                        default:
                            tmp = new String[1];
                            break;
                    }
                    Token token = new Token(c, tmp);
                    tokens.add(token);
                }
            }
            if (!lineMatch) {
                throw new Exception("Wrong command ( " + line.split(" ")[0] + " ) on line " + lineNumber);
            }
        }

        try {
            if (JavaCreater.createOutput("output.java", tokens)) {
                System.out.println("Done!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
