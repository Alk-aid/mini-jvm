package instructions.constants;


import instructions.base.NoOperandsInstruction;
import rtda.unshared.Zframe;

public class FCONST_0 extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        frame.getOperandStack().pushFloat(0.0f);
    }
}
