package instructions.math.mul;


import instructions.base.NoOperandsInstruction;
import rtda.unshared.OperandStack;
import rtda.unshared.Zframe;

public class DMUL extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        OperandStack stack = frame.getOperandStack();
        double val1 = stack.popDouble();
        double val2 = stack.popDouble();
        double res = val1 * val2;
        stack.pushDouble(res);
    }
}
