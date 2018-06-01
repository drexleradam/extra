import java.io.*;
import java.util.List;

class JavaCreater {

    static boolean createOutput(String fileName, List<Token> tokens) throws Exception {

        StringBuilder builder = new StringBuilder();

        String className[] = fileName.split("\\.");

        builder.append(String.format("public class %s {\n\n\tpublic static void main(String[] args) {\n\n", className[0]));

        for (Token t : tokens) {
            String appending;
            switch (t.getType()) {
                case EMPTY_LINE:
                    builder.append("\n");
                    break;
                case PRINT_STRING:
                    builder.append(String.format("\t\tSystem.out.println(\"%s\");\n", t.getElements()[0]));
                    break;
                case PRINT_VARIABLE:
                    builder.append(String.format("\t\tSystem.out.println(%s);\n", t.getElements()[0]));
                    //builder.append(String.format("\t\tint %s;\n\t\t%s = ",t.getElement(0),t.getElement(0)));
                    //for (String s : t.getElementsFrom(1)){
                    //    builder.append(String.format("%s + ",s));
                    //}
                    //builder.delete(builder.length() - 3, builder.length());
                    //builder.append(";\n");
                    break;
                case STRING_VARIABLE:
                    builder.append(String.format("\t\tString %s = \"%s\";\n", t.getElements()[0], t.getElements()[1]));
                    break;
                case DEFAULT_STRING_VARIABLE:
                    builder.append(String.format("\t\tString %s = \"\";\n", t.getElements()[0]));
                    break;
                case INTEGER_VARIABLE:
                    builder.append(String.format("\t\tint %s = %s;\n", t.getElements()[0], t.getElements()[1]));
                    break;
                case DEFAULT_INTEGER_VARIABLE:
                    builder.append(String.format("\t\tint %s = 0;\n", t.getElements()[0]));
                    break;
                case ADD_INTEGER:
                    builder.append(String.format("\t\t%s =", t.getElements()[0]));
                    appending = " ";
                    for (String s : t.getElements()){
                        if (!s.equals(t.getElements()[0])){
                            builder.append(appending).append(s);
                            appending = " + ";
                        }
                    }
                    builder.append(";\n");
                    break;
                case SUB_INTEGER:
                    builder.append(String.format("\t\t%s =", t.getElements()[0]));
                    appending = " ";
                    for (String s : t.getElements()){
                        if (!s.equals(t.getElements()[0])){
                            builder.append(appending).append(s);
                            appending = " - ";
                        }
                    }
                    builder.append(";\n");
                    break;
                default:
                    throw new Exception("Wrong command.");
            }
        }

        builder.append("\n\t}\n\n}");

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
