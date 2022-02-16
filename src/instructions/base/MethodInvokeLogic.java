package instructions.base;


import rtda.heap.method_area.Zmethod;
import rtda.unshared.Slot;
import rtda.unshared.Zframe;
import rtda.unshared.Zthread;

import java.io.FileInputStream;
import java.io.IOException;

/**

 */
public class MethodInvokeLogic {
    public static void invokeMethod(Zframe invokerFrame, Zmethod method) {

        Zthread thread = invokerFrame.getThread();
        Zframe newFrame = thread.createFrame(method);
        thread.pushFrame(newFrame);

        int argSlotCount = method.getArgSlotCount();
        if (argSlotCount > 0) {
            for (int i = argSlotCount - 1; i >= 0; i--) {
                Slot slot = invokerFrame.getOperandStack().popSlot();
                newFrame.getLocalVars().setSlot(i, slot);
            }
        }
    }
}
