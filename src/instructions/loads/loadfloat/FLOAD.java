package instructions.loads.loadfloat;


import instructions.base.Index8Instruction;
import instructions.loads.Load;
import rtda.unshared.Zframe;

public class FLOAD extends Index8Instruction {
    @Override
    public void execute(Zframe frame) {
        Load.fload(frame,index);
    }
}
