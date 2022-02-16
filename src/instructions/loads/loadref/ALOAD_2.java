package instructions.loads.loadref;


import instructions.base.NoOperandsInstruction;
import instructions.loads.Load;
import rtda.unshared.Zframe;

public class ALOAD_2 extends NoOperandsInstruction {
    @Override
    public void execute(Zframe frame) {
        Load.aload(frame,2);
    }
}
