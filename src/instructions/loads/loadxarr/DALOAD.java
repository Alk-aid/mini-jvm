package instructions.loads.loadxarr;


import instructions.base.NoOperandsInstruction;
import instructions.loads.Load;

import rtda.heap.Zobject;
import rtda.unshared.OperandStack;
import rtda.unshared.Zframe;

public class DALOAD extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        OperandStack operandStack = frame.getOperandStack();
        //数组元素的索引值
        int index = operandStack.popInt();
        //数组对象的引用
        Zobject arrRef = operandStack.popRef();

        Load.checkNotNull(arrRef);
        //得到数组对象
        double[] refs = arrRef.getDoubles();
        Load.checkIndex(arrRef.getArrayLen(), index);
        //将数组的 index 的值压栈
        operandStack.pushDouble(refs[index]);
    }
}
