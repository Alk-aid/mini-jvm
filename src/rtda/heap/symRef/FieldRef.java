package rtda.heap.symRef;


import classfile.constant.ref.ConstantFieldRefInfo;
import rtda.heap.method_area.Zclass;
import rtda.heap.method_area.Zfield;
import rtda.heap.runtimepool.RuntimeConstantPool;

public class FieldRef extends MemberRef {
    Zfield field;

    public FieldRef(RuntimeConstantPool runtimeConstantPool, ConstantFieldRefInfo fieldRefInfo) {
        super(runtimeConstantPool);
        copyMemberRefInfo(fieldRefInfo);
    }

    //字段引用转直接引用
    public Zfield resolvedField() {
        if (field == null) {
            resolvedRefField();
        }
        return field;
    }

    public void resolvedRefField() {
        Zclass d = runtimeConstantPool.clazz;
        // 获取 fieldRef 所在的类
        Zclass c = resolvedClass();
        //在该类中找到对应的字段 field
        Zfield field = lookupField(c, name, descriptor);
        if (field == null) {
            throw new NoSuchFieldError("NoSuchFieldError：" + name);
        }

        if (!field.isAccessTo(d)) {
            throw new IllegalAccessError(d.thisClassName + " can't access " + name + "in Class " + c.thisClassName);
        }

        this.field = field;
    }

    private Zfield lookupField(Zclass c, String name, String descriptor) {
        for (Zfield zf : c.fileds) {
            if (zf.name.equals(name) && zf.getDescriptor().equals(descriptor)) {
                return zf;
            }
        }

        for (Zclass zin : c.interfaces) {
            return lookupField(zin, name, descriptor);
        }

        if (c.superClass != null) {
            return lookupField(c.superClass, name, descriptor);
        }

        return null;
    }

}
