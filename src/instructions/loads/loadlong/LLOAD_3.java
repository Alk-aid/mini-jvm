package instructions.loads.loadlong;


import instructions.base.NoOperandsInstruction;
import instructions.loads.Load;
import rtda.unshared.Zframe;

public class LLOAD_3 extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        Load.lload(frame, 3);
    }
}
