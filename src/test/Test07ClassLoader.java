package test;


import classpath.ClassPath;
import instructions.InstructionFactory;
import instructions.base.BytecodeReader;
import instructions.base.Instruction;
import rtda.heap.ZclassLoader;
import rtda.heap.method_area.Zclass;
import rtda.heap.method_area.Zmethod;
import rtda.unshared.Zframe;
import rtda.unshared.Zthread;
import utils.Cmd;

import java.util.Scanner;

public class Test07ClassLoader {
    public static void main(String[] args) {
        System.out.println("the same as testInterpreter06!");
        Scanner in = new Scanner(System.in);
        // java -cp /Users/zachaxy  TestClassLoader7
        String cmdLine = in.nextLine();
        Cmd cmd = new Cmd(cmdLine);
        ClassPath classPath = new ClassPath(cmd.getCpOption());
        ZclassLoader classLoader = new ZclassLoader(classPath);
        Zclass testClass = classLoader.loadClass(cmd.getClassName());
        Zmethod testMethod = testClass.getMethod("main", "([Ljava/lang/String;)V");
        if (testMethod != null) {
            Zthread thread = new Zthread();
            Zframe frame = thread.createFrame(testMethod);


            //start loop
            BytecodeReader reader = new BytecodeReader();
            byte[] byteCode = testMethod.getCode();
            while (true) {
                int pc = frame.getNextPC(); //这第一次frame才刚初始化，获取的pc应该是默认值0吧。
                thread.setPc(pc);
                reader.reset(byteCode, pc); //reset方法，其实是在不断的设置pc的位置。
                int opCode = reader.readUint8();
                //解析指令,创建指令,然后根据不同的指令执行不同的操作
                try {
                    Instruction instruction = InstructionFactory.createInstruction(opCode);
                    instruction.fetchOperands(reader);
                    frame.setNextPC(reader.getPc());
                    instruction.execute(frame);
                    System.out.println("current instruction: " + instruction.getClass().getSimpleName());
                } catch (Exception e) {
                    e.printStackTrace();
                    if (!frame.getOperandStack().isEmpty()) {
                        int returnVar = frame.getOperandStack().popInt();
                        System.out.println("return: " + returnVar);
                    }
                    return;
                }
            }
        } else {
            System.out.println("can't find testMethod!!!");
        }

    }

    public static int staticVar;
    public int instanceVar;

    public static int test() {
        // ldc
        int x = 31415;
        //new
        Test07ClassLoader test = new Test07ClassLoader();
        //putstatic
        Test07ClassLoader.staticVar = x;
        //getstatic
        x = Test07ClassLoader.staticVar;
        //putfield
        test.instanceVar = x;
        //getfield
        x = test.instanceVar;
        Object obj = test;
        //instanceof
        if (obj instanceof Test07ClassLoader) {
            // checkcast
            test = (Test07ClassLoader) obj;
            System.out.println(test.instanceVar);
        }
        return x;
    }
}
