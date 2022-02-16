package rtda.heap.symRef;


import classfile.constant.ref.ConstantClassInfo;
import rtda.heap.runtimepool.RuntimeConstantPool;


public class ClassRef extends SymRef {
    public ClassRef(RuntimeConstantPool runtimeConstantPool, ConstantClassInfo classInfo) {
        super(runtimeConstantPool);
        this.className = classInfo.getName();
    }
}
