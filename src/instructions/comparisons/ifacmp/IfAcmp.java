package instructions.comparisons.ifacmp;


import rtda.heap.Zobject;
import rtda.unshared.OperandStack;
import rtda.unshared.Zframe;

public class IfAcmp {
    public static boolean _acmp(Zframe frame) {
        OperandStack stack = frame.getOperandStack();
        Zobject ref2 = stack.popRef();
        Zobject ref1 = stack.popRef();
        return ref1 == ref2;
    }
}
