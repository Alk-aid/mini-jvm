package rtda.unshared;

import classfile.attribute.CodeAttribute;
import rtda.heap.method_area.Zmethod;


/**
 * Desc:栈帧,执行方法所需的局部变量表大小和操作数栈深度是由编译器预先计算好的，存储在class文件method_info结构的Code属性中
 * 详细实现参考 {@link CodeAttribute}
 */
public class Zframe {
    Zframe lower;       // 当前帧的 前一帧的引用;相当于单向链表的前一个指针
    LocalVars localVars;    // 局部变量表的引用;
    OperandStack operandStack;  // 操作数栈的引用;
    int nextPC;
    Zthread thread;         // 当前栈帧所在的线程;
    Zmethod method;

    public Zframe(Zthread thread, int maxLocals, int maxStack) {
        this.thread = thread;
        localVars = new LocalVars(maxLocals);
        operandStack = new OperandStack(maxStack);
    }

    public Zframe(Zthread thread, Zmethod method) {
        this.thread = thread;
        this.method = method;
        localVars = new LocalVars(method.getMaxLocals());
        operandStack = new OperandStack(method.getMaxStack());
    }

    public LocalVars getLocalVars() {
        return localVars;
    }

    public OperandStack getOperandStack() {
        return operandStack;
    }

    public Zthread getThread() {
        return thread;
    }

    public int getNextPC() {
        return nextPC;
    }

    public void setNextPC(int nextPC) {
        this.nextPC = nextPC;
    }

    public Zmethod getMethod() {
        return method;
    }

    public void setMethod(Zmethod method) {
        this.method = method;
    }

    //用在new，getStatic，invokeStatic 等指令中，判断clinit 方法是否执行，如果执行，则需要保存当前thread 的 pc
    //eg：当前执行的是 new 指令，那么 thead 的 pc 指向的是 new，
    //再 push 一个新栈去执行<clinit>，等<clinit>直接结束后，在回到当前 frame，拿到 pc，此时的 pc 指向的还是 new
    //重新执行一遍 new
    public void revertNextPC() {
        this.nextPC = thread.getPc();
    }

}
