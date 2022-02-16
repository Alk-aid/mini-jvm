package instructions.control;


import instructions.base.NoOperandsInstruction;
import rtda.unshared.Zframe;

public class RETURN extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        frame.getThread().popFrame();
    }
}
