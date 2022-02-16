package classfile.constant.constant;

import classfile.ClassReader;
import classfile.constant.ConstantInfo;


public class ConstantIntegerInfo extends ConstantInfo {
    int val;

    public ConstantIntegerInfo(int i) {
        tag = i;
    }

    @Override
    public void readInfo(ClassReader reader) {
        val = reader.readInteger();
    }

    public int getVal() {
        return val;
    }
}
