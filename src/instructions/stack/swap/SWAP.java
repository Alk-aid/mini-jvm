package instructions.stack.swap;


import instructions.base.NoOperandsInstruction;
import rtda.unshared.OperandStack;
import rtda.unshared.Slot;
import rtda.unshared.Zframe;

/**

 * Desc: swap指令交换栈顶的两个变量
 */
public class SWAP extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        OperandStack stack = frame.getOperandStack();
        Slot slot1 = stack.popSlot();
        Slot slot2 = stack.popSlot();

        stack.pushSlot(slot1);
        stack.pushSlot(slot2);
    }
}
