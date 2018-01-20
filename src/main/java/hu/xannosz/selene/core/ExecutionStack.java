package hu.xannosz.selene.core;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ExecutionStack {

    private List<StackFrame> frames = new LinkedList<>();
    
    private Map<String,Variable> globalVariables = new HashMap<>();

    public ExecutionStack() {
        frames.add(new StackFrame());
    }

}
