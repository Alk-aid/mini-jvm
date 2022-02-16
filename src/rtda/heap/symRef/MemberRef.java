package rtda.heap.symRef;


import classfile.constant.ref.ConstantMemberRefInfo;
import rtda.heap.runtimepool.RuntimeConstantPool;

public class MemberRef extends SymRef {
    protected String name;        //字段或方法名
    protected String descriptor;  //字段或方法描述符

    public MemberRef(RuntimeConstantPool runtimeConstantPool) {
        super(runtimeConstantPool);
    }

    void copyMemberRefInfo(ConstantMemberRefInfo refInfo) {
        className = refInfo.getClassName();
        name = refInfo.getName();
        descriptor = refInfo.getDescriptor();
    }

    public String getName() {
        return name;
    }

    public String getDescriptor() {
        return descriptor;
    }
}
