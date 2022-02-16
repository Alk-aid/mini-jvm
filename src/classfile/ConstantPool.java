package classfile;

import classfile.constant.ConstantInfo;
import classfile.constant.constant.ConstantDoubleInfo;
import classfile.constant.constant.ConstantLongInfo;
import classfile.constant.constant.ConstantUtf8Info;
import classfile.constant.ref.ConstantClassInfo;
import classfile.constant.ref.ConstantNameAndTypeInfo;

public class ConstantPool {
    private int constantPoolCount;
    private int realConstantPoolCount;
    // 常量的集合，主要是 字面量 和 符号引用
    ConstantInfo[] infos;

    public ConstantPool(ClassReader reader) {
        constantPoolCount = reader.readUint16();
        infos = new ConstantInfo[constantPoolCount];
        for (int i = 1; i < constantPoolCount; i++) {
            infos[i] = ConstantInfo.readConstantInfo(reader, this);
            realConstantPoolCount++;
//            System.out.println(i+":"+infos[i].getClass());
            if ((infos[i] instanceof ConstantLongInfo) || (infos[i] instanceof ConstantDoubleInfo)) {
                i++;
            }
        }
    }

    //按索引查找常量,如果没有的话,直接抛异常;
    private ConstantInfo getConstantInfo(int index) {
        if (index > 0  && index < constantPoolCount) {
            ConstantInfo info = infos[index];
            if (info != null) {
                return info;
            }
        }
        throw new NullPointerException("Invalid constant pool index!");
    }
    //只要调用这个方法，一定是想去读字符串常量了，所以拿到index所对应的常量后，直接强转为ConstantUtf8Info，然后获取其val值；
    public String getUtf8(int index) {
        return ((ConstantUtf8Info) getConstantInfo(index)).val;
    }

    //常量池查找字段或方法的名字和描述符
    public String getName(int index) {
        ConstantNameAndTypeInfo info = (ConstantNameAndTypeInfo) getConstantInfo(index);
        return getUtf8(info.nameIndex);
    }
    //常量池查找字段或方法的描述符,描述符其实就是由其对应的类型名字对应而成;
    public String getType(int index) {
        ConstantNameAndTypeInfo info = (ConstantNameAndTypeInfo) getConstantInfo(index);
        return getUtf8(info.descriptorIndex);
    }
    public String[] getNameAndType(int index) {
        String[] str = new String[2];
        ConstantNameAndTypeInfo info = (ConstantNameAndTypeInfo) getConstantInfo(index);
        str[0] = getUtf8(info.nameIndex);
        str[1] = getUtf8(info.descriptorIndex);
        return str;
    }
    public String getClassName(int index) {
        ConstantClassInfo info = (ConstantClassInfo) getConstantInfo(index);
        return getUtf8(info.nameIndex);
    }
    public int getConstantPoolCount() {
        return constantPoolCount;
    }
    public ConstantInfo[] getInfos() {
        return infos;
    }
}
