package instructions.control;


import instructions.base.NoOperandsInstruction;
import rtda.unshared.Zframe;
import rtda.unshared.Zthread;

public class LRETURN extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        Zthread thread = frame.getThread();
        Zframe currentFrame = thread.popFrame();
        Zframe invokerFrame = thread.getCurrentFrame();
        long val = currentFrame.getOperandStack().popLong();
        invokerFrame.getOperandStack().pushLong(val);
    }
}
