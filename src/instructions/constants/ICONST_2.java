package instructions.constants;


import instructions.base.NoOperandsInstruction;
import rtda.unshared.Zframe;

public class ICONST_2 extends NoOperandsInstruction {

    @Override
    public void execute(Zframe frame) {
        frame.getOperandStack().pushInt(2);
    }
}
