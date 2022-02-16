package instructions.references;


import instructions.base.Index16Instruction;
import rtda.heap.Zobject;
import rtda.heap.method_area.Zclass;
import rtda.heap.method_area.Zfield;
import rtda.heap.method_area.Zmethod;
import rtda.heap.runtimepool.RuntimeConstantPool;
import rtda.heap.symRef.FieldRef;
import rtda.unshared.OperandStack;
import rtda.unshared.Slots;
import rtda.unshared.Zframe;

/**

 * Desc: 从实例变量中获取值,并将之放在当前操作数栈
 */
public class GET_FIELD extends Index16Instruction {
    @Override
    public void execute(Zframe frame) {
        Zmethod currentMethod = frame.getMethod();
        Zclass currentClass = currentMethod.getClazz();
        RuntimeConstantPool runtimeConstantPool = currentClass.getRuntimeConstantPool();

        //获取到字段的符号引用
        FieldRef fieldRef = (FieldRef) runtimeConstantPool.getRuntimeConstant(index).getValue();
        //将字段符号引用转换为直接引用
        Zfield field = fieldRef.resolvedField();

        if (field.isStatic()) {
            throw new IncompatibleClassChangeError("should not call a static field by an instance");
        }

        String descriptor = field.getDescriptor();
        int slotId = field.getSlotId();
        OperandStack stack = frame.getOperandStack();
        //获取字段所在的实例
        Zobject instance = stack.popRef();
        if (instance == null) {
            throw new NullPointerException("call " + field.getName() + " on a null object");
        }
        Slots slots = instance.getFields();
        switch (descriptor.charAt(0)) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
                stack.pushInt(slots.getInt(slotId));
                break;
            case 'F':
                stack.pushFloat(slots.getFloat(slotId));
                break;
            case 'J':
                stack.pushLong(slots.getLong(slotId));
                break;
            case 'D':
                stack.pushDouble(slots.getDouble(slotId));
                break;
            case 'L':
            case '[':
                stack.pushRef(slots.getRef(slotId));
                break;
            default:
                break;
        }
    }
}
