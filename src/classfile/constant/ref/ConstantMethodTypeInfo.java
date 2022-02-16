package classfile.constant.ref;

import classfile.ClassReader;
import classfile.constant.ConstantInfo;


public class ConstantMethodTypeInfo extends ConstantInfo {
    //关于byte上界,自行处理;
    private int descriptorIndex;

    public ConstantMethodTypeInfo(int i) {
        tag = i;
    }


    @Override
    public void readInfo(ClassReader reader) {
        descriptorIndex = reader.readUint16();
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }
}
