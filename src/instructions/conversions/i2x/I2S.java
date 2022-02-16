package instructions.conversions.i2x;


import instructions.base.NoOperandsInstruction;
import rtda.unshared.OperandStack;
import rtda.unshared.Zframe;

public class I2S extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        OperandStack stack = frame.getOperandStack();
        int val1 = stack.popInt();
        short val2 = (short) val1;
        stack.pushInt(val2);
    }
}
