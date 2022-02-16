package instructions.constants;


import classfile.constant.ConstantInfo;
import instructions.base.Index16Instruction;
import rtda.heap.method_area.Zclass;
import rtda.heap.runtimepool.RuntimeConstantInfo;
import rtda.unshared.OperandStack;
import rtda.unshared.Zframe;

public class LDC2_W extends Index16Instruction {
    @Override
    public void execute(Zframe frame) {
        OperandStack operandStack = frame.getOperandStack();
        Zclass clazz = frame.getMethod().getClazz();
        RuntimeConstantInfo runtimeConstant = clazz.getRuntimeConstantPool().getRuntimeConstant(index);
        switch (runtimeConstant.getType()){
            case ConstantInfo.CONSTANT_Long:
                operandStack.pushLong((Long) runtimeConstant.getValue());
                break;
            case ConstantInfo.CONSTANT_Double:
                operandStack.pushDouble((Double) runtimeConstant.getValue());
                break;
            default:
                throw new ClassFormatError();
        }
    }
}
