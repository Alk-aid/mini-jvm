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

/**
 * @Author: Alk-aid
 * @Date: 2/1/2022 11:15
 * @Description:
 */
public class Test09NewArray {
    public static void main(String[] args) {
        System.out.println("the same as testInterpreter06!");
        Scanner in = new Scanner(System.in);
        // java -cp /Users/zachaxy/TestClassFiles  TestNewArray09
        String cmdLine = in.nextLine();
        Cmd cmd = new Cmd(cmdLine);
        ClassPath classPath = new ClassPath(cmd.getCpOption());
        ZclassLoader classLoader = new ZclassLoader(classPath);
        Zclass testClass = classLoader.loadClass(cmd.getClassName());
        Zmethod testMethod = testClass.getMethod("test", "()V");
        //初始化栈帧
        Zthread thread = new Zthread();
        Zframe frame = thread.createFrame(testMethod);
        Zframe outFrame = frame;
        thread.pushFrame(frame);
        BytecodeReader reader = new BytecodeReader();

        while (true) {
            frame = thread.getCurrentFrame();
            int pc = frame.getNextPC();
            thread.setPc(pc);


            //decode
            reader.reset(frame.getMethod().getCode(), pc);
            int opCode = reader.readUint8();
            //解析指令,创建指令,然后根据不同的指令执行不同的操作
            try {
                Instruction instruction = InstructionFactory.createInstruction(opCode);
                instruction.fetchOperands(reader);
                frame.setNextPC(reader.getPc());
                instruction.execute(frame);
                if (frame == outFrame) {
                    System.out.println("current instruction: " + pc + ": " + instruction.getClass().getSimpleName());
                }
                if (thread.isStackEmpty()) {
                    if (!frame.getOperandStack().isEmpty()) {
                        System.out.println("return: " + frame.getOperandStack().popInt());
                    }
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                //return;
            }
        }
    }

    public static void test() {
        int[] a1 = new int[10];         //newarray
        String[] a2 = new String[10];   //anewarray
        int[][] a3 = new int[10][10];   //multianewarray
        int x = a1.length;              //arraylength
        a1[0] = 100;                    //iastore
        int y = a1[0];                  //iaload
        //暂时还未实现字符串的 ldc，因此下面对字符串数组的赋值无法进行；
//        a2[0] = "abc";                  //aastore
//        String s = a2[0];               //aaload
    }
}
