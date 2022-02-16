package instructions.constants;


import instructions.base.BytecodeReader;
import instructions.base.Instruction;
import rtda.unshared.Zframe;

public class BIPUSH implements Instruction {
    int val;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        val = reader.readInt8();
    }

    @Override
    public void execute(Zframe frame) {
        frame.getOperandStack().pushInt((val + 256) % 256);
    }
}
