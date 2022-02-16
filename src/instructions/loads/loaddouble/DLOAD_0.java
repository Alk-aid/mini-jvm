package instructions.loads.loaddouble;


import instructions.base.NoOperandsInstruction;
import instructions.loads.Load;
import rtda.unshared.Zframe;

public class DLOAD_0 extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        Load.dload(frame,0);
    }
}
