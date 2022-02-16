package instructions.conversions.i2x;


import instructions.base.NoOperandsInstruction;
import rtda.unshared.OperandStack;
import rtda.unshared.Zframe;

public class I2D extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        OperandStack stack = frame.getOperandStack();
        int val1 = stack.popInt();
        double val2 = (double) val1;
        stack.pushDouble(val2);
    }
}
