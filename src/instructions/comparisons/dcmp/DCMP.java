package instructions.comparisons.dcmp;


import rtda.unshared.OperandStack;
import rtda.unshared.Zframe;

/**
 * 当两个float变量中至少有一个是NaN时，用fcmpg指令比较的结果是1，而用fcmpl指令比较的结果是-1。
 */
public class DCMP {
    static void _dcmp(Zframe frame, boolean flag) {
        OperandStack stack = frame.getOperandStack();
        double val2 = stack.popDouble();
        double val1 = stack.popDouble();

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
