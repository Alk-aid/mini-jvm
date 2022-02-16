package classfile.constant.constant;


import classfile.ClassReader;
import classfile.constant.ConstantInfo;

public class ConstantLongInfo extends ConstantInfo {
    long val;

    public ConstantLongInfo(int i) {
       tag = i;
    }


    @Override
    public void readInfo(ClassReader reader) {
        val= reader.readLong();
    }

    public long getVal() {
        return val;
    }


}
