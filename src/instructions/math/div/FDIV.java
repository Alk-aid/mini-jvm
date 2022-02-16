package instructions.math.div;


import instructions.base.NoOperandsInstruction;
import rtda.unshared.OperandStack;
import rtda.unshared.Zframe;

public class FDIV extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        OperandStack stack = frame.getOperandStack();
        float val2 = stack.popFloat();
        float val1 = stack.popFloat();
        float res = val1 / val2;
        stack.pushFloat(res);
    }
}
