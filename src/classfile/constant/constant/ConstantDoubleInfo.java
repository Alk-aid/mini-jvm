package classfile.constant.constant;


import classfile.ClassReader;
import classfile.constant.ConstantInfo;

public class ConstantDoubleInfo extends ConstantInfo {
    double val;

    public ConstantDoubleInfo(int i) {
        tag = i;
    }


    @Override
    public void readInfo(ClassReader reader) {
        val = reader.readDouble();
    }

    public double getVal() {
        return val;
    }
}
