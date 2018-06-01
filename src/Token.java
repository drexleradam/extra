public class Token {

    private COMMAND_TYPE type;
    private String elements[];

    public Token(COMMAND_TYPE type, String elements[]) {
        this.type = type;
        this.elements = elements;
    }

    public String[] getElements() {
        return elements;
    }

    public COMMAND_TYPE getType() {
        return type;
    }

}
