package rtda.heap.method_area;


import classfile.ClassFile;
import rtda.heap.AccessFlag;
import rtda.heap.ClassNameHelper;
import rtda.heap.ZclassLoader;
import rtda.heap.Zobject;
import rtda.heap.runtimepool.RuntimeConstantPool;
import rtda.unshared.Slots;

public class Zclass {
    private int accessFlags;        //类访问标志；
    public String thisClassName;    //类名 ；
    public String superClassName;   //父类名字(完全限定名)
    public String[] interfaceNames; //接口名字(完全限定名,不可以为null,若为实现接口,数组大小为0)
    private RuntimeConstantPool runtimeConstantPool;//运行时常量池,注意和class文件中常量池区别;
    public Zfield[] fileds;        //字段表,包括静态和非静态，此时并不分配 slotId；下面的staticVars 是其子集
    public Zmethod[] methods;      //方法表，包括静态和非静态
    public String sourceFile;

    public ZclassLoader loader;    //类加载器
    public Zclass superClass;      //当前类的父类class,由类加载时,给父类赋值;
    public Zclass[] interfaces;    //当前类的接口class,由类加载时,给父类赋值;

    public int instanceSlotCount;  //非静态变量占用slot大小,这里只是统计个数(从顶级父类Object开始算起)
    public int staticSlotCount;    // 静态变量所占空间大小
    public Slots staticVars;      // 存放静态变量

    public boolean initStarted;    //判断类是否已经初始化，执行了类的<clinit>方法
    public Zobject jObject;        // jObject 指向的是该类的元类对象obj。 eg：String.class 得到的结果

    public Zclass(ClassFile classFile) {
        accessFlags = classFile.getAccessFlags();
        thisClassName = classFile.getClassName();
        superClassName = classFile.getSuperClassName();
        interfaceNames = classFile.getInterfaceNames();
        runtimeConstantPool = new RuntimeConstantPool(this, classFile.getConstantPool());
        fileds = Zfield.makeFields(this, classFile.getFields());
        methods = Zmethod.makeMethods(this, classFile.getMethods());
        sourceFile = classFile.getSourceFile();
    }

    //用来创建数组类型
    public Zclass(int accessFlags, String thisClassName, ZclassLoader loader,
                  boolean initStarted, Zclass superClass, Zclass[] interfaces) {
        this.accessFlags = accessFlags;
        this.thisClassName = thisClassName;
        this.loader = loader;
        this.initStarted = initStarted;
        this.superClass = superClass;
        this.interfaces = interfaces;
    }

    public RuntimeConstantPool getRuntimeConstantPool() {
        return runtimeConstantPool;
    }

    public ZclassLoader getLoader() {
        return loader;
    }

    public Zclass getSuperClass() {
        return superClass;
    }

    public boolean isInitStarted() {
        return initStarted;
    }

    public void startInit() {
        initStarted = true;
    }


    public boolean isPublic() {
        return 0 != (accessFlags & AccessFlag.ACC_PUBLIC);
    }

    public boolean isFinal() {
        return 0 != (accessFlags & AccessFlag.ACC_FINAL);
    }

    public boolean isSuper() {
        return 0 != (accessFlags & AccessFlag.ACC_SUPER);
    }

    public boolean isInterface() {
        return 0 != (accessFlags & AccessFlag.ACC_INTERFACE);
    }

    public boolean isAbstract() {
        return 0 != (accessFlags & AccessFlag.ACC_ABSTRACT);
    }

    public boolean isSynthetic() {
        return 0 != (accessFlags & AccessFlag.ACC_SYNTHETIC);
    }

    public boolean isAnnotation() {
        return 0 != (accessFlags & AccessFlag.ACC_ANNOTATION);
    }

    public boolean isEnum() {
        return 0 != (accessFlags & AccessFlag.ACC_ENUM);
    }

    public boolean isAccessibleTo(Zclass other) {
        return isPublic() || getPackageName().equals(other.getPackageName());
    }

    public Slots getStaticVars() {
        return staticVars;
    }

    public String getPackageName() {
        int i = thisClassName.lastIndexOf("/");
        if (i > 0) {
            return thisClassName.substring(0, i);
        }
        return "";
    }

    public String getJavaName() {
        return thisClassName.replace("/", ".");
    }

    public boolean isSubClassOf(Zclass parent) {
        for (Zclass c = superClass; c != null; c = c.superClass) {
            if (c == parent) {
                return true;
            }
        }
        return false;
    }

    public boolean isSuperClassOf(Zclass sub) {
        return sub.isSubClassOf(this);
    }




    public boolean isSubInterfaceOf(Zclass iface) {
        for (Zclass superInterface : interfaces) {
            if (superInterface == iface || superInterface.isSubInterfaceOf(iface)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSuperInterfaceOf(Zclass source) {
        return source.isSubInterfaceOf(this);
    }

    public boolean isImplements(Zclass iface) {
        for (Zclass c = this; c != null; c = c.superClass) {
            for (int i = 0; i < c.interfaces.length; i++) {
                if (c.interfaces[i] == iface || c.interfaces[i].isSubInterfaceOf(iface)) {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean isAssignableFrom(Zclass source) {
        // source 是否由 target 扩展而来（子类）
        Zclass target = this;
        if (source == target) {
            return true;
        }

        if (!source.isArray()) {
            if (!source.isInterface()) {
                if (!target.isInterface()) {
                    return source.isSubClassOf(target);
                } else {
                    // target 是接口
                    return source.isImplements(target);
                }
            } else {
                // source 是接口
                if (!target.isInterface()) {
                    return target.isJlObject();
                } else {
                    // target 也是接口
                    return target.isSuperInterfaceOf(source);
                }
            }
        } else {
            //source 是数组
            if (!target.isArray()) {
                if (!target.isInterface()) {
                    return target.isJlObject();
                } else {
                    // target 是接口
                    // t is interface;数组默认实现了Cloneable和Serializable接口
                    return target.isJlCloneable() || target.isJioSerializable();
                }
            } else {
                // target 也是数组
                Zclass sc = source.getComponentClass();
                Zclass tc = target.getComponentClass();
                return sc == tc || tc.isAssignableFrom(source);
            }
        }
    }

    public boolean isJlObject() {
        return "java/lang/Object".equals(thisClassName);
    }

    public boolean isJlCloneable() {
        return "java/lang/Cloneable".equals(thisClassName);
    }

    public boolean isJioSerializable() {
        return "java/io/Serializable".equals(thisClassName);
    }

    public Zobject newObject() {
        return new Zobject(this);
    }

    public Zclass arrayClass() {
        String arrayClassName = ClassNameHelper.getArrayClassName(thisClassName);
        return loader.loadClass(arrayClassName);
    }

    //根据方法名和描述符获取方法，在测试环境中使用；
    public Zmethod getMethod(String name, String desc) {
        for (Zclass clazz = this; clazz != null; clazz = clazz.superClass) {
            for (Zmethod method : methods) {
                if (method.name.equals(name) && method.descriptor.equals(desc)) {
                    return method;
                }
            }
        }
        return null;
    }

    public Zfield getField(String name, String descriptor, boolean isStatic) {
        for (Zclass clazz = this; clazz != null; clazz = clazz.superClass) {
            for (Zfield field : clazz.fileds) {
                if (field.isStatic() == isStatic &&
                        field.name.equals(name) &&
                        field.descriptor.equals(descriptor)) {
                    return field;
                }
            }
        }
        return null;
    }

    //---------------针对数组相关的方法
    public boolean isArray() {
        return thisClassName.startsWith("[");
    }

    public Zobject newArray(int count) {
        if (!isArray()) {
            throw new RuntimeException("Not array class: " + thisClassName);
        }
        switch (thisClassName) {
            case "[Z":
                return new Zobject(this, new byte[count], null);
            case "[B":
                return new Zobject(this, new byte[count], null);
            case "[C":
                return new Zobject(this, new char[count], null);
            case "[S":
                return new Zobject(this, new short[count], null);
            case "[I":
                return new Zobject(this, new int[count], null);
            case "[J":
                return new Zobject(this, new long[count], null);
            case "[F":
                return new Zobject(this, new float[count], null);
            case "[D":
                return new Zobject(this, new double[count], null);
            default:
                return new Zobject(this, new Zobject[count], null);
        }
    }

    public Zclass getComponentClass() {
        String componentClassName = ClassNameHelper.getComponentClassName(thisClassName);
        return loader.loadClass(componentClassName);
    }

    public void setjObject(Zobject jObject) {
        this.jObject = jObject;
    }

    public Zobject getjObject() {
        return jObject;
    }

    public String getSourceFile() {
        return sourceFile;
    }
}
