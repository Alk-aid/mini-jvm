package instructions.conversions.d2x;


import instructions.base.NoOperandsInstruction;
import rtda.unshared.OperandStack;
import rtda.unshared.Zframe;

public class D2I extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        OperandStack stack = frame.getOperandStack();
        double val1 = stack.popDouble();
        int val2 = (int) val1;
        stack.pushInt(val2);
    }
}
