import java.util.ArrayList;
import java.util.List;

public class Token {

    private LolType type;
    private String varName;
    private List<String> elements;

    public Token(LolType type) {
        this.type = type;
        this.elements = new ArrayList<>();
    }

    public void addElement(String string) {
        this.elements.add(string);
    }

    public void addElements(List<String > elements){
        this.elements = elements;
    }

    public String getElement(int index){
        return elements.get(index);
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }

    public LolType getType() {
        return type;
    }

    public List<String> getElements() {
        return elements;
    }

    public List<String > getElementsFrom(int index){
        return elements.subList(index,elements.size());
    }
}
