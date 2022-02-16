package classfile.constant.ref;

import classfile.ClassReader;
import classfile.constant.ConstantInfo;

/*
CONSTANT_NameAndType_info {
    u1 tag;
    u2 name_index;
    u2 descriptor_index;
}
 */
public class ConstantNameAndTypeInfo extends ConstantInfo {
    public int nameIndex;
    public int descriptorIndex;

    public ConstantNameAndTypeInfo(int i) {
        tag = i;
    }


    @Override
    public void readInfo(ClassReader reader) {
        nameIndex = reader.readUint16();
        descriptorIndex = reader.readUint16();
    }
}
