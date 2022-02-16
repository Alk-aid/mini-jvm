package instructions.conversions.f2x;


import instructions.base.NoOperandsInstruction;
import rtda.unshared.OperandStack;
import rtda.unshared.Zframe;

public class F2L extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        OperandStack stack = frame.getOperandStack();
        float val1 = stack.popFloat();
        long val2 = (long) val1;
        stack.pushLong(val2);
    }
}
