package rtda.unshared;


import rtda.heap.Zobject;

/**
 * 但要和 LocalVars 区分开，本地变量表按索引访问，操作数栈是用数组模拟的栈；方法栈是用单向链表模拟的栈
 */
public class OperandStack {

    //初始值为0,在运行中,代表当前栈顶的index,还未使用,可以直接用,用完记得size++;
    private int size;
    private Slot[] slots;

    public OperandStack(int maxStack) {
        if (maxStack >= 0) {
            slots = new Slot[maxStack];
        } else {
            throw new NullPointerException("maxStack<0");
        }
    }

    public void pushBoolean(boolean val) {
        if (val) {
            pushInt(1);
        } else {
            pushInt(0);
        }
    }

    public boolean popBoolean() {
        return popInt() == 1;
    }

    public void pushInt(int val) {
        Slot slot = new Slot();
        slot.num = val;
        slots[size++] = slot;
    }

    public int popInt() {
        return slots[--size].num;
    }

    public void pushFloat(float val) {
        Slot slot = new Slot();
        slot.num = Float.floatToIntBits(val);
        slots[size++] = slot;
    }

    public float popFloat() {
        return Float.intBitsToFloat(slots[--size].num);
    }

    public void pushLong(long val) {
        //低位
        Slot slot1 = new Slot();
        slot1.num = (int) (val);
        slots[size++] = slot1;
        //高位
        Slot slot2 = new Slot();
        slot2.num = (int) (val >> 32);
        slots[size++] = slot2;
    }

    public long popLong() {
        size -= 2;
        int low = slots[size].num;
        long high = slots[size + 1].num;
        //下面的low在和后面的数进行&运算时自动转换为long;
        return ((high & 0x000000ffffffffL) << 32) | (low & 0x00000000ffffffffL);
    }

    public void pushDouble(double val) {
        long bits = Double.doubleToLongBits(val);
        pushLong(bits);
    }

    public double popDouble() {
        long bits = popLong();
        return Double.longBitsToDouble(bits);
    }

    public void pushRef(Zobject ref) {
        Slot slot = new Slot();
        slot.ref = ref;
        slots[size++] = slot;
    }

    public Zobject popRef() {
        return slots[--size].ref;
    }

    public void pushSlot(Slot slot) {
        slots[size++] = slot;
    }

    public Slot popSlot() {
        return slots[--size];
    }

    //新添加的方法,根据参数n,返回操作数栈中的倒数第n个引用;
    public Zobject getRefFromTop(int n) {
        return slots[size - 1 - n].ref;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
    }
}

