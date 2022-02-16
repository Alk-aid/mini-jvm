package classfile.constant.constant;


import classfile.ClassReader;
import classfile.constant.ConstantInfo;

public class ConstantFloatInfo extends ConstantInfo {
    float val;

    public ConstantFloatInfo(int i) {
        tag = i;
    }

    @Override
    public void readInfo(ClassReader reader) {
        val= reader.readFloat();
    }
    public float getVal() {
        return val;
    }
}
