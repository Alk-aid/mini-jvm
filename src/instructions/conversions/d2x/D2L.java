package instructions.conversions.d2x;


import instructions.base.NoOperandsInstruction;
import rtda.unshared.OperandStack;
import rtda.unshared.Zframe;

public class D2L extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        OperandStack stack = frame.getOperandStack();
        double val1 = stack.popDouble();
        long val2 = (long) val1;
        stack.pushLong(val2);
    }
}
