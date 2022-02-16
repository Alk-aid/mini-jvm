package instructions.loads.loaddouble;


import instructions.base.Index8Instruction;
import instructions.loads.Load;
import rtda.unshared.Zframe;

public class DLOAD extends Index8Instruction {
    @Override
    public void execute(Zframe frame) {
        Load.dload(frame,index);
    }
}
