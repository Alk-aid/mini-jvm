package classfile.constant.ref;


import classfile.ClassReader;
import classfile.ConstantPool;
import classfile.constant.ConstantInfo;

public class ConstantClassInfo extends ConstantInfo {
    ConstantPool constantPool;
    public int nameIndex;

    public ConstantClassInfo(ConstantPool constantPool, int i) {
        this.constantPool = constantPool;
        tag = i;
    }


    @Override
    public void readInfo(ClassReader reader) {
        nameIndex = reader.readUint16();
    }

    public String getName() {
        return constantPool.getUtf8(nameIndex);
    }
}
