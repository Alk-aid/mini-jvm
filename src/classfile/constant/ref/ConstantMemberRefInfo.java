package classfile.constant.ref;


import classfile.ClassReader;
import classfile.ConstantPool;
import classfile.constant.ConstantInfo;

/**

 * CONSTANT_Fieldref_info表示字段符号引用
 * CONSTANT_Methodref_info表示普通（非接口）方法符号引用
 * CONSTANT_InterfaceMethodref_info表示接口方法符号引用
 * 这三种类型结构一样,所以给出统一的类结构;
 * 然后定义三个类继承这个超类;
 * class_index和name_and_type_index都是常量池索引，分别指向CONSTANT_Class_info和CONSTANT_NameAndType_info常量。
 */
/*
CONSTANT_Fieldref_info {
    u1 tag;
    u2 class_index;
    u2 name_and_type_index;
}
 */
public class ConstantMemberRefInfo extends ConstantInfo {
    ConstantPool constantPool;
    int classIndex;
    int nameAndTypeIndex;


    //    该构造方法是供外部调用的;
    public ConstantMemberRefInfo(ConstantPool constantPool, int tag) {
        this.constantPool = constantPool;
        this.tag = tag; //因为接口,方法,字段通用这一个类,所以在构造方法中传入 i 来区分不同的类型;
    }


    @Override
    public void readInfo(ClassReader reader) {
        classIndex = reader.readUint16();
        nameAndTypeIndex = reader.readUint16();
    }

    public String getClassName() {
        return constantPool.getClassName(classIndex);
    }

    public String[] getNameAndDescriptor() {
        return constantPool.getNameAndType(nameAndTypeIndex);
    }


    //下面两个方法是将上面的单独分开拿出来的,
    public String getName() {
        return constantPool.getName(nameAndTypeIndex);
    }

    public String getDescriptor() {
        return constantPool.getType(nameAndTypeIndex);
    }


}
