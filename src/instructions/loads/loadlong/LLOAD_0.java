package instructions.loads.loadlong;


import instructions.base.NoOperandsInstruction;
import instructions.loads.Load;
import rtda.unshared.Zframe;

public class LLOAD_0 extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        Load.lload(frame,0);
    }
}
