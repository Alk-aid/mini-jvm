package instructions.math.iinc;


import instructions.base.BytecodeReader;
import instructions.base.Instruction;
import rtda.unshared.LocalVars;
import rtda.unshared.Zframe;

public class IINC implements Instruction {
    public int index;
    public int offset;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        index = reader.readUint8();
        offset = reader.readInt8();
    }

    @Override
    public void execute(Zframe frame) {
        LocalVars localVars = frame.getLocalVars();
        int val = localVars.getInt(index);
        val += offset;
        localVars.setInt(index, val);
    }
}
