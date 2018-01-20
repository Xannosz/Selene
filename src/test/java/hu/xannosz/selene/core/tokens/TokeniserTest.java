package hu.xannosz.selene.core.tokens;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import hu.xannosz.selene.utils.Pair;

public class TokeniserTest {
    
    @Test
    public void readWordTest1(){
        Tokeniser tokeniser = new Tokeniser();
        tokeniser.addContent("szov = 2;");
        Pair<String, String> response = tokeniser.readWord();
        Assert.assertEquals(response.getLeft(), "szov");
        Assert.assertEquals(response.getRight(), " ");
        response = tokeniser.readWord();
        Assert.assertEquals(response.getLeft(), "");
        Assert.assertEquals(response.getRight(), "=");
        response = tokeniser.readWord();
        Assert.assertEquals(response.getLeft(), "");
        Assert.assertEquals(response.getRight(), " ");
        response = tokeniser.readWord();
        Assert.assertEquals(response.getLeft(), "2");
        Assert.assertEquals(response.getRight(), ";");
    }
    
    @Test
    public void parseOneTest1(){
        Tokeniser tokeniser = new Tokeniser();
        tokeniser.addContent("szov = 2;");
        tokeniser.parseOne();
        Assert.assertEquals(tokeniser.tokens.size(), 1);
        tokeniser.parseOne();
        Assert.assertEquals(tokeniser.tokens.size(), 2);
        tokeniser.parseOne();
        Assert.assertEquals(tokeniser.tokens.size(), 2);
        tokeniser.parseOne();
        Assert.assertEquals(tokeniser.tokens.size(), 4);
    }
    
    @Test
    public void parseTest1(){
        Tokeniser tokeniser = new Tokeniser();
        tokeniser.addContent("szov = 2;");
        tokeniser.parse();
        Assert.assertEquals(tokeniser.tokens.size(), 4);
    }

}