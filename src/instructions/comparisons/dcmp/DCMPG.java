package instructions.comparisons.dcmp;


import instructions.base.NoOperandsInstruction;
import rtda.unshared.Zframe;

public class DCMPG extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        DCMP._dcmp(frame, true);
    }
}
