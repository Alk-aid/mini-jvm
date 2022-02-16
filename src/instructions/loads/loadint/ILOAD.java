package instructions.loads.loadint;


import instructions.base.Index8Instruction;
import instructions.loads.Load;
import rtda.unshared.Zframe;

public class ILOAD extends Index8Instruction {
    @Override
    public void execute(Zframe frame) {
        Load.iload(frame,index);
    }
}
