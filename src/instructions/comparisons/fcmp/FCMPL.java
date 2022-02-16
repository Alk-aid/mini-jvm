package instructions.comparisons.fcmp;


import instructions.base.NoOperandsInstruction;
import rtda.unshared.Zframe;

public class FCMPL extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        FCMP._fcmp(frame, false);
    }
}
