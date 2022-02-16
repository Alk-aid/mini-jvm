package instructions.math.sh;


import instructions.base.NoOperandsInstruction;
import rtda.unshared.OperandStack;
import rtda.unshared.Zframe;

public class ISHR extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        OperandStack stack = frame.getOperandStack();
        int val2 = stack.popInt();  //要移动多少bit
        int val1 = stack.popInt();  //要进行位移操作的变量
        int s = val2 & 0x1f;
        int res = val1 >> s;
        stack.pushInt(res);
    }
}
