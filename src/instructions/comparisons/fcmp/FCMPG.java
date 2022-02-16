package instructions.comparisons.fcmp;


import instructions.base.NoOperandsInstruction;
import rtda.unshared.Zframe;

public class FCMPG extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        FCMP._fcmp(frame, true);
    }
}
