package classfile.constant.ref;

import classfile.ConstantPool;
/**
 CONSTANT_Fieldref_info {
 u1 tag;
 u2 class_index;
 u2 name_and_type_index;
 }
 **/
public class ConstantFieldRefInfo extends ConstantMemberRefInfo {
    public ConstantFieldRefInfo(ConstantPool constantPool, int type) {
        super(constantPool,type);
    }
}
