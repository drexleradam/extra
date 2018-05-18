import java.io.*;
import java.util.List;

public class JavaCreater {

    static boolean createOutput(String fileName, List<Token> tokens) throws Exception {

        StringBuilder builder = new StringBuilder();

        String className[] = fileName.split("\\.");

        builder.append(String.format("public class %s {\n\tpublic static void main(String[] args) {\n",className[0]));

        for (Token t : tokens) {
            switch (t.getType()) {
                case PRI:
                    builder.append(String.format("\t\tSystem.out.println(\"%s\");\n",t.getElement(0)));
                    break;
                case ADD:
                    builder.append(String.format("\t\tint %s;\n\t\t%s = ",t.getElement(0),t.getElement(0)));
                    for (String s : t.getElementsFrom(1)){
                        builder.append(String.format("%s + ",s));
                    }
                    builder.delete(builder.length()-3,builder.length());
                    builder.append(";\n");
                    break;
                default:
                    throw new Exception("Wrong command.");
            }
        }

        builder.append("\t}\n}");

        File file = new File(fileName);
        FileOutputStream fos = new FileOutputStream(file);
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        Writer w = new BufferedWriter(osw);
        w.write(builder.toString());
        w.close();
        osw.close();
        fos.close();

        return true;
    }

}
