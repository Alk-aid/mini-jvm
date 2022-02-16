package instructions.control;


import instructions.base.NoOperandsInstruction;

import rtda.heap.Zobject;
import rtda.unshared.Zframe;
import rtda.unshared.Zthread;

public class ARETURN extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        Zthread thread = frame.getThread();
        Zframe currentFrame = thread.popFrame();
        Zframe invokerFrame = thread.getCurrentFrame();
        Zobject val = currentFrame.getOperandStack().popRef();
        invokerFrame.getOperandStack().pushRef(val);
    }
}
