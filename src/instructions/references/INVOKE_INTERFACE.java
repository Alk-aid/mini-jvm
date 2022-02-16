package instructions.references;


import instructions.base.BytecodeReader;
import instructions.base.Index16Instruction;
import instructions.base.MethodInvokeLogic;
import rtda.heap.Zobject;
import rtda.heap.method_area.Zclass;
import rtda.heap.method_area.Zmethod;
import rtda.heap.runtimepool.RuntimeConstantPool;
import rtda.heap.symRef.InterfaceMethodRef;
import rtda.heap.symRef.MethodLookup;
import rtda.unshared.Zframe;


public class INVOKE_INTERFACE extends Index16Instruction {
    @Override
    public void fetchOperands(BytecodeReader reader) {
        super.fetchOperands(reader);
        reader.readUint8();// 第三个字节是接口方法的参数数量,其实可以像实例方法那样计算出来,但是历史原因保留了下来
        reader.readUint8();// 第四个字节必须为0
    }

    @Override
    public void execute(Zframe frame) {
        // 调用该方法所在的类
        Zclass currentClass = frame.getMethod().getClazz();
        RuntimeConstantPool runtimeConstantPool = currentClass.getRuntimeConstantPool();
        //通过index,拿到方法符号引用
        InterfaceMethodRef methodRef = (InterfaceMethodRef) runtimeConstantPool.getRuntimeConstant(index).getValue();
        Zmethod resolvedMethod = methodRef.resolvedInterfaceMethod();
        if (resolvedMethod.isStatic() || resolvedMethod.isPrivate()) {
            throw new IncompatibleClassChangeError(resolvedMethod.getName());
        }

        //接口方法核心在于确定一个实现了该接口的实例
        Zobject ref = frame.getOperandStack().getRefFromTop(resolvedMethod.getArgSlotCount() - 1);
        if (ref == null) {
            throw new NullPointerException("called " + resolvedMethod.getName() + " on a null reference!");
        }
        //验证该实例是否确实实现了对应的接口
        if (!ref.getClazz().isImplements(methodRef.resolvedClass())) {
            throw new IncompatibleClassChangeError(ref.getClazz().thisClassName + " doesn't implements " + methodRef.resolvedClass().thisClassName);
        }

        Zmethod methodToBeInvoked = MethodLookup.lookupMethodInClass(ref.getClazz(),
                methodRef.getName(), methodRef.getDescriptor());

        if (methodToBeInvoked == null || methodToBeInvoked.isAbstract()) {
            throw new AbstractMethodError(methodToBeInvoked.getName());
        }

        if (!methodToBeInvoked.isPublic()) {
            throw new IllegalAccessError(methodToBeInvoked.getName() + " is not public");
        }

        MethodInvokeLogic.invokeMethod(frame, methodToBeInvoked);
    }
}
