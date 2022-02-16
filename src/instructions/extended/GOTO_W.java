package instructions.extended;


import instructions.base.BranchLogic;
import instructions.base.BytecodeReader;
import instructions.base.Instruction;
import rtda.unshared.Zframe;

public class GOTO_W implements Instruction {
    int offset;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        offset = reader.readInt32();
    }

    @Override
    public void execute(Zframe frame) {
        BranchLogic.branch(frame, offset);
    }
}
