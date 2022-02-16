package instructions.math.and;


import instructions.base.NoOperandsInstruction;
import rtda.unshared.OperandStack;
import rtda.unshared.Zframe;

/**

 * Desc:
 */
public class LAND extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        OperandStack stack = frame.getOperandStack();
        long val2 = stack.popLong();
        long val1 = stack.popLong();
        long res = val1 & val2;
        stack.pushLong(res);
    }
}
