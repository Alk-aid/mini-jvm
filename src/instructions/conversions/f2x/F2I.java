package instructions.conversions.f2x;


import instructions.base.NoOperandsInstruction;
import rtda.unshared.OperandStack;
import rtda.unshared.Zframe;

public class F2I extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        OperandStack stack = frame.getOperandStack();
        float val1 = stack.popFloat();
        int val2 = (int) val1;
        stack.pushInt(val2);
    }
}
