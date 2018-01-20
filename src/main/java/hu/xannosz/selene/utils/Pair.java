package hu.xannosz.selene.utils;

import lombok.Getter;
import lombok.Setter;

public class Pair<T,Y> {
    
    @Getter @Setter private T left;
    @Getter @Setter  private Y right;

    public Pair(){
        this(null,null);
    }

    public Pair(T left, Y right){
        this.left = left;
        this.right = right;
    }
}
