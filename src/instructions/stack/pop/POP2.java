package instructions.stack.pop;


import instructions.base.NoOperandsInstruction;
import rtda.unshared.OperandStack;
import rtda.unshared.Zframe;

/**

 * Desc: double和long变量在操作数栈中占据两个位置，需要使用pop2指令弹出
 */
public class POP2 extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        OperandStack stack = frame.getOperandStack();
        stack.popSlot();
        stack.popSlot();
    }
}
