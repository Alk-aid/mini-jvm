package instructions.control;


import instructions.base.NoOperandsInstruction;
import rtda.unshared.Zframe;
import rtda.unshared.Zthread;

public class DRETURN extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        Zthread thread = frame.getThread();
        Zframe currentFrame = thread.popFrame();
        Zframe invokerFrame = thread.getCurrentFrame();
        double val = currentFrame.getOperandStack().popDouble();
        invokerFrame.getOperandStack().pushDouble(val);
    }
}
