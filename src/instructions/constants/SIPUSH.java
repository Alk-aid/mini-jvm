package instructions.constants;


import instructions.base.BytecodeReader;
import instructions.base.Instruction;
import rtda.unshared.Zframe;

public class SIPUSH implements Instruction {
    int val;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        val = reader.readInt16();
    }

    @Override
    public void execute(Zframe frame) {
        frame.getOperandStack().pushInt((val + 65536) % 65536);
    }
}
