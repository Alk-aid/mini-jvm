package instructions.conversions.l2x;


import instructions.base.NoOperandsInstruction;
import rtda.unshared.OperandStack;
import rtda.unshared.Zframe;

public class L2D extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        OperandStack stack = frame.getOperandStack();
        long val1 = stack.popLong();
        double val2 = (double) val1;
        stack.pushDouble(val2);
    }
}
