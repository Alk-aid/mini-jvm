package instructions.loads.loadfloat;


import instructions.base.NoOperandsInstruction;
import instructions.loads.Load;
import rtda.unshared.Zframe;

public class FLOAD_3 extends NoOperandsInstruction {

    @Override
    public void execute(Zframe frame) {
        Load.fload(frame, 3);
    }
}
