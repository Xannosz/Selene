package hu.xannosz.selene.core.tokens;

public class VariableToken implements Token {

    private String variable;
    
    public VariableToken(String variable) {
        this.variable = variable;
    }

    @Override
    public String toString(){
        return "[Variable: " + variable+ "]";
    }
}
