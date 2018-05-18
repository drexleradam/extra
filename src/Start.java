import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Start {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("test.lol"));

        List<Token> tokens = new ArrayList<>();
        int i;
        String line;
        StringBuilder builder;
        while ((line = reader.readLine()) != null) {
            i=0;
            builder = new StringBuilder();
            for (i=0; i < line.length(); i++) {
                if (line.charAt(i) == ' ') {
                    break;
                }
                builder.append(line.charAt(i));
            }
            i++;
            boolean isOkey = false;
            Token token = null;
            for (LolType type : LolType.values()){
                if (builder.toString().toUpperCase().equals(type.toString())){
                    token = new Token(type);
                    isOkey = true;
                    break;
                }
            }
            if (!isOkey){
                System.out.println("No such token in Extra language.");
                break;
            }
            builder = new StringBuilder();
            boolean string = false;
            List<String > elements = new ArrayList<>();
            for (i=i;i<line.length();i++){
                if (!string){
                    if (line.charAt(i) == '"') {
                        string = true;
                        continue;
                    }
                    if (line.charAt(i) != ' '){
                        builder.append(line.charAt(i));
                    }else {
                        elements.add(builder.toString());
                        builder = new StringBuilder();
                    }
                }else {
                    if (line.charAt(i) == '"') {
                        string = false;
                        elements.add(builder.toString());
                        continue;
                    }
                    builder.append(line.charAt(i));
                }
            }
            if (!builder.toString().equals("")){
                elements.add(builder.toString());
            }
            token.addElements(elements);
            tokens.add(token);
        }
        reader.close();

        // fill the java class with the elements

        try {
            if (JavaCreater.createOutput("output.java", tokens )){
                System.out.println("Done!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
