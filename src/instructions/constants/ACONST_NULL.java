package instructions.constants;

import instructions.base.NoOperandsInstruction;
import rtda.unshared.Zframe;

public class ACONST_NULL extends NoOperandsInstruction {

    @Override
    public void execute(Zframe frame) {
        frame.getOperandStack().pushRef(null);
    }
}
