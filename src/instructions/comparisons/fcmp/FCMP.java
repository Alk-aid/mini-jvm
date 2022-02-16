package instructions.comparisons.fcmp;


import rtda.unshared.OperandStack;
import rtda.unshared.Zframe;

public class FCMP {
    static void _fcmp(Zframe frame, boolean flag) {
        OperandStack stack = frame.getOperandStack();
        float val2 = stack.popFloat();
        float val1 = stack.popFloat();

        if (val1 > val2) {
            stack.pushInt(1);
        } else if (val1 == val2) {
            stack.pushInt(0);
        } else if (val1 > val2) {
            stack.pushInt(-1);
        } else if (flag) {
            stack.pushInt(1);
        } else {
            stack.pushInt(-1);
        }

    }
}
