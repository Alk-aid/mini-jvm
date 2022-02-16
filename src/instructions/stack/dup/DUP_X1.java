package instructions.stack.dup;


import instructions.base.NoOperandsInstruction;
import rtda.unshared.OperandStack;
import rtda.unshared.Slot;
import rtda.unshared.Zframe;

/**
 * Desc: DUP_X1 指令 先将栈顶的两个变量交换,然后再将原栈顶元素添加到栈顶 ab => bab;
 */
public class DUP_X1 extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        OperandStack stack = frame.getOperandStack();
        Slot slot1 = stack.popSlot();
        Slot slot2 = stack.popSlot();
        stack.pushSlot(slot1);
        stack.pushSlot(slot2);
        stack.pushSlot(slot1);
    }
}
