package instructions.control;


import instructions.base.NoOperandsInstruction;
import rtda.unshared.Zframe;
import rtda.unshared.Zthread;

public class FRETURN extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        Zthread thread = frame.getThread();
        Zframe currentFrame = thread.popFrame();
        Zframe invokerFrame = thread.getCurrentFrame();
        float val = currentFrame.getOperandStack().popFloat();
        invokerFrame.getOperandStack().pushFloat(val);
    }
}
