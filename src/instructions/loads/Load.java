package instructions.loads;


import rtda.heap.Zobject;
import rtda.unshared.Zframe;

public class Load {
    public static void aload(Zframe frame, int index) {
        Zobject ref = frame.getLocalVars().getRef(index);
        frame.getOperandStack().pushRef(ref);
    }

    public static void dload(Zframe frame, int index) {
        double val = frame.getLocalVars().getDouble(index);
        frame.getOperandStack().pushDouble(val);
    }

    public static void fload(Zframe frame, int index) {
        float val = frame.getLocalVars().getFloat(index);
        frame.getOperandStack().pushFloat(val);
    }

    public static void iload(Zframe frame, int index) {
        int val = frame.getLocalVars().getInt(index);
        frame.getOperandStack().pushInt(val);
    }

    public static void lload(Zframe frame, int index) {
        long val = frame.getLocalVars().getLong(index);
        frame.getOperandStack().pushLong(val);
    }

    //用在 load 数组元素时，检测数组是否为 null
    public static void checkNotNull(Zobject arrRef) {
        if (arrRef == null) {
            throw new NullPointerException();
        }
    }

    public static void checkIndex(int count, int index) {
        if (index < 0 || index >= count) {
            throw new ArrayIndexOutOfBoundsException("index: " + index + " array's count: " + count);
        }
    }
}
