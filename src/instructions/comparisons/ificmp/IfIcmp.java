package instructions.comparisons.ificmp;

import rtda.unshared.OperandStack;
import rtda.unshared.Zframe;
public class IfIcmp {
    static int[] _icmpPop(Zframe frame) {
        OperandStack stack = frame.getOperandStack();
        int[] res = new int[2];
        res[1] = stack.popInt();
        res[0] = stack.popInt();
        return res;
    }
}
