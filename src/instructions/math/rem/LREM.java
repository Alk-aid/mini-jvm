package instructions.math.rem;


import instructions.base.NoOperandsInstruction;
import rtda.unshared.OperandStack;
import rtda.unshared.Zframe;

public class LREM extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        OperandStack stack = frame.getOperandStack();
        long val2 = stack.popLong();
        if (val2 == 0) {
            throw new ArithmeticException("java.lang.ArithmeticException: / by zero");
        }
        long val1 = stack.popLong();
        long res = val1 % val2;
        stack.pushLong(res);
    }
}

