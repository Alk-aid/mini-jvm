package instructions.loads.loadlong;


import instructions.base.Index8Instruction;
import instructions.loads.Load;
import rtda.unshared.Zframe;

public class LLOAD extends Index8Instruction {
    @Override
    public void execute(Zframe frame) {
        Load.lload(frame,index);
    }
}
