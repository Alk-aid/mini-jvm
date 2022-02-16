package instructions.loads.loadint;


import instructions.base.NoOperandsInstruction;
import instructions.loads.Load;
import rtda.unshared.Zframe;

public class ILOAD_1 extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        Load.iload(frame,1);
    }
}
