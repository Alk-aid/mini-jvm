package instructions;


import classfile.MemberInfo;
import classfile.attribute.CodeAttribute;
import instructions.base.BytecodeReader;
import instructions.base.Instruction;
import rtda.unshared.Zframe;
import rtda.unshared.Zthread;

public class Interpretor {
    public static void interpret(Zthread thread, MemberInfo targetMethod) {
        if (targetMethod == null) System.out.println("can't load test method");
        //拿到Code属性,主要是拿到其中的字节码用来测试之间创建的指令是否有效
        CodeAttribute codeAttribute = targetMethod.getCodeAttribute();

        //获得执行方法所需的局部变量表和操作数栈空间，以及方法的字节码；
        int maxLocals = codeAttribute.getMaxLocals();
        int maxStack = codeAttribute.getMaxStack();
        byte[] byteCode = codeAttribute.getCode();

        //该线程中创建一帧
        Zframe frame = thread.createFrame(maxLocals, maxStack);
        thread.pushFrame(frame);
        BytecodeReader reader = new BytecodeReader();

        loop(thread, byteCode, frame, reader);
    }

    private static void loop(Zthread thread, byte[] byteCode, Zframe frame, BytecodeReader reader) {
        while (true) {
            int pc = frame.getNextPC();
            thread.setPc(pc);
            reader.reset(byteCode, pc); //reset 方法，其实是在不断的设置 pc 的位置
            int opCode = reader.readUint8();
            //解析指令,创建指令,然后根据不同的指令执行不同的操作
            Instruction instruction = InstructionFactory.createInstruction(opCode);
            instruction.fetchOperands(reader);
            frame.setNextPC(reader.getPc());
            System.out.printf("pc: %2d, inst: %s \n", pc, instruction.getClass().getSimpleName());
            instruction.execute(frame);
        }
    }

}
