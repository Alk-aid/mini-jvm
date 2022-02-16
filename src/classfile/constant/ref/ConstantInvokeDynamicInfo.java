package classfile.constant.ref;

import classfile.ClassReader;
import classfile.constant.ConstantInfo;

public class ConstantInvokeDynamicInfo extends ConstantInfo {
    int bootstrapMethodAttrIndex;
    int nameAndTypeIndex;

    public ConstantInvokeDynamicInfo(int i) {
        tag = i;
    }

    @Override
    public void readInfo(ClassReader reader) {
        bootstrapMethodAttrIndex = reader.readUint16();
        nameAndTypeIndex = reader.readUint16();
    }
}
