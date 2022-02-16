package test;

import classfile.ClassFile;
import classfile.MemberInfo;
import classfile.attribute.AttributeInfo;
import classpath.ClassPath;
import utils.Cmd;

import java.util.Scanner;

/**
 * @Author: Alk-aid
 * @Date: 1/31/2022 16:57
 * @Description:
 */
public class Test03ClassFile {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String cmdLine = in.nextLine();
        Cmd cmd = new Cmd(cmdLine);
        ClassPath classPath = new ClassPath(cmd.getCpOption());
        byte[] classData = classPath.readClass(cmd.getClassName());
        ClassFile classFile = new ClassFile(classData);
        System.out.println("classFile.getMajorVersion: " + classFile.getMajorVersion());
        System.out.println("classFile.getMinorVersion: " + classFile.getMinorVersion());
        System.out.println("classFile.getAccessFlags: " + classFile.getAccessFlags());
        System.out.println("classFile.getClassName: " + classFile.getClassName());
        System.out.println("classFile.getSuperClassName: " + classFile.getSuperClassName());
        System.out.println("interface names:");
        for (String name : classFile.getInterfaceNames()) {
            System.out.println(name);
        }
        System.out.println("---------------------");
        System.out.println("field count: " + classFile.getFields().length);
        for (MemberInfo name : classFile.getFields()) {
            System.out.println(name.getName());
        }
        System.out.println("---------------------");
        System.out.println("method count: " + classFile.getMethods().length);
        for (MemberInfo name : classFile.getMethods()) {
            System.out.println(name.getName() + ":" + name.getDescriptor());
        }
        System.out.println("---------------------");
        System.out.println("constantPool count: "+classFile.getConstantPool().getConstantPoolCount());
        System.out.println("---------------------");
        System.out.println("attribute count:"+classFile.getAttributes().length);
        for (AttributeInfo attribute:classFile.getAttributes()){
            System.out.println(attribute.getClass());
        }
    }
}
