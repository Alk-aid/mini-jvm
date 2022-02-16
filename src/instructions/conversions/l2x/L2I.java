package instructions.conversions.l2x;


import instructions.base.NoOperandsInstruction;
import rtda.unshared.OperandStack;
import rtda.unshared.Zframe;

public class L2I extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        OperandStack stack = frame.getOperandStack();
        long val1 = stack.popLong();
        int val2 = (int) val1;
        stack.pushInt(val2);
    }
}
