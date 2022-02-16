package instructions.conversions.f2x;


import instructions.base.NoOperandsInstruction;
import rtda.unshared.OperandStack;
import rtda.unshared.Zframe;

public class F2D extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        OperandStack stack = frame.getOperandStack();
        float val1 = stack.popFloat();
        double val2 = val1;
        stack.pushDouble(val2);
    }
}
