package instructions.comparisons.dcmp;


import instructions.base.NoOperandsInstruction;
import rtda.unshared.Zframe;

public class DCMPL extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        DCMP._dcmp(frame, false);
    }
}
