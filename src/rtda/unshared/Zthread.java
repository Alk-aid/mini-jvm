package rtda.unshared;


import rtda.heap.method_area.Zmethod;

public class Zthread {
    private int pc;         //该PC也不是自己修改的,而是由外部传入供当前线程所持有的;
    private Zstack stack; //Stack结构体（Java虚拟机栈）的引用;



    public Zthread() {
        //默认栈的大小是1024,也就是说可以存放1024个栈帧
        stack = new Zstack(1024);
    }
    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public Zstack getStack() {
        return stack;
    }

    public void pushFrame(Zframe frame) {
        stack.push(frame);
    }

    public Zframe popFrame() {
        return stack.pop();
    }

    public Zframe getCurrentFrame() {
        return stack.top();
    }

    public Zframe createFrame(int maxLocals, int maxStack) {
        return new Zframe(this, maxLocals, maxStack);
    }

    public Zframe createFrame(Zmethod method) {
        return new Zframe(this, method);
    }

    public boolean isStackEmpty() {
        return stack.size == 0;
    }

    public void clearStack() {
        while (!isStackEmpty()) {
            stack.pop();
        }
    }

    public Zframe[] getFrames() {
        return stack.getFrames();
    }
}
