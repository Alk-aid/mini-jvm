package classfile;

import classfile.attribute.AttributeInfo;
import classfile.attribute.SourceFileAttribute;

public class ClassFile {
    private int minorVersion;
    private int majorVersion;
    public ConstantPool constantPool;
    private int accessFlags;

    private int thisClass;
    private int superClass;
    private int[] interfaces;
    private MemberInfo[] fields;        //存放类中所有的字段;
    private MemberInfo[] methods;       //存放类中所有的方法
    private AttributeInfo[] attributes; //属性表，其实本质上是和常量池类似的
    public ClassFile(byte[] classData) {
        ClassReader reader = new ClassReader(classData);
        read(reader);
    }
    private void read(ClassReader reader) {
        readAndCheckMagic(reader);
        readAndCheckVersion(reader);
        constantPool = new ConstantPool(reader);
        accessFlags = reader.readUint16();
        thisClass = reader.readUint16();
        superClass = reader.readUint16();
        interfaces = reader.readUint16s();
        fields = MemberInfo.readMembers(reader, constantPool);
        methods = MemberInfo.readMembers(reader, constantPool);
        attributes = AttributeInfo.readAttributes(reader, constantPool);
    }
    private void readAndCheckMagic(ClassReader reader) {
        long magic = reader.readUint32();
        if (magic != (0xCAFEBABE & 0x0FFFFFFFFL)) {
            throw new ClassFormatError("magic!");
        }
    }
    private void readAndCheckVersion(ClassReader reader) {
        minorVersion = reader.readUint16();
        majorVersion = reader.readUint16();
        if (majorVersion == 45) {
            return;
        }
        if (minorVersion == 0 && majorVersion >= 46 && majorVersion <= 52) {
            return;
        }
        throw new RuntimeException("java.lang.UnsupportedClassVersionError!");
    }
    public int getMinorVersion() {
        return minorVersion;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public int getThisClass() {
        return thisClass;
    }

    public int getSuperClass() {
        return superClass;
    }

    public int[] getInterfaces() {
        return interfaces;
    }

    public MemberInfo[] getFields() {
        return fields;
    }

    public MemberInfo[] getMethods() {
        return methods;
    }

    public AttributeInfo[] getAttributes() {
        return attributes;
    }

    public String getClassName() {
        return constantPool.getClassName(thisClass);
    }

    public String getSuperClassName() {
        if (superClass > 0) {
            return constantPool.getClassName(superClass);
        } else {
            return "";
        }
    }

    public String[] getInterfaceNames() {
        String[] interfaceNames = new String[interfaces.length];
        for (int i = 0; i < interfaceNames.length; i++) {
            interfaceNames[i] = constantPool.getClassName(interfaces[i]);
        }
        return interfaceNames;

    }


    public String getSourceFile() {
        for (AttributeInfo info : attributes) {
            if (info instanceof SourceFileAttribute) {
                return ((SourceFileAttribute) info).getFileName();
            }
        }
        return "unknow";
    }
}
