package instructions.math.neg;


import instructions.base.NoOperandsInstruction;
import rtda.unshared.OperandStack;
import rtda.unshared.Zframe;

public class LNEG extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        OperandStack stack = frame.getOperandStack();
        long val = stack.popLong();
        stack.pushLong(-val);
    }
}

