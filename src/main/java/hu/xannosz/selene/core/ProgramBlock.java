package hu.xannosz.selene.core;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import hu.xannosz.selene.core.rows.ProgramRow;
import lombok.Getter;

public class ProgramBlock {

    @Getter
    private String name;
    @Getter
    private BigInteger code;
    private static BigInteger lastCode = BigInteger.valueOf(-1);
    private List<ProgramRow> programRows;

    private ProgramBlock(String block, String name) {
        programRows = new ArrayList<>();
        this.name = name;
        code = nextCode();
    }

    public void addProgramRow(ProgramRow row) {
        programRows.add(row);
    }

    private BigInteger nextCode() {
        return lastCode.add(BigInteger.ONE);
    }

    public void execute(ExecutionStack stack) {
        for (ProgramRow row : programRows) {
            row.execute(stack);
        }
    }
}
