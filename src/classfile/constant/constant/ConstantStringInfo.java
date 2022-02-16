package classfile.constant.constant;


import classfile.ClassReader;
import classfile.ConstantPool;
import classfile.constant.ConstantInfo;

public class ConstantStringInfo extends ConstantInfo {
    ConstantPool constantPool;
    int stringIndex;

    public ConstantStringInfo(ConstantPool constantPool, int i) {
        this.constantPool = constantPool;
        tag = i;
    }


    //读取常量池索引
    @Override
    public void readInfo(ClassReader reader) {
        stringIndex = reader.readUint16();
    }

    public String getString() {
        return constantPool.getUtf8(stringIndex);
    }
}
