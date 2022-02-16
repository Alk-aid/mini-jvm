package test;



import classfile.ClassFile;
import classfile.MemberInfo;
import classpath.ClassPath;
import instructions.Interpretor;

import rtda.unshared.Zthread;
import utils.Cmd;

import java.util.Scanner;

public class Test06Inteceptor {
    public static void main(String[] args) {
        System.out.println("simply test a java method as 'public static void test()' in this class");
        System.out.println("the test class file--'TestInterpreter06.class' as you see in the src/test dir");
        System.out.println("ues 'java -cp your/path TestInterpreter06'");
        System.out.println("your/path means the path where you place the 'TestInterpreter06.class'");
        System.out.println("I beg you to place the 'TestInterpreter06.class' file in your desktop or other dir ");
        System.out.println("but not in the current project path!!!");
        Scanner in = new Scanner(System.in);
        String cmdLine = in.nextLine();
        Cmd cmd = new Cmd(cmdLine);
        ClassPath classPath = new ClassPath(cmd.getCpOption());
        byte[] classData = classPath.readClass(cmd.getClassName());
        ClassFile classFile = new ClassFile(classData);
        MemberInfo[] methods = classFile.getMethods();
        MemberInfo targetMethod = null;
        for (MemberInfo method : methods) {
            if (method.getName().equals("main")) {
                targetMethod = method;
                break;
            }
        }
        if (targetMethod != null) {
            Interpretor.interpret(new Zthread(), targetMethod);
        }
    }
}
