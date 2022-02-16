package instructions.conversions.i2x;


import instructions.base.NoOperandsInstruction;
import rtda.unshared.OperandStack;
import rtda.unshared.Zframe;

public class I2L extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        OperandStack stack = frame.getOperandStack();
        int val1 = stack.popInt();
        long val2 = (long) val1;
        stack.pushLong(val2);
    }
}
